import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n,m;
    static boolean[] views;
    static int[][] map;
    static List<Node>[] graph;

    private static class Node {
        int v;
        long cost;

        public Node(int v, long cost) {
            this.v = v;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        views = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int v = Integer.parseInt(st.nextToken());
            if (v == 1) views[i] = true; // 해당 분기점이 상대방의 시야에 보임
        }

        //map = new int[n][n];
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b,t));
            graph[b].add(new Node(a,t));
        }

        System.out.print(dijkstra(0));
    }

    // 최단경로 구하므로 다익스트라 사용하되, 보이는 분기점은 제외하고 진행한다.
    private static long dijkstra(int start) {

        long[] dist = new long[n];
        boolean[] visited = new boolean[n];

        long INF = Long.MAX_VALUE;
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.cost, o2.cost));
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.v]) {
                continue;
            }
            // 상대의 시야에 보이는 분기점은 방문할 수 없음, 단 마지막 분기점은 방문 가능
            if (views[cur.v] && cur.v != n-1) {
                continue;
            }
            visited[cur.v] = true;

            for (Node next : graph[cur.v]) {
                if (dist[next.v] > dist[cur.v] + next.cost) {
                    dist[next.v] = dist[cur.v] + next.cost;
                    pq.offer(new Node(next.v, dist[next.v]));
                }
            }
        }

        if (dist[n-1] == INF) return -1;
        return dist[n-1];

    }
}
