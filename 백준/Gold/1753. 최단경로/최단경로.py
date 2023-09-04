import sys, heapq
INF = sys.maxsize
input = sys.stdin.readline

#시작 노드로부터 모든 노드까지의 최단 거리 구함
def dijkstra(start):
    distance = [INF] * (V + 1)
    #현재 선택된 정점들로부터 최단거리에 있는 노드 구하기 위해 사용
    Q = []
    #최소힙 사용
    heapq.heappush(Q, [0, start])
    distance[start] = 0
    
    while Q:
        minWeight, minNode = heapq.heappop(Q)
        
        for nextWeight, nextNode in graph[minNode]:
            nextWeight = nextWeight + minWeight
            if nextWeight < distance[nextNode]:
                distance[nextNode] = nextWeight
                heapq.heappush(Q, [nextWeight, nextNode])
                
    return distance
    
V, E = map(int, input().split())
start = int(input())

graph = [[] for _ in range(V + 1)]

for i in range(E):
    u, v, w = map(int, input().split())
    graph[u].append([w, v])


result = dijkstra(start)

for i in range(1, len(result)):
    if i == start:
        print(0)
    elif result[i] == INF:
        print("INF")
    else:
        print(result[i])