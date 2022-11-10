'''
Good morning! Here's your coding interview problem for today.

This problem was asked by Amazon.

Given an integer k and a string s, find the length of the longest substring that contains at most k distinct characters.

For example, given s = "abcba" and k = 2, the longest substring with k distinct characters is "bcb".

'''

def longest_substring(s, k):
    distinct, longest_sub, this_sub = set([]), "", ""

    for c in s:
        this_sub += c
        distinct.add(c)
        
        while len(distinct) > k:
            elim = this_sub[0]
            this_sub = this_sub[1:]
            distinct.discard(elim)

        if len(this_sub) > len(longest_sub):
            longest_sub = this_sub
    
    return longest_sub
            


assert(longest_substring("abcba", 2) == "bcb")