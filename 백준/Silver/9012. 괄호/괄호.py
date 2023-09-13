import sys
input = sys.stdin.readline
from collections import deque

T = int(input())
list = [list(input()) for _ in range(T)]

for i in range(T):
    queue = deque()
    isDone = False
    for j in range(0, len(list[i]) - 1):
        if list[i][j] == '(':
            queue.append(1)
        else:
            if queue:
                queue.popleft()
            else:
                isDone = True
                break
   
    if queue or isDone:
        print("NO")
    else:
        print("YES")

