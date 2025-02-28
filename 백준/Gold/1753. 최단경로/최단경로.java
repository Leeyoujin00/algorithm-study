import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int v,e,k;
    static List<Node>[] graph;

    private static class Node {
        int index;
        int cost; // 선택된 그래프 집합과 해당 노드와의 거리

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        // 시작 정점의 번호 k
        k = Integer.parseInt(br.readLine());

        graph = new List[v+1];
        for (int i = 0; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, w));
        }

        dijkstra();

    }

    private static void dijkstra() {

        // 방문여부 저장배열
        boolean[] visited = new boolean[v+1];
        // 시작지점부터 각 노드까지의 거리 저장 배열
        int[] dist = new int[v+1];
        int INF = Integer.MAX_VALUE;
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2) -> Integer.compare(o1.cost, o2.cost));
        // 시작 지점 자신과의 거리는 0임
        dist[k] = 0;
        pq.offer(new Node(k, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int nowIdx = cur.index;

            // 만약 해당 노드 이미 방문했다면 탐색 x
            if (visited[nowIdx]) continue;
            visited[nowIdx] = true;

            // 현재 노드와 연결된 주변 노드까지의 거리 갱신
            for (Node nextNode : graph[nowIdx]) {
                if (dist[nextNode.index] > dist[nowIdx] + nextNode.cost) {
                    dist[nextNode.index] = dist[nowIdx] + nextNode.cost;
                    pq.offer(new Node(nextNode.index, dist[nextNode.index]));
                }
            }
        }

        for (int i = 1; i <= v; i++) {
            if (dist[i] == INF) System.out.println("INF");
            else System.out.println(dist[i]);
        }
    }


}
