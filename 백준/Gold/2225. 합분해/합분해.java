import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n,k;
    static long[][] dp; // dp[더한개수][총합]

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dp = new long[k+1][n+1]; // k개를 써서 n을 만드는 경우의 수 저장
        // 0개를 써서 n을 만드는 방법은 0개이다. dp[0][n] = 0
        // 1개를 써서 n을 만드는 방법은 1개이다. dp[1][n] = 1
        // dp[k][n] = dp[k-1][0] + dp[k-1][1] + ... + dp[k-1][n-1] + dp[k-1][n]

        // 초기화
        Arrays.fill(dp[1], 1);
        // k개를 써서 0으로 만드는 방법은 1개이다.
        for (int i = 1; i <= k; i++) {
            dp[i][0] = 1;
        }

        for (int i = 2; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = (dp[i][j-1] + dp[i-1][j]) % 1000000000;
            }
        }

        System.out.print(dp[k][n] % 1000000000);

    }
}
