import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    static int n,k;
    static int[][] dp;
    static int[] w;
    static int[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        w = new int[n+1];
        v = new int[n+1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[n+1][k+1]; // dp[i][j] = 물건 i 포함 무게 k일 때의 최대 가치 저장

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                // 이전 물건까지의 최대 가치를 가져온다.
                dp[i][j] = dp[i-1][j];

                // 현재 물건의 무게가 현재 무게 이하여야 함
                if (w[i] <= j) {
                    // 현재 물건을 포함했을 때와 안했을 때 중 더 큰 값으로 갱신
                    dp[i][j] = Math.max(dp[i][j], v[i] + dp[i-1][j-w[i]]);
                }
            }
        }

        System.out.print(dp[n][k]);
    }
}
