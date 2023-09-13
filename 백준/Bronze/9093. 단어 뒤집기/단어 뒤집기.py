import sys
input = sys.stdin.readline

T = int(input())
list = [list(input().split()) for _ in range(T)]

for i in range(T):
    string = []
    for j in range(len(list[i])):
        string.append(list[i][j][::-1])
    result = " ".join(string)
    print(result)