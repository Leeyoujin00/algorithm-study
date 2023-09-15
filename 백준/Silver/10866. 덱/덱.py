import re
import sys
input = sys.stdin.readline

N = int(input())
commands = [list(input()) for _ in range(N)]
deque = []

for i in range(N):
    str = "".join(commands[i])
    if 'push_back' in str:
        num = re.sub(r'[^0-9]','',str)
        deque.append(num)
    elif 'push_front' in str:
        num = re.sub(r'[^0-9]','',str)
        deque.append(0)
        for j in range(len(deque) - 1):
            deque[len(deque) - j - 1] = deque[len(deque) - j - 2]
        deque[0] = num
    elif 'pop_back' in str:
        if deque:
            print(deque[-1])
            del deque[-1]
        else:
            print(-1)
    elif 'pop_front' in str:
        if deque:
            print(deque[0])
            del deque[0]
        else:
            print(-1)
    elif 'size' in str:
        print(len(deque))
    elif 'empty' in str:
        if deque:
            print(0)
        else:
            print(1)
    elif 'front' in str:
        if deque:
            print(deque[0])
        else:
            print(-1)
    else:
        if deque:
            print(deque[-1])
        else:
            print(-1)
            
    


