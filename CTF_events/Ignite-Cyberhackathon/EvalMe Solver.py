# Cyberhackathon 2024 filtering round CTF - Eval Me challenge
from pwn import *
import re

context.log_level="DEBUG"
# Set up the remote connection
host = '3.146.173.232'
port = 35180

# Function to convert the expression to Python syntax
def convert_expression(expression):
    # Replace the operations with Python equivalents
    expression = expression.replace('add', '+')
    expression = expression.replace('sub', '-')
    expression = expression.replace('mul', '*')
    expression = expression.replace('xor', '^')
    return expression

# Create a remote connection using pwntools
conn = remote(host, port)

# Solve 50 arithmetic expressions
try:
    for level in range(1, 51):  # Change range to start from 1
        # Receive the prompt until the 'Answer: ' prompt is received
        prompt = conn.recvuntil(b'Answer: ').decode()
        print(f"Received prompt for Level {level}: {prompt}")
        
        # Extract the expression part from the received data
        lines = prompt.splitlines()
        if len(lines) < 2:
            print("Error: Prompt did not contain enough lines.")
            break
        
        # Extract the expression (which should be on the last line before 'Answer: ')
        expression = lines[-2].strip()
        
        if not expression:
            print(f"Error: Received an empty or invalid expression at Level {level}. Exiting.")
            break
        
        print(f"Level {level} Expression: {expression}")
        
        # Convert the expression to Python syntax
        python_expression = convert_expression(expression)
        print(f"Level {level} Python Expression: {python_expression}")
        
        # Evaluate the expression
        try:
            result = eval(python_expression)  # Evaluate the expression
            print(f"Level {level} Result: {result}")
        except Exception as e:
            print(f"Error evaluating expression at Level {level}: {e}")
            break
        
        # Send the result
        conn.sendline(str(result))
        print(f"Level {level} Sent Result: {result}")
    
    # Receive the flag after completing 50 levels
    flag = conn.recvline().strip().decode()
    print(f"Final Flag: {flag}")

except EOFError:
    print("Connection closed by the server.")
finally:
    conn.close()
