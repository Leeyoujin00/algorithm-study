import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m,r;
    static List<Integer>[] graph;
    static int[] seq;
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        graph = new List[n+1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        // 인접 정점 내림차순 정렬
        for (int i = 1; i <= n; i++) {
            Collections.sort(graph[i], Collections.reverseOrder());
        }

        seq = new int[n+1];
        boolean[] visited = new boolean[n+1];
        dfs(1, r, visited);

        int num = 1;
        for (int s : result) {
            seq[s] = num++;
        }

        for (int i = 1; i <= n; i++) {
            System.out.println(seq[i]);
        }
    }

    private static void dfs(int num, int cur, boolean[] visited) {

        visited[cur] = true;
        result.add(cur);
        for (int next : graph[cur]) {
            if (!visited[next]) {
                dfs(++num, next, visited);
            }
        }
    }
}
