"""
Good morning! Here's your coding interview problem for today.

This problem was asked by Apple.

Implement a job scheduler which takes in a function f and an integer n, and calls f after n milliseconds.
"""
from time import sleep
import asyncio
import time
import threading

# my attempt
async def job_scheduler(f, n):
    sleep(n/1000)
    f()

def hi_mom():
    print("hi mom")

asyncio.run(job_scheduler(hi_mom, 2000))

# solution from: https://stackoverflow.com/questions/55642383/calling-a-function-with-delay (?)
import threading
from time import time

class Scheduler:
    def __init__(self):
        self.fns = [] # tuple of (fn, time)

        # The lock prevents 2 threads from messing with fns at the same time;
        # also lets us use Condition
        self.lock = threading.RLock()

        # The condition lets one thread wait, optionally with a timeout,
        # and lets other threads wake it up
        self.condition = threading.Condition(self.lock)

        t = threading.Thread(target=self.poll)
        t.start()

    def poll(self):
        while True:
            now = time() * 1000

            with self.lock:
                # Prevent the other thread from adding to fns while we're sorting
                # out the jobs to run now, and the jobs to keep for later

                to_run = [fn for fn, due in self.fns if due <= now]
                self.fns = [(fn, due) for (fn, due) in self.fns if due > now]

            # Run all the ready jobs outside the lock, so we don't keep it
            # locked longer than we have to
            for fn in to_run:
                fn()

            with self.lock:
                if not self.fns:
                    # If there are no more jobs, wait forever until a new job is 
                    # added in delay(), and notify_all() wakes us up again
                    self.condition.wait()
                else:
                    # Wait only until the soonest next job's due time.
                    ms_remaining = min(due for fn, due in self.fns) - time()*1000
                    if ms_remaining > 0:
                        self.condition.wait(ms_remaining / 1000)

    def delay(self, f, n):
        with self.lock:
            self.fns.append((f, time() * 1000 + n))

            # If the scheduler thread is currently waiting on the condition,
            # notify_all() will wake it up, so that it can consider the new job's
            # due time.
            self.condition.notify_all()
