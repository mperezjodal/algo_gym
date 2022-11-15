'''
Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.

Assume the environment does not allow you to store 64-bit integers (signed or unsigned).

 

Example 1:

Input: x = 123
Output: 321
Example 2:

Input: x = -123
Output: -321
Example 3:

Input: x = 120
Output: 21
 

Constraints:

-231 <= x <= 231 - 1
'''

class Solution:
    def reverse(self, x: int) -> int:
        negative = False
        if x < 0:
            negative = True
        x_string = str(x).replace("-","")
        x_array = [*x_string]

        reversed_num = int("".join(x_array[::-1]))
        
        if reversed_num < -2**31 or reversed_num > 2**31-1:
            return 0
        
        if negative:
            return - reversed_num
        else:
            return reversed_num