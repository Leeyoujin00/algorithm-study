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
    static int MAX = 1000000000;

    static class Node {
        int num;
        int w;
        public Node (int num, int w) {
            this.num = num;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new List[n+1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b,c));
            graph[b].add(new Node(a,c));
        }

        st = new StringTokenizer(br.readLine());
        int f1 = Integer.parseInt(st.nextToken());
        int f2 = Integer.parseInt(st.nextToken());

        System.out.println(bfs(f1,f2));
    }

    // 시작점 -> 도착점으로 경로 탐색
    // 경로 선택 기준은, 중량제한 w 값이 가장 큰 경로
    private static int bfs(int f1, int f2) {

        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2) -> Integer.compare(o2.w, o1.w));
        pq.offer(new Node(f1, 0));
        boolean[] ck = new boolean[n+1];
        ck[f1] = true;

        int min = MAX;
        while(!pq.isEmpty()) {

            Node cur = pq.poll();
            ck[cur.num] = true;
            if (cur.num != f1) min = Math.min(min, cur.w);

            if (cur.num == f2) break;

            // 연결된 경로들 넣음
            for (Node next : graph[cur.num]) {
                if (!ck[next.num]) {
                    pq.offer(next);
                }
            }
        }

        return min;
    }
}
