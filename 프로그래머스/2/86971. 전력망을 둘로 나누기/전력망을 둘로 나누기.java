import java.util.ArrayList;

public class Solution {

    ArrayList<Integer>[] graph;

    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        // 연결 그래프 초기화
        graph = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] wire : wires) {
            int s = wire[0];
            int e = wire[1];
            graph[s].add(e);
            graph[e].add(s);
        }

        // 연결을 끊어봄
        for (int[] wire : wires) {
            int v1 = wire[0];
            int v2 = wire[1];

            graph[v1].remove(Integer.valueOf(v2));
            graph[v2].remove(Integer.valueOf(v1));
            boolean[] visited = new boolean[n+1];

            int nodeCnt = dfs(1, visited);
            answer = Math.min(answer, Math.abs(nodeCnt - (n - nodeCnt)));

            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        return answer;
    }

    int dfs(int v, boolean[] visited) {

        visited[v] = true;
        
        int cnt = 1;

        for (int next : graph[v]) {
            if (visited[next]) continue;
            cnt += dfs(next, visited);
        }

        return cnt;
    }
}
