import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,e;
    static List<Node>[] edgeList;
    static int INF = 200000000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        edgeList = new ArrayList[n+1];

        int a,b,c;
        for (int i = 0; i <= n; i++) {
            edgeList[i] = new ArrayList<>();
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            edgeList[a].add(new Node(b, c));
            edgeList[b].add(new Node(a, c));
        }

        // 반드시 거쳐야 하는 두 개의 서로 다른 정점
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int answer = INF;
        // (1) 1번 노드 -> v1 + v2 -> n번 노드
        // (2) 1번 노드 -> v2 + v1 -> n번 노드
        // 두 값 중 큰 값을 선택해 출력

        int a1_1 = dijkstra(1, v1);
        int a1_m = dijkstra(v1, v2);
        int a1_2 = dijkstra(v2, n);
        //System.out.println(a1_2 + " " + a1_2);
        if (a1_1 != -1 && a1_2 != -1) {
            answer = a1_1 + a1_2 + a1_m;
        }

        int a2_1 = dijkstra(1, v2);
        int a2_m = dijkstra(v2,v1);
        int a2_2 = dijkstra(v1, n);
        //System.out.println(a2_1 + " " + a2_2);
        if (a2_1 != -1 && a2_2 != -1) {
            answer = Math.min(answer, a2_1 + a2_2 + a2_m);
        }

        System.out.print(answer == INF ? -1 : answer);
    }

    static class Node {
        int index;
        int cost;

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }
    }

    // 다익스트라 알고리즘 구현
    public static int dijkstra(int start, int destination) {

        // 비용이 더 작은 노드가 우선순위 가짐
        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2) -> Integer.compare(o1.cost, o2.cost));
        boolean[] visited = new boolean[n+1];
        int[] dist = new int[n+1]; // 출발지부터 각 노드까지의 거리 저장

        Arrays.fill(dist, INF);
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {

            int curNode = pq.poll().index;

            // 이미 방문한 노드일 경우 처리 x
            if (visited[curNode]) continue;

            visited[curNode] = true;

            for (Node nextNode : edgeList[curNode]) {
                // 최단경로 갱신
                if (dist[nextNode.index] > dist[curNode] + nextNode.cost) {
                    dist[nextNode.index] = dist[curNode] + nextNode.cost;
                    pq.offer(new Node(nextNode.index, dist[nextNode.index]));
                }
            }
        }

        return dist[destination] == INF ? -1 : dist[destination];
    }

}
