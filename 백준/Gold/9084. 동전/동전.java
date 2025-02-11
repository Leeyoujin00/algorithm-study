import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int t, n, m;
    static int[] coin;
    static int[][] dp;
    static int[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        ans = new int[t];

        for (int tc = 0; tc < t; tc++) {
            n = Integer.parseInt(br.readLine());
            coin = new int[n+1];
            // 동전 종류
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                coin[i] = Integer.parseInt(st.nextToken());
            }
            // 만들 금액
            m = Integer.parseInt(br.readLine());

            // 동전 i를 포함했을 때 금액 j를 만들 수 있는 방법 수
            dp = new int[n+1][m+1];
            // 초기화, 0열의 모든 값은 1 <- 어떤 동전으로든 0원을 만드는 방법은 1가지임
            for (int i = 0; i <= n; i++) {
                dp[i][0] = 1;
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    dp[i][j] = dp[i-1][j];
                    if (j-coin[i] >= 0) {
                        dp[i][j] += dp[i][j-coin[i]];
                    }
                }
            }

            ans[tc] = dp[n][m];
        }

        for(int a : ans) {
            System.out.println(a);
        }
    }
}
