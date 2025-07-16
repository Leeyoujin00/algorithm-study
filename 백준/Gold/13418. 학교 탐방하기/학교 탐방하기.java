import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static List<Node>[] graph;
    static class Node {
        int next;
        int isUpper;
        public Node (int next, int isUpper) {
            this.next = next;
            this.isUpper = isUpper;
        }
    }
    static int max = 0;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 그래프 입력받음
        for (int i = 0; i < m+1; i++) {
            st = new StringTokenizer(br.readLine());
            int now = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());
            int flag = Integer.parseInt(st.nextToken()); // 0이면 오르막길, 1이면 내리막길
            if (flag == 0) {
                // 반대로 저장 -> 1이면 오르막길, 0이면 내리막길
                graph[now].add(new Node(next, 1));
                graph[next].add(new Node(now,1));
            }
            else {
                graph[now].add(new Node(next, 0));
                graph[next].add(new Node(now,0));
            }
        }

        // MST 알고리즘 (프림 알고리즘)을 수행한다.
        reversePrim(0);
        prim(0);
        System.out.print(max - min);
    }

    // 오르막길 우선선택
    private static void reversePrim(int start) {

        boolean[] visited = new boolean[n+1];
        // 오르막길인 경로를 우선선택한다.
        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2) -> Integer.compare(o2.isUpper, o1.isUpper));
        pq.offer(new Node(start,0));

        int totalCost = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.next]) continue;
            visited[cur.next] = true;
            totalCost += cur.isUpper;


            for (Node node : graph[cur.next]) {
                if (!visited[node.next]) {
                    pq.offer(node);
                }
            }
        }

        max = totalCost*totalCost;
    }

    private static void prim(int start) {

        boolean[] visited = new boolean[n+1];
        // 내리막길인 경로를 우선선택한다.
        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2) -> Integer.compare(o1.isUpper, o2.isUpper));
        pq.offer(new Node(start,0));

        int totalCost = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.next]) continue;
            visited[cur.next] = true;
            totalCost += cur.isUpper;

            for (Node node : graph[cur.next]) {
                if (!visited[node.next]) {
                    pq.offer(node);
                }
            }
        }

        min = totalCost*totalCost;
    }
}
