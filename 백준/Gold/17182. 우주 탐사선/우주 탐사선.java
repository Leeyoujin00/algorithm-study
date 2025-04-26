import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,k;
    static int[][] t;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        t = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                t[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /**
         * 모든 행성을 탐사하는 데 걸리는 최소 시간을 계산
         * 탐색 후 다시 시작 행성으로 돌아올 필요 X, 중복 방문 가능
         *
         * 모든 행성에 대해, 각 행성으로 가는 최단경로를 저장한다.
         */
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) continue;
                    if (t[i][j] > t[i][k] + t[k][j]) {
                        t[i][j] = t[i][k] + t[k][j];
                    }
                }
            }
        }

        boolean[] visited = new boolean[n];
        visited[k] = true;
        backtracking(k, 1, 0, visited);

        System.out.print(min);
    }

    private static void backtracking(int cur, int count, int sum, boolean[] visited) {
        // 모든 행성 탐사했으면, 최소 시간 갱신
        if (count == n) {
            min = Math.min(min, sum);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                backtracking(i, count+1, sum+t[cur][i], visited);
                visited[i] = false;
            }
        }
    }
}
