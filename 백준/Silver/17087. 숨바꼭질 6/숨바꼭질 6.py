import sys
input = sys.stdin.readline

def gcd(a, b):
    if (b == 0):
        return a
    else:
        return gcd(b, a % b)

N, S = map(int, input().split())
arr = list(map(int, input().split()))
# 2 4 8
for i in range(N):
    big = max(arr[i], S)
    small = min(arr[i], S)
    arr[i] = big - small

arr.sort()

result = arr[0]
for i in range(1, N):
    x = result
    result = gcd(arr[i], x)
    
print(result)

