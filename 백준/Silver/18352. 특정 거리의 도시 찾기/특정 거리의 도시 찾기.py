import sys
from collections import deque

n, m, k, x = map(int,input().split())

arr = [[] for _ in range(n + 1)]
visited = [False] * (n + 1)

#출발 도시로부터 해당 도시까지의 최단 거리 담는 배열.
distances = [0] * (n + 1)

result = []

for i in range(m):
    s, d = map(int,sys.stdin.readline().split())
    arr[s].append(d)


def bfs(start, distance):
    queue = deque()
    queue.append(start)
    visited[start] = True
    
    while queue:
        v = queue.popleft()
        
        for i in arr[v]:
            if visited[i] == False:
                queue.append(i)
                distances[i] = distances[v] + 1
                #해당 도시 방문 처리
                visited[i] = True
    
    for i in range(1, n + 1):
        if distances[i] == distance:
            result.append(i)
    result.sort()

bfs(x, k)

if not result:
    print(-1)
else:
    for i in result:
        print(i)