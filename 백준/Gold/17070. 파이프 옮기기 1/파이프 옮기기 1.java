import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 파이프의 한쪽 끝을 이동시키는 방법의 수를 구한다.
        /**
         * 각 칸에서 이동할 수 있는 경우의 수
         * 각 칸은, 3가지 방향 중 하나로 온 경우 중 하나임
         * 3차원 배열 -> 각 좌표로 이동하기 전 상태를 저장
         * 0 - 가로, 1 - 세로, 2 - 대각선
         */
        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /**
         * 가로, 대각선 -> 가로 (0)
         * 세로, 대각선 -> 세로 (1)
         * 가로, 세로, 대각선 -> 대각선 (2)
         */

        dp = new int[n][n][3];
        dp[0][1][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;

                // 벽일 경우 이동할 수 없음
                if (map[i][j] == 1) continue;

                // 가로 이동
                if (j-1 >= 0) {
                    dp[i][j][0] += dp[i][j-1][0];
                }
                if (j-1 >= 0) {
                    dp[i][j][0] += dp[i][j-1][2];
                }

                // 세로 이동
                if (i-1 >= 0) {
                    dp[i][j][1] += dp[i-1][j][1];
                }
                if (i-1 >= 0) {
                    dp[i][j][1] += dp[i-1][j][2];
                }

                // 대각선 이동
                if (i-1 < 0 || j-1 < 0) continue;
                if (map[i-1][j] == 1 || map[i][j-1] == 1) continue;
                dp[i][j][2] += dp[i-1][j-1][0];
                dp[i][j][2] += dp[i-1][j-1][1];
                dp[i][j][2] += dp[i-1][j-1][2];
//                if (i-1 >= 0 && j-1 >= 0) {
//                    dp[i][j][2] += dp[i-1][j-1][0];
//                }
//                if (i-1 >= 0 && j-1 >= 0) {
//                    dp[i][j][2] += dp[i-1][j-1][1];
//                }
//                if (i-1 >= 0 && j-1 >= 0) {
//                    dp[i][j][2] += dp[i-1][j-1][2];
//                }
            }
        }

        int ans = 0;
        for (int num : dp[n-1][n-1]) ans += num;

//        for (int i = 0; i < n; i++) {
//            //int a = 0;
//            for (int j = 0; j < n; j++) {
//                int a = 0;
//                a += dp[i][j][0] + dp[i][j][1] + dp[i][j][2];
//                System.out.print(a + " ");
//            }
//
//            System.out.println();
//        }

        System.out.print(ans);
    }
}
