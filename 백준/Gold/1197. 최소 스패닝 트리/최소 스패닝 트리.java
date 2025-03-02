import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int v,e;
    static List<Node>[] graph;

    static class Node {
        int v;
        int cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }

    public static int prim(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2) -> Integer.compare(o1.cost, o2.cost));
        boolean[] visited = new boolean[v+1];

        pq.offer(new Node(start, 0));

        int sum = 0;
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int v = cur.v;
            int cost = cur.cost;

            // 방문한 적 있는 노드라면 진행x
            if (visited[v]) continue;

            // 방문한 적 없는 노드라면 최소스패닝트리에 포함한다.
            visited[v] = true;
            sum += cost;

            for (Node next : graph[v]) {
                if (!visited[next.v]) {
                    pq.offer(next);
                }
            }

        }

        return sum;
    }

    // 간선 개수가 더 많으므로, 프림 알고리즘을 사용한다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new ArrayList[v+1];
        for (int i = 0; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        System.out.print(prim(1));

    }
}
