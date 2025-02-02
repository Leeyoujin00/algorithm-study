import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n + 2][2];

        arr[0][0] = 1;
        arr[0][1] = 0;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            arr[i][0] = t;
            arr[i][1] = p;
        }

        dp = new int[n+2];

        int max = 0;
        for (int i = 1; i <= n+1; i++) {
            int next = i + arr[i][0];
            int cost = arr[i][1];
            max = Math.max(max, dp[i]);

            if (next <= n+1) {
                dp[next] = Math.max(dp[next], max + cost);
            }
        }

       
        System.out.print(dp[n+1]);

    }
}
