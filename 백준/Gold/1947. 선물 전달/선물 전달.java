import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // n개의 선물을 나누는 경우의 수, 자기의 선물을 자기가 받는 경우는 없음

        long[] dp = new long[n+1]; // n 명에게 선물 나눠줄 때의 경우의 수 저장
        // 사람이 0명이거나 1명인 경우, 교환할 수 있는 경우의 수는 0
        if (n == 1) {
            System.out.println(0);
            System.exit(0);
        }

        // 2명인 경우, 교환할 수 있는 경우의 수는 1
        dp[2] = 1;
        // 완전순열의 공식 적용
        // 점화식 dp[n] = (i-1) * (dp[i-1] + dp[i-2])
        for (int i = 3; i <= n; i++) {
            dp[i] = (i-1) * (dp[i-1] + dp[i-2]) % 1000000000;
        }

        System.out.println(dp[n] % 1000000000);
    }
}
