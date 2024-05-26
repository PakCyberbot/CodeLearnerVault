# challenge name: 1337 Malware 
# Event: Nahamcon 2024
# XOR Key finder through already known text of id_rsa ssh private key.

from pwn import xor

match = b"-----BEGIN OPENSSH PRIVATE KEY-----"
# match = b"\x25\x50\x44\x46\x2D"  # Example for a PDF file

enc_file = "encrypted_id_rsa"

with open(enc_file, 'rb') as f:
    enc_data = f.read()

key = b''
for cnt in range(32):
    for i in range(256):  # Iterate over all possible byte values (0-255)
        test_key = key + bytes([i])
        decode_text = xor(enc_data[:cnt + 1], test_key)  # Only decode up to current byte
        if decode_text == match[:cnt + 1]:
            key += bytes([i])
            print('Key extended:', key)
            break

print('This is the key:', key)

# key found: b'\x82\xc2SP\x8b\xd5LG\xa7\xe5m\xec\xd8v\xb5\xd6\xa4\'\xc6E\t\x0br\xe1\xb9q3"\xfe\xe1YY'