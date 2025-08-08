import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] dx = {0,1,-1,0,0};
    static int[] dy = {0,0,0,-1,1};
    static int[][] board;
    static final long MAX = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(st.nextToken());
        int sy = Integer.parseInt(st.nextToken());

        board = new int[n+1][2];
        board[0][0] = sx;
        board[0][1] = sy;

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            board[i][0] = x;
            board[i][1] = y;
        }

        // dp[i][j] = i 번째 배달 위치에서 (중앙,하,상,우,좌)에 대한 최단거리
        long[][] dp = new long[n+1][5];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 5; j++) {
                dp[i][j] = MAX;
            }
        }

        for (int i = 1; i < 5; i++) {
            int x = sx + dx[i];
            int y = sy + dy[i];
            if (!isInRange(x,y)) continue;
            dp[0][i] = 1;
        }

        for (int i = 1; i <= n; i++) {
            // 이전 위치들 중 현재 위치까지 가장 가까운 거리를 저장
            for (int j = 0; j < 5; j++) {
                int curX = board[i][0] + dx[j];
                int curY = board[i][1] + dy[j];
                if (!isInRange(curX, curY)) continue;
                for (int k = 0; k < 5; k++) {
                    int prevX = board[i-1][0] + dx[k];
                    int prevY = board[i-1][1] + dy[k];
                    if (!isInRange(prevX, prevY)) continue;
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][k] + calDistance(curX, curY, prevX, prevY));
                }
            }
        }

        long min = Long.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            min = Math.min(min, dp[n][i]);
        }

        System.out.print(min);


    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x <= 100000 && 0 <= y && y <= 100000;
    }

    private static int calDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }
}
