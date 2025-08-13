import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
            fill(x,y,l,f);
        }

        /**
         * 1. 정사각형 모양 선택
         * 2. 정사각형에 포함된 과일의 종류는 최대 두 종류
         * 3. 0번 과일은 가져갈 수 없음
         */

        long res = 0;
        // 2종류의 과일 선택 -> 해당 과일을 가져갈 수 있는 최대 정사각형 넓이를 구함
        // 1. 과일 2종류를 선택한다.
        for (int i = 1; i <= 6; i++) {
            for (int j = i+1; j <= 7; j++) {
                res = Math.max(res, solve(i,j));
            }
        }

        System.out.println(res);
    }

    private static void fill(int x, int y, int l, int f) {

        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                map[x+i][y+j] = f;
            }
        }
    }

    private static long solve(int type1, int type2) {

        int[][] dp = new int[n][n];
        // 선택 종류 과일 표시
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == type1 || map[i][j] == type2) {
                    dp[i][j] = 1;
                }
            }
        }

        // 최대 정사각형 변길이 계산
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                // 현재 위치가 선택 과일이 아니라면, 가져갈 수 없음
                if (dp[i][j] == 0) continue;
                dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
            }
        }

        // 최대 정사각형 변길이 구함
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maxLen = Math.max(maxLen, dp[i][j]);
            }
        }

        return 1L * maxLen * maxLen;
    }
}
