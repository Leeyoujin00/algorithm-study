import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m,x;
    static List<Node>[] graph;
    static class Node {
        int num;
        int time;
        public Node(int num, int time) {
            this.num = num;
            this.time = time;
        }
    }
    static int maxTime = 0;
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph[s].add(new Node(d,t));
        }
        // 모든 정점에서 다른 모든 정점으로 가는 경로의 길이를 구한다.
        // -> 다익스트라 알고리즘 활용
        for (int i = 1; i <= n; i++) {
            int total = dijkstra(i,x);
            total += dijkstra(x,i);
            maxTime = Math.max(maxTime, total);
        }

        System.out.print(maxTime);

    }

    private static int dijkstra(int start, int destination) {

        int[] distance = new int[n+1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(distance, INF);
        }
        distance[start] = 0;

        // 걸리는 시간이 짧은 노드 먼저 선택한다.
        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2) -> Integer.compare(o1.time, o2.time));
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            // 목적지에 도착
            if (cur.num == destination) {
                return distance[destination];
            }

            for (Node next : graph[cur.num]) {
                if (distance[next.num] > distance[cur.num] + next.time) {
                    distance[next.num] = distance[cur.num] + next.time;
                    pq.offer(new Node(next.num, distance[next.num]));
                }
            }
        }

        return -1;
    }
}
