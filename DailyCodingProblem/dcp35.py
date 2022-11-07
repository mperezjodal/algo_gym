'''
Good morning! Here's your coding interview problem for today.

This problem was asked by Google.

Given an array of strictly the characters 'R', 'G', and 'B', segregate the values of the array so that all the Rs come first, the Gs come second, and the Bs come last. You can only swap elements of the array.

Do this in linear time and in-place.

For example, given the array ['G', 'B', 'R', 'R', 'B', 'R', 'G'], it should become ['R', 'R', 'R', 'G', 'G', 'B', 'B'].
'''

def sortRGB(arr):
    low, high = 0, len(arr) - 1

    while arr[low] == 'R':
        low += 1
    while arr[high] == 'B':
        high -= 1

    middle = low + 1

    while middle <= high:
        thisVal = arr[middle]
        
        if thisVal == 'R':
            arr[middle] = arr[low]
            arr[low] = thisVal
            low =+ 1
        elif thisVal == 'B':
            arr[middle] = arr[high]
            arr[high] = thisVal
            high -= 1
        elif thisVal == 'G':
            middle += 1
        
        while arr[low] == 'R':
            low += 1
        while arr[high] == 'B':
            high -= 1
    
    return arr


assert(sortRGB(['G', 'B', 'R', 'R', 'B', 'R', 'G']) == ['R', 'R', 'R', 'G', 'G', 'B', 'B'])