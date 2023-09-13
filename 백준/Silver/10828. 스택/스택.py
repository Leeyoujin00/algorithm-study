import sys
import re
input = sys.stdin.readline

n = int(input())

word = [list(input()) for _ in range(n)]
stack = []


for i in range(n):
    str = "".join(word[i])
    if 'push' in str :
        numbers = re.sub(r'[^0-9]', '', str)
        stack.append(numbers)
    elif 'pop' in str:
        if stack:
            print(stack[-1])
            del stack[-1]
        else:
            print(-1)
    elif 'top' in str:
        if stack:
            print(stack[-1])
        else:
            print(-1)
    elif 'size' in str:
        print(len(stack))
    else:
        if stack:
            print(0)
        else: 
            print(1)