import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m,r;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        graph = new List[n+1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        // 인접 노드 내림차순 정렬
        for (int i = 1; i <= n; i++) {
            Collections.sort(graph[i], Collections.reverseOrder());
        }

        System.out.println(bfs(r));

    }

    private static String bfs(int start) {

        Queue<Integer> que = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        // 시작 정점 방문
        que.offer(start);
        visited[start] = true;

        // 방문 순서 저장 배열
        int[] seq = new int[n+1];
        int s = 1;

        while (!que.isEmpty()) {
            int cur = que.poll();
            seq[cur] = s++;

            for (int next : graph[cur]) {
                if (!visited[next]) {
                    visited[next] = true;
                    que.offer(next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(seq[i] + "\n");
        }

        return sb.toString();
    }
}
