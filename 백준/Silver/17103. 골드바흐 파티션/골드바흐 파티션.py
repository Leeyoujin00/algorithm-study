prime = [] #소수 저장
n = 1000000 #소수의 개수
check = [False] * (n + 1) #지워졌으면 True
check[1] = True
#테르토테네스의 체 이용한 소수들 저장
for i in range(2, n+1):
    if check[i] == False:
        prime.append(i)
        for j in range(i * 2, n + 1, i):
            check[j] = True
        
t = int(input())
arr = []
for i in range(t):
    n = int(input())
    result = 0
    for j in range(len(prime)):
        if n <= prime[j]:
            break
        if check[n - prime[j]] == False:
            result += 1 
    arr.append(result)

for i in arr:
    if i == 1:
        print(1)
    elif i % 2 == 0:
        print(i // 2)
    else:
        print((i + 1) // 2)