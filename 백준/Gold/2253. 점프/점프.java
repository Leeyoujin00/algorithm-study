import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static int[][] dp;
    static int SIZE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        SIZE = (int)Math.pow(2*n, 0.5) + 1;

        /**
         * 0. 1 -> n 번 돌로 이동
         * 1. 이동은 돌 번호 증가 순으로만 가능
         * 2. 제일 처음 점프시에는 한 칸만 점프 가능, 이후에는 가속/감속 점프 가능
         *    이전 속도 +-1 씩 가능
         * 3. 각 돌들은 각기 그 크기가 다르고, 그 중 몇개의 돌은 크기가 너무 작기 때문에
         *      그러한 돌에는 올라갈 수 없음
         *   ===> 필요한 최소의 점프 횟수를 구함
         */

        boolean[] ck = new boolean[n+1]; // 이동할 수 없는 돌의 번호는 true로 저장
        // 크기 작은 돌들의 정보 저장
        for (int i = 0; i < m; i++) {
            int s = Integer.parseInt(br.readLine());
            ck[s] = true;
        }

        dp = new int[n+1][SIZE]; // 최대 이동횟수는 n번
        // dp]i][j] == i번 돌에 도달하기 위한, 직전에 j칸 점프해서 온 최소 누적점프횟수값

        int INF = 10000;
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[2][1] = 1;

        // 다음 칸으로 전파
        for (int i = 2; i <= n; i++) {
            if (ck[i]) continue;
            for (int j = 1; j < SIZE; j++) { // 점프 크기
                if (i+j <= n && !ck[i+j]) { // j칸 점프
                    dp[i+j][j] = Math.min(dp[i+j][j], dp[i][j] + 1);
                }
                if (i+j+1 <= n && j+1 < SIZE && !ck[i+j+1]) { // j+1칸 점프
                    dp[i+j+1][j+1] = Math.min(dp[i+j+1][j+1], dp[i][j] + 1);
                }
                if (i+j-1 <= n && 0 < j-1 && !ck[i+j-1]) { // j-1칸 점프
                    dp[i+j-1][j-1] = Math.min(dp[i+j-1][j-1], dp[i][j] + 1);
                }
            }
        }

        int min = INF;
        for (int i = 1; i < SIZE; i++) {
            min = Math.min(min, dp[n][i]);
        }

        if (min == INF) System.out.print(-1);
        else System.out.print(min);
    }
}
