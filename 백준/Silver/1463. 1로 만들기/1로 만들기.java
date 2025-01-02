import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int x;
    static int[] dp; // idx에 저장되는 수를 만들기 위해 필요한 최소 연산 수 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        x = Integer.parseInt(br.readLine());

        dp = new int[x+1];
        Arrays.fill(dp, Integer.MAX_VALUE-1);
        dp[x] = 0;

        dfs(x);

        System.out.println(dp[1]);

    }

    public static void dfs(int n) {

        if (n % 3 == 0 && dp[n/3] > dp[n]+1) {
            dp[n/3] = dp[n] + 1;
            dfs(n/3);
        }
        if (n % 2 == 0 && dp[n/2] > dp[n]+1) {
            dp[n/2] = dp[n] + 1;
            dfs(n/2);
        }
        if (n-1 > 0 && dp[n-1] > dp[n]+1) {
            dp[n-1] = dp[n]+1;
            dfs(n-1);
        }
    }
}
