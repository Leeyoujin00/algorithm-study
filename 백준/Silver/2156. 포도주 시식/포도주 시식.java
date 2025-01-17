import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int n;
    static int[] amount;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        amount = new int[n+1];
        dp = new int[n+1][3];

        // 연속으로 놓인 3잔 마실 수 없음
        // 마시거나 안마시거나
        // 6 10 13 9 8 1
        for (int i = 1; i <= n; i++) {
            amount[i] = Integer.parseInt(br.readLine());
        }

        if (n == 1) {
            System.out.print(amount[1]);
            System.exit(0);
        }
        dp[2][2] = amount[1]+amount[2];
        for (int i = 1; i <= n; i++) {
            // 연속된 개수가 0인 경우
            dp[i][0] = Math.max(Math.max(dp[i-1][0], dp[i-1][1]), dp[i-1][2]);
            // 연속된 개수가 1인 경우
            dp[i][1] = dp[i-1][0] + amount[i];
            // 연속된 개수가 2인 경우
            if (i >= 2) {
                dp[i][2] = dp[i-1][1]+amount[i];
            }
        }

        int result = Math.max(dp[n][0], Math.max(dp[n][1], dp[n][2]));
        System.out.print(result);
        
    }
}
