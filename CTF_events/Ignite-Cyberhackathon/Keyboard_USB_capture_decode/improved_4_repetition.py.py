
mappings = {
    0x04: "a", 0x05: "b", 0x06: "c", 0x07: "d", 0x08: "e", 0x09: "f", 0x0A: "g", 0x0B: "h", 0x0C: "i", 0x0D: "j",
    0x0E: "k", 0x0F: "l", 0x10: "m", 0x11: "n", 0x12: "o", 0x13: "p", 0x14: "q", 0x15: "r", 0x16: "s", 0x17: "t",
    0x18: "u", 0x19: "v", 0x1A: "w", 0x1B: "x", 0x1C: "y", 0x1D: "z", 0x1E: "1", 0x1F: "2", 0x20: "3", 0x21: "4",
    0x22: "5", 0x23: "6", 0x24: "7", 0x25: "8", 0x26: "9", 0x27: "0", 0x28: "\n", 0x2A: "[DEL]", 0x2B: "    ",
    0x2C: " ", 0x2D: "-", 0x2E: "=", 0x2F: "[", 0x30: "]", 0x31: "\\", 0x32: "`", 0x33: ";", 0x34: "'", 0x36: ",",
    0x37: "."
}

modifier_mappings = {
    0x02: "Shift",  # Left Shift
    0x20: "AltGr",  # Right Alt
}

# Parse each line of USB data
def parse_usb_data(line):
    # Split the line into bytes (2 hex chars per byte)
    bytes_line = [int(line[i:i+2], 16) for i in range(0, len(line), 2)]
    return bytes_line

# Handle a single report line
def process_line(bytes_line, prev_keys):
    output = ""
    
    modifier = bytes_line[0]  # Modifier byte
    key_codes = bytes_line[2:8]  # Key codes (6 bytes)

    # Handle modifier (e.g., Shift)
    shift_active = (modifier & 0x02) != 0  # Check if Left Shift is active
    
    # Iterate through the key codes and check for changes
    for key_code in key_codes:
        if key_code == 0:  # No key pressed
            continue
        if key_code not in prev_keys and key_code in mappings:  # New key press
            char = mappings[key_code]
            if shift_active and char.isalpha():  # Capitalize alphabetic characters if Shift is active
                output += char.upper()
            else:
                output += char

    return output, key_codes

# Main function to read and process the USB data file
def decode_usb_keystrokes(file_path):
    output = ""
    prev_keys = []  # Track previous pressed keys to filter out repeats

    with open(file_path, 'r') as keys:
        for line in keys:
            line = line.strip()  # Remove newline or extra spaces
            if len(line) == 16:  # Each line must be 16 hex characters (8 bytes)
                bytes_line = parse_usb_data(line)
                if bytes_line == [0] * 8:  # Reset if no keys pressed
                    prev_keys = []
                    continue
                new_output, prev_keys = process_line(bytes_line, prev_keys)
                output += new_output

    # Clean up multiple spaces and newlines
    output = ' '.join(output.split())  # Remove extra spaces
    return output

# Test the script with a file named 'usbdata.txt'
file_path = 'hex.txt'
output = decode_usb_keystrokes(file_path)
print('Output:', output)
