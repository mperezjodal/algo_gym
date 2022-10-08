def JollyJumpers(nums):
    nums_length = len(nums)
    all_nums = [0] * nums_length

    for i in range(nums_length-1):
        jump = abs(nums[i]-nums[i+1])
        
        if jump < nums_length and all_nums[jump] == 0:
            all_nums[jump] = 1
        else:
            return "Not jolly"
            
    return "Jolly"

print(JollyJumpers([1,3,2]))
print(JollyJumpers([1,4,2,3]))
print(JollyJumpers([]))
print(JollyJumpers([-1,-2]))
print(JollyJumpers([4,1,4,2,3]))
print(JollyJumpers([1,2,-1,6]))
print(JollyJumpers([5,1,2,-1,6]))
print(JollyJumpers([-1,-2,3]))