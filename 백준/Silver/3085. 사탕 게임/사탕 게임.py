import sys
input = sys.stdin.readline

N = int(input())

graph = [list(input()) for _ in range(N)]

#연속된 사탕의 최대 개수 구하는 함수
def check(graph):
    max_cnt = 1
    for i in range(N):
        cnt = 1
        # 같은 행에서 확인
        for j in range(0, N - 1):
            if graph[i][j] == graph[i][j + 1]:
                cnt += 1
            else:
                cnt = 1
            max_cnt = max(max_cnt, cnt)
        # 같은 열에서 확인
        cnt = 1
        for j in range(0, N - 1):
            if graph[j][i] == graph[j + 1][i]:
                cnt += 1
            else:
                cnt = 1
            max_cnt = max(max_cnt, cnt)
    return max_cnt
    
ans = 0

for i in range(N):
    for j in range(N):
        if j + 1 < N:
            #인접한 사탕 서로 교환
            graph[i][j], graph[i][j + 1] = graph[i][j + 1], graph[i][j]
            max_cnt = check(graph)
            ans = max(ans, max_cnt)
            #교환한 사탕 원위치로
            graph[i][j], graph[i][j + 1] = graph[i][j + 1], graph[i][j]
        
    for j in range(N): 
        if i + 1 < N:
            #인접한 사탕 서로 교환
            graph[i][j], graph[i + 1][j] = graph[i + 1][j], graph[i][j]
            max_cnt = check(graph)
            ans = max(ans, max_cnt)
            #교환한 사탕 원위치로
            graph[i][j], graph[i + 1][j] = graph[i + 1][j], graph[i][j]
    
print(ans)
    
    
    
    
    
    
    
    
    
    
    


    
