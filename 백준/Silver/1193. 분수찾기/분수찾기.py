x = int(input())

n = 0 #계산 횟수

t = 2 * x

while n * (n+1) < t:
    n += 1

num = x - ((n - 1) * n // 2) - 1
#n은 x번째 숫자 중 
if n % 2 == 1:
    s = n - num
    p = 1 + num
else :
    p = n - num
    s = 1 + num
    

print(s, "/", p, sep = '')