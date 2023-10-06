import sys

    
def gcd(a, b):
    if b == 0:
        return a
    else:
         return gcd(b, a % b)


t = int(input())

arr = []
result = []
for i in range(t):
    arr = list(map(int, input().split()))
    sum = 0
    length = arr[0]
    for j in range(1, length):
        for k in range(j + 1, length + 1):
            a = max(arr[j], arr[k])
            b = min(arr[j], arr[k])
            sum += gcd(a, b)
    result.append(sum)
    
    
for i in range(t):
    print(result[i])
