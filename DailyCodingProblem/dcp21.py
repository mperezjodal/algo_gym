''' 
Good morning! Here's your coding interview problem for today.

This problem was asked by Snapchat.

Given an array of time intervals (start, end) for classroom lectures (possibly overlapping), find the minimum number of rooms required.

For example, given [(30, 75), (0, 50), (60, 150)], you should return 2.

'''

def minRooms(arr):
    rooms = []
    for (s, e) in arr:
        hasRoom, i = False, 0
        
        while not hasRoom:
            if i == len(rooms):
                rooms.append([(s, e)])
                hasRoom = True
            if all(re < s or rs > e for (rs, re) in rooms[i]):
                rooms[i].append((s, e))
                hasRoom = True
            else: i += 1

    return len(rooms)


assert(minRooms([(30, 75), (0, 50), (60, 150)]) == 2)