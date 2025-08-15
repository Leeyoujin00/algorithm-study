import java.io.*;
import java.util.*;

public class Main {
    static int n, m, k;
    static int[][] w; // w[a][b] = a->b 최대 점수(여러 간선 중 최대)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        w = new int[n + 1][n + 1];
        for (int t = 0; t < k; t++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a < b) w[a][b] = Math.max(w[a][b], c); // a<b만 유효
        }

        System.out.println(solve());
    }

    // dp[i][c] = 도시 i에 c개 도시를 거쳐(= c번 방문) 도달했을 때 최대 점수, 불가능은 -1
    static long solve() {
        m = Math.min(m, n); // 방문 도시 수는 n을 넘을 수 없음

        long[][] dp = new long[n + 1][m + 1];
        for (int i = 0; i <= n; i++) Arrays.fill(dp[i], -1);
        dp[1][1] = 0; // 시작: 도시 1만 방문, 점수 0

        for (int i = 1; i < n; i++) {            // 출발 도시
            for (int j = i + 1; j <= n; j++) {   // 도착 도시(번호 증가)
                if (w[i][j] == 0) continue;      // 간선 없으면 skip
                for (int c = 1; c < m; c++) {    // 현재 방문 도시 수
                    if (dp[i][c] == -1) continue; // 불가능 상태는 전이 X
                    dp[j][c + 1] = Math.max(dp[j][c + 1], dp[i][c] + w[i][j]);
                }
            }
        }

        long ans = 0;
        for (int c = 1; c <= m; c++) ans = Math.max(ans, dp[n][c]);
        return ans;
    }
}
