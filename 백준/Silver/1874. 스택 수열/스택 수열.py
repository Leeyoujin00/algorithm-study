import sys
from collections import deque
input = sys.stdin.readline

'''
수열 차례대로 탐색, 해당 숫자가 max값(처음에는 0)보다 크다면
해당 숫자보다 작고 아직 스택에 들어온 적 없는 모든 숫자부터
해당 숫자까지 차례로 스택에 push, +출력 pop 한 번 실행, - 출력
해당 숫자가 max값보다 작다면 스택에 있다는 의미이므로
스택을 pop, pop한 값이 해당 숫자가 아니라면 NO출력하고 종료

'''


n = int(input())

list = []
for i in range(n):
    num = int(input())
    list.append(num)
    
target = list[::]

list.sort()
Q = deque(list)
tempQ = deque()
max = 0
result = []
for i in range(n):
    if target[i] > max:
        while Q[0] != target[i]:
            tempQ.append(Q.popleft())
            result.append('+')
        tempQ.append(Q.popleft())
        result.append('+')
        tempQ.pop()
        result.append('-')
        max = target[i]
    else:
        if tempQ.pop() != target[i]:
            print("NO")
            exit(0)
        else:
            result.append('-')
            
for i in range(len(result)):
    print(result[i])
        

