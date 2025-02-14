import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        /**
         * 이전 자리수의 j보다 작은 수로 끝나는 오르막수의 개수가 곧 현재 자리수의 J로 끝나는 오르막수의 개수
         */
        // i자리수의 j로 끝나는 오르막수의 개수 저장
        dp = new int[n+1][10];
        // 초기화
        // 1자리수의 i로 끝나는 오르막수의 개수는 모두 1
        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }
        // 모든 자리수의 0으로 끝나는 오르막수의 개수는 1
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++){
            for (int j = 1; j < 10; j++) {
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 10007;
            }
        }

        int sum = 0;
        for (int s: dp[n]) {
            sum += s;
        }

        System.out.print(sum % 10007);
    }
}
