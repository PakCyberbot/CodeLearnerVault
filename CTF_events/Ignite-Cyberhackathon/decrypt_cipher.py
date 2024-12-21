import os
import sys
import struct
from Crypto.Cipher import AES
from binascii import unhexlify

def hex_to_bytes(hex_str):
    return unhexlify(hex_str)

def aes_decrypt(ciphertext, key, iv):
    cipher = AES.new(key, AES.MODE_CBC, iv)
    return cipher.decrypt(ciphertext)

def remove_padding(data, length):
    padding = data[length - 1]
    return length - padding

def main():
    v12 = [
        0xBE71CA1510EB3D60,
        0x81777D85F0AE732B,
        0xD708613B072C351F,
        0xF4DF1409A310982D
    ]
    v10 = 0x706050403020100
    v11 = 0xF0E0D0C0B0A0908

    # Open the FIFO pipe
    try:
        fd = os.open("my_pipe", os.O_RDONLY)
    except OSError:
        print("Failed to open FIFO for reading", file=sys.stderr)
        sys.exit(1)

    # Read from the FIFO
    try:
        buf = os.read(fd, 0x100)
    except OSError:
        print("Failed to read from FIFO", file=sys.stderr)
        sys.exit(1)

    # Close the FIFO pipe
    os.close(fd)

    buf_len = len(buf)
    buf = buf[:buf_len]
    s = buf.decode('utf-8')

    # Convert hex string to bytes
    v16 = len(s) // 2
    v15 = v16 - 1
    v5 = hex_to_bytes(s)

    # Corrected AES keys (pack 64-bit integers into 32 bytes)
    v7 = b''.join(struct.pack('Q', x) for x in v12)  # Pack each 64-bit integer into 8 bytes
    v6 = v7  # Same key for decryption

    # Correct the IV: Make sure it's 16 bytes long.
    # Concatenate v10 and v11 to create a 16-byte IV
    iv = struct.pack('Q', v10) + struct.pack('Q', v11)

    # AES decryption
    try:
        cipher = AES.new(v6, AES.MODE_CBC, iv)
        decrypted = aes_decrypt(v5, v6, iv)
    except ValueError as e:
        print(f"AES decryption failed: {e}", file=sys.stderr)
        sys.exit(1)

    # Process padding
    decrypted_len = remove_padding(decrypted, v16)
    decrypted = decrypted[:decrypted_len]

    # Output the result
    print(decrypted.decode('utf-8'))

if __name__ == "__main__":
    main()
