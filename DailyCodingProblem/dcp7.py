"""
    Good morning! Here's your coding interview problem for today.

    This problem was asked by Facebook.

    Given the mapping a = 1, b = 2, ... z = 26, and an encoded message, count the number of ways it can be decoded.

    For example, the message '111' would give 3, since it could be decoded as 'aaa', 'ka', and 'ak'.

    You can assume that the messages are decodable. For example, '001' is not allowed.
"""

def number_of_decodes(map, msg):
    if msg == '':
        return 1
    if int(msg[0]) == 0:
        return 0
        
    decode_count = 0

    for val in map.values():
        for i in range(1, len(msg)+1):
            if val == int(msg[:i]):
                decode_count += number_of_decodes(map, msg[i:])

    return decode_count

map = {'a': 1, 'b': 2, 'c': 3, 'd': 4, 'e': 5, 'f': 6, 'g': 7, 'h': 8, 'i': 9, 'j': 10, 'k': 11, 'l': 12, 'm': 13, 'n': 14, 'o': 15, 'p': 16, 'q': 17, 'r': 18, 's': 19, 't': 20, 'u': 21, 'v': 22, 'w': 23, 'x': 24, 'y': 25, 'z': 26}

assert(number_of_decodes(map, '9111') == 3)
assert(number_of_decodes(map, '111') == 3)
assert(number_of_decodes(map, '11') == 2)
assert(number_of_decodes(map, '12') == 2)
assert(number_of_decodes(map, '226') == 3)
assert(number_of_decodes(map, '06') == 0)