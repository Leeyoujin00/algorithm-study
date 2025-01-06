import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        dp = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            dp[i] = Integer.parseInt(st.nextToken());
        }

        // 수열의 연속된 숫자들 중, 즉 부분 수열 중 최대합을 구함
        // 10, -4, 3, 1, 5, 6, -35, 12, 21, -1
        // 12 + 21 이 최대합

        for (int i = 1; i < n; i++) {
            if (dp[i]+dp[i-1] >= dp[i]) {
                dp[i] += dp[i-1];
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);


    }


}
