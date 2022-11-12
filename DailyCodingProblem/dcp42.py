'''

Good morning! Here's your coding interview problem for today.

This problem was asked by Google.

Given a list of integers S and a target number k, write a function that returns a subset of S that adds up to k.
If such a subset cannot be made, then return null.

Integers can appear more than once in the list. You may assume all numbers in the list are positive.

For example, given S = [12, 1, 61, 5, 9, 2] and k = 24, return [12, 9, 2, 1] since it sums up to 24.
'''

def subset_with_k_sum(S, k):
    if k < 0 or len(S) == 0:
        return None

    fst = S.pop()
    if k == fst:
        return [fst]

    include = subset_with_k_sum(S.copy(), k - fst)

    if include != None:
        include.append(fst)
        return include
        
    return subset_with_k_sum(S.copy(), k)

print(subset_with_k_sum([12, 1, 61, 5, 9, 2], 24))