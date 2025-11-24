import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] t, p;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        t = new int[n+2];
        p = new int[n+2];
        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i] = i일에 얻을 수 있는 최대 금액
        int[] dp = new int[n+2];
        int max = -1;
        for (int i = 1; i < n+2; i++) {
            if (max < dp[i]) {
                max = dp[i];
            }

            int nxt = i + t[i];
            if (nxt < n+2) {
                dp[nxt] = Math.max(dp[nxt], p[i] + max);
            }
        }

        System.out.print(dp[n+1]);

    }
}
