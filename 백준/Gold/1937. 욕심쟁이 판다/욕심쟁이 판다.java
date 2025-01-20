import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    static int n;
    static int[][] arr;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int[][] dp; // 해당 좌표에 도달하기 위해 이동한 칸 수 저장
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, dfs(i,j));
            }
        }

        System.out.print(max);

    }

    // 한 번 갔던 경로는 메모이제이션을 이용해 저장,
    // 그 영역 탐색시 저장해둔 값 반환
    // x,y 좌표에서 출발시 생존할 수 있는 기간 dp[x][y]에 저장 및 반환
    static int dfs(int x, int y) {

        // 방문한 적 있다면, 그 값 반환
        if (dp[x][y] != 0) {
            return dp[x][y];
        }

        // x, y에서 시작했을 때, 해당 위치에서 판다는 1일 생존 가능
        dp[x][y] = 1;

        int nx, ny;
        for (int i = 0; i < 4; i++) {
            nx = x + dx[i];
            ny = y + dy[i];

            // 경로 벗어나면 탐색 중지
            if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                continue;
            }

            // 이동 가능한 위치라면, 생존 가능일 갱신
            if (arr[x][y] < arr[nx][ny]) {
                dp[x][y] = Math.max(dp[x][y], dfs(nx,ny)+1);
            }
        }

        return dp[x][y];
    }
    
}