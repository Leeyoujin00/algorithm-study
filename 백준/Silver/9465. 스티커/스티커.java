import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T,n;
    static int[][] arr, dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[2][n+1];
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp = new int[n+1][3];
            
            for (int col = 1; col <= n; col++) {

                dp[col][0] += Math.max(dp[col-1][0], Math.max(dp[col-1][1], dp[col-1][2]));
                dp[col][1] += arr[0][col] + Math.max(dp[col-1][0], dp[col-1][2]);
                dp[col][2] += arr[1][col] + Math.max(dp[col-1][0], dp[col-1][1]);
            }

            System.out.println(Math.max(Math.max(dp[n][0], dp[n][1]),dp[n][2]));
        }

    }
}
