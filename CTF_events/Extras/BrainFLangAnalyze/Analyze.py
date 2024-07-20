# Imaginary CTF 2024 challenge named "BF" of Reversing Category
# https://2024.imaginaryctf.org/

import re

code=open("bfcode.txt","r").read()

# loops minus
loop_matches1 = re.findall(r'>>(\+*)\[', code)

# [<+++>-]
loop_matches2 = re.findall(r'\[<(\+*)>-\]', code)

numbers_to_minus = []
for val1, val2 in zip(loop_matches1, loop_matches2):
    numbers_to_minus.append(len(val1)*len(val2))

# print(numbers_to_minus)

# Main val
# <[-<+>]<-----------[><]

main_matches = re.findall(r'<\[-<\+>\]<(\-*)\[><\]', code)

for main_val, number in zip(main_matches,numbers_to_minus):
    print(chr(len(main_val)-number),end='')
