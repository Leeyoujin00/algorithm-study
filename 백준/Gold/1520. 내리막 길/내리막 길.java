import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n,m;
    static int[][] arr;
    static int[][] dp;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        arr = new int[m][n];
        dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        System.out.print(dfs(0,0));
    }

    // x,y 좌표에서 도착지까지 갈 수 있는 경로의 수를 반환한다.
    private static int dfs(int x, int y) {

        // 도착지에 도달했으면 추가로 계산할 필요 x
        if (x == m-1 && y == n-1) {
            return 1;
        }

        // 이미 방문한 적 있다면 dp 값 반환
        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        else {
            dp[x][y] = 0;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;

                // 현재 높이보다 낮다면
                if (arr[x][y] > arr[nx][ny]) {
                    dp[x][y] += dfs(nx,ny);
                }
            }
        }

        return dp[x][y];
    }
}
