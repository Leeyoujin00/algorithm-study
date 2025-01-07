import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map;
    static long[][] dp;
    static int cnt = 0;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        dp = new long[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int d = map[i][j];
                if (i == n-1 && j == n-1) break;
                if (i+d < n) {
                    dp[i+d][j]+=dp[i][j];
                }
                if (j+d < n) {
                    dp[i][j+d]+=dp[i][j];
                }
            }
        }

        System.out.print(dp[n-1][n-1]);

    }
}
