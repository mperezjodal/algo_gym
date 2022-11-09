'''
Good morning! Here's your coding interview problem for today.

This problem was asked by Stripe.

Given an array of integers, find the first missing positive integer in linear time and constant space. In other words, find the lowest positive integer that does not exist in the array. The array can contain duplicates and negative numbers as well.

For example, the input [3, 4, -1, 1] should give 2. The input [1, 2, 0] should give 3.

You can modify the input array in-place.
'''

def MinPositiveIntecer(arr):
    arr_len = len(arr)
    allPos = [i for i in range(arr_len+1)]
    allPos[0] = -1

    for n in arr:
        if n > 0 and n < arr_len:
            allPos[n] = -1
    
    for pos in allPos:
        if pos != -1:
            return pos

    return -1

assert(MinPositiveIntecer([3, 4, -1, 1]) == 2)
assert(MinPositiveIntecer([1, 2, 0]) == 3)
