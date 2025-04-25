import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static int[][] map;
    // 상하좌우
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean[][] visited;

    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 모든 좌표에 대해, 상하좌우로 탐색 진행
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                solve(i,j,map[i][j],1);
                visited[i][j] = false;
            }
        }

        System.out.print(max);

    }

    // 폴리노미오를 회전이나 대칭을 시켜도 되므로, 상하좌우 4칸 탐색으로 구현할 수 있다.
    private static void solve(int r, int c, int sum, int count) {

        if (count == 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = r + dx[i];
            int ny = c + dy[i];
            // 탐색 범위 벗어났거나, 이미 방문한 위치면 진행 x
            if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny]) continue;

            // 만약 5번 모양이라면, 2번째 위치에서 탐색 한번 더 진행해야 함
            if (count == 2) {
                visited[nx][ny] = true;
                solve(r,c,sum+map[nx][ny],count+1);
                visited[nx][ny] = false;
            }

            visited[nx][ny] = true;
            solve(nx,ny,sum+map[nx][ny],count+1);
            visited[nx][ny] = false;
        }

    }
}
