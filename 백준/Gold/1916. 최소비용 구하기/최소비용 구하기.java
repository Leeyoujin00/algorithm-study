import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class Main {
    static int n,m;
    static List<Node>[] graph;

    static class Node {
        int idx;
        int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        graph = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, c));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());

        System.out.print(dijkstra(start, dest));

    }

    private static int dijkstra(int start, int dest) {

        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2) -> Integer.compare(o1.cost, o2.cost));
        boolean[] visited = new boolean[n+1];
        int[] distance = new int[n+1];
        int INF = Integer.MAX_VALUE;

        Arrays.fill(distance, INF);
        pq.offer(new Node(start, 0));
        distance[start] = 0;

        while(!pq.isEmpty()) {
            int curIdx = pq.poll().idx;

            // 이미 방문했으면 진행 x
            if (visited[curIdx]) continue;
            visited[curIdx] = true;

            for (Node next : graph[curIdx]) {
                if (distance[next.idx] > distance[curIdx] + next.cost) {
                    distance[next.idx] = distance[curIdx] + next.cost;
                    pq.offer(new Node(next.idx, distance[next.idx]));
                }
            }
        }

        return distance[dest];
    }
}
