import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,m,k;
    static boolean[][][] block;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(br.readLine());
        block = new boolean[m+1][n+1][2];
        // map[i][j][0] == 위에서 (i,j)로 오는 길이 막혀있는지 여부
        // map[i][j][1] == 왼쪽에서 (i,j)로 오는 길이 막혀있는지 여부

        // 도로는 양방향
        // 두 점 중, 더 낮은 곳 or 더 왼쪽에 있는 곳이 도로의 시작으로
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            if (checkSeq(a,b,c,d)) { // (a,b) -> (c,d)
                if (checkDir(a,b,c,d)) { // 좌우관계
                    block[d][c][1] = true;
                } else { // 상하관계
                    block[d][c][0] = true;
                }
            } else {
                if (checkDir(a,b,c,d)) {
                    block[b][a][1] = true;
                } else {
                    block[b][a][0] = true;
                }
            }
        }

        System.out.print(solve());
    }

    private static long solve() {
        long[][] dp = new long[m+1][n+1];
        // dp[i][j] == (i,j) 까지 오는 경우의 수
        dp[0][0] = 1;

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // 위에서 현재 경로로 이동
                if (isInRange(i-1,j) && !block[i][j][0]) {
                    dp[i][j] += dp[i-1][j];
                }
                // 왼쪽에서 현재 경로로 이동
                if (isInRange(i,j-1) && !block[i][j][1]) {
                    dp[i][j] += dp[i][j-1];
                }
            }
        }

        return dp[m][n];
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x <= m && 0 <= y && y <= n;
    }

    private static boolean checkSeq(int a, int b, int c, int d) {
        // 처음 좌표가 시작점에서 더 가까우면 true, 아니면 false 반환
        return (a < c || b < d);
    }

    // 두 좌표가 좌우관계면 true, 상하관계면 false 반환
    private static boolean checkDir(int x1, int y1, int x2, int y2) {
        return (Math.abs(y1-y2) == 0);
    }
}
