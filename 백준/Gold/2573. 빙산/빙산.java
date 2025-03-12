import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
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
            }
        }


        int year = 0;
        while (true) {
            year++;
            // 빙하 녹임
            bfs();

            // 덩어리 수 1이면 계속 진행
            int iceberg = countIceberg();
            if (iceberg == 0) {
                System.out.print(0);
                break;
            }

            if (iceberg > 1) {
                System.out.print(year);
                break;
            }
        }

    }

    // 빙하 녹이는 함수
    private static void bfs() {

        boolean[][] visited = new boolean[n][m];
        Queue<int[]> que = new LinkedList<>(); // 빙하 좌표 저장
        // 빙하 있는 칸 표시
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] != 0) {
                    visited[i][j] = true;
                    que.offer(new int[] {i,j});
                }
            }
        }

        while(!que.isEmpty()) {

            int x = que.peek()[0];
            int y = que.poll()[1];

            int seaCount = 0;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 동서남북 방향의 바다 개수 count
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && arr[nx][ny] == 0 && !visited[nx][ny]) {
                    seaCount++;
                }
            }

            arr[x][y] -= seaCount;
            if (arr[x][y] < 0) arr[x][y] = 0;
        }

    }

    // 빙산 덩어리 개수 구하는 함수
    private static int countIceberg() {
        boolean[][] visited = new boolean[n][m];

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && arr[i][j] != 0) {
                    dfs(i,j,visited);
                    count++;
                }
            }
        }

        return count;
    }

    // 빙산 개수 구하기 위해 호출
    private static void dfs(int x, int y, boolean[][] visited) {
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                continue;
            }
            if (arr[nx][ny] != 0 && !visited[nx][ny]) {
                dfs(nx,ny,visited);
            }
        }
    }
}
