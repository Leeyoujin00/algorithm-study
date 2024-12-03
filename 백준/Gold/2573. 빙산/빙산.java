import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n,m;
    static int[][] arr;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
//                if (arr[i][j] > 0) {
//                    sNum++;
//                }
            }
        }

        int year = 0;

        while (true) {

            year++;
            bfs();

            int c = countIceBerg();
            if (c > 1) {
                System.out.print(year);
                break;
            }
            else if (c == 0) {
                System.out.print(0);
                break;
            }
        }

        //System.out.println(0);
    }

    // 빙하 녹이는 함수
    static void bfs() {

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        // 모든 빙하의 좌표 큐에 삽입
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] != 0) {
                    q.offer(new int[] {i,j});
                    visited[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {

            int r = q.peek()[0];
            int c = q.poll()[1];
            int seaNum = 0;

            for (int i = 0; i < 4; i++) {
                int nr = r + dy[i];
                int nc = c + dx[i];
                if (0 > nr || nr >= n || 0 > nc || nc >= m) {
                    continue;
                }
                // 주변 위치가 바다일 경우
                if (!visited[nr][nc]) {
                    seaNum++;
                }
            }

            if (arr[r][c] <= seaNum) {
                arr[r][c] = 0;
            }
            else {
                arr[r][c] -= seaNum;
            }
        }


    }

    static int countIceBerg() {

        boolean[][] visited = new boolean[n][m];

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] != 0 && !visited[i][j]) {
                    dfs(i,j,visited);
                    count++;
                }
            }
        }

        return count;
    }

    // 분리된 빙하 개수 구하기
    static void dfs(int y, int x, boolean[][] visited) {

        visited[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int ny = y+dy[i];
            int nx = x+dx[i];
            if (0 <= ny && ny < n && 0 <= nx && nx < m && !visited[ny][nx] && arr[ny][nx] != 0) {
                dfs(ny,nx,visited);
            }
        }
    }
}
