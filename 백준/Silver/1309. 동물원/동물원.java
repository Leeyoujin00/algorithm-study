import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dp = new int[n+1][3];
        Arrays.fill(dp[1], 1);

        for (int i = 2; i <= n; i++) {
            // 이번 행을 비우는 경우는 이전 행이 어떤 경우든 가능
            dp[i][0] += (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % 9901;
            // 이전 행이 비어있으면, 오른쪽과 왼쪽 모두 가능
            dp[i][1] += dp[i-1][0] % 9901;
            dp[i][2] += dp[i-1][0] % 9901;

            // 이전 행에 사자가 있는 쪽 반대에 배치 가능
            dp[i][1] += dp[i-1][2] % 9901;
            dp[i][2] += dp[i-1][1] % 9901;
        }

        int result = 0;
        for (int r : dp[n]) {
            result += r;
        }

        System.out.print(result % 9901);

    }

}
