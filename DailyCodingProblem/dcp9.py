"""
Good morning! Here's your coding interview problem for today.

This problem was asked by Airbnb.

Given a list of integers, write a function that returns the largest sum of non-adjacent numbers. Numbers can be 0 or negative.

For example, [2, 4, 6, 2, 5] should return 13, since we pick 2, 6, and 5. [5, 1, 1, 5] should return 10, since we pick 5 and 5.

Follow-up: Can you do this in O(N) time and constant space?
"""

# my attempt
def largest_non_adjacent_sum(nums):
    return 0 if not nums else max(nums.pop(0) + largest_non_adjacent_sum(nums[1:]), largest_non_adjacent_sum(nums))

# solution from https://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/
def largest_non_adjacent_sum_2(nums):
    N = len(nums)
    dp = [[0 for _ in range(2)] for _ in range(N)]
     
    if (N == 1):
        return nums[0]
   
    # Initialize the values in dp array
    dp[0][0] = 0
    dp[0][1] = nums[0]
   
    # Loop to find the maximum possible sum
    for i in range(1,N):
        dp[i][1] = dp[i - 1][0] + nums[i]
        dp[i][0] = max(dp[i - 1][1], dp[i - 1][0])
   
    # Return the maximum sum
    return max(dp[N - 1][0], dp[N - 1][1])

assert(largest_non_adjacent_sum([2, 4, 6, 2, 5]) == 13)
assert(largest_non_adjacent_sum([5, 1, 1, 5]) == 10)