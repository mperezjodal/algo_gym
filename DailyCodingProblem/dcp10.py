"""
Good morning! Here's your coding interview problem for today.

This problem was asked by Apple.

Implement a job scheduler which takes in a function f and an integer n, and calls f after n milliseconds.
"""
from time import sleep
import asyncio

async def job_scheduler(f, n):
    sleep(n/1000)
    f()

def hi_mom():
    print("hi mom")

asyncio.run(job_scheduler(hi_mom, 2000))
