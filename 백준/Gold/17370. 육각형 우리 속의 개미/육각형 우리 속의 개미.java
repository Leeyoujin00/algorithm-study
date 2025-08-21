import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int cnt = 0;
    // 이동방향 - 상(0),하(1),대각선상왼(2), 대각선상우(3), 대각선하왼(4), 대각선하우(5)
    static int[] dx = {-2,2,-1,-1,1,1};
    static int[] dy = {0,0,-1,1,-1,1};
    static int[][] nDir = {{2,3}, {4,5}, {0,4}, {0,5}, {1,2}, {1,3}};
    static int INF = 22*2*2; // 양방향으로 최대 22번 이동 * 최대 이동 크기인 2

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        // 시작 좌표는 중앙위치
        boolean[][] v = new boolean[INF][INF];
        int sx = 44, sy = 44;
        v[sx][sy] = true;
        v[sx-2][sy] = true;
        dfs(0, v, sx-2, sy, 0);
        System.out.print(cnt);
    }

    /**
     * 이전 이동이 상
     * - 다음 이동은 대각선 상왼/대각선 상오
     * 이전 이동이 대각선 상오
     * - 다음 이동은 상/대각선 하오
     * 이전 이동이 대각선 상왼
     * - 다음 이동은 상/대각선 하왼
     *
     * 이전 이동이 하
     * - 다음 이동은 대각선 하왼/대각선 하오
     * 이전 이동이 대각선 하왼
     * - 다음 이동은 하/대각선 상왼
     * 이전 이동이 대각선 하오
     * - 다음 이동은 하/대각선 상오
     */
    private static void dfs(int step, boolean[][] v, int x, int y, int dir) {
        //System.out.println("step = " + step + " x = " + x + "y = " + y);
        if (step == n) {
            return;
        }

        for (int nd : nDir[dir]) {
            int nx = x + dx[nd];
            int ny = y + dy[nd];
            // 방문하지 않은 경로일 경우에만 이동
            if (!v[nx][ny]) {
                v[nx][ny] = true;
                dfs(step+1, v, nx, ny, nd);
                v[nx][ny] = false;
            }
            if (step+1 == n && v[nx][ny]) cnt++;
        }
    }
}
