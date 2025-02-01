import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] dp;
    static int[] vip;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n, m;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        vip = new int[m];
        for (int i = 0; i < m; i++) {
            vip[i] = Integer.parseInt(br.readLine());
        }

        if (n == 1) {
            System.out.print(1);
            return;
        }
//        if (n == 2) {
//            System.out.print(2);
//            return;
//        }

        // 좌석 길이에 따른 배치 경우의 수 저장
        dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        //dp[2] = 2;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-2] + dp[i-1];
        }

        if (m == 0) {
            System.out.print(dp[n]);
            return;
        }

        // 연속된 좌석만큼을 곱함
        int ans = 1;
        int cnt = 0;
        int idx = 0;
        for (int i = 1; i <= n; i++) {
            if (idx < m && i < vip[idx]) {
                cnt++;
            }
            if (idx < m && i == vip[idx]) {
                idx++;
                ans *= dp[cnt];
                cnt = 0;
            }
        }

        // 마지막 vip 이후의 부분 경우의 수를 곱함
        ans *= dp[n-vip[m-1]];

        System.out.print(ans);

    }
}
