import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static int[][] map;
    static int[] dx = {0,0,1,-1,1,-1,-1,1};
    static int[] dy = {1,-1,0,0,-1,1,-1,1};
    static boolean[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        v = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 0 && !v[i][j] && countMountains(i,j)) {
                    count++;
                    bfs(i,j);
                }
            }
        }

        System.out.print(count);
    }

    // 산봉우리의 개수를 센다.
    // 산봉우리는 팔방의 격자들보다 높아야 한다.
    // 팔방에 더 높은 격자 없으면 산봉우리임.
    // bfs 활용
    private static boolean countMountains(int x, int y) {

        boolean[][] visited = new boolean[n][m];
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] {x,y});
        visited[x][y] = true;

        int h = map[x][y];
        while(!que.isEmpty()) {
            int[] cur = que.poll();

            for (int i = 0; i < 8; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny]) {
                    continue;
                }

                // 인접한 격자 중 자신보다 높은 것 있으면 산봉우리 아니므로 false 반환
                if (map[nx][ny] > h) {
                    return false;
                }

                // 인접한 격자 중 자신과 높이 같은 것 있으면 산봉우리에 포함
                if (map[nx][ny] == h) {
                    que.offer(new int[] {nx,ny});
                    visited[nx][ny] = true;
                }
            }

        }

        return true;
    }

    private static void bfs(int x, int y) {
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[]{x, y});
        visited[x][y] = true;
        v[x][y] = true;

        int h = map[x][y];
        while (!que.isEmpty()) {
            int[] cur = que.poll();

            for (int i = 0; i < 8; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny]) {
                    continue;
                }

                // 인접한 격자 중 자신과 높이 같은 것 있으면 산봉우리에 포함
                if (map[nx][ny] == h) {
                    que.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    v[nx][ny] = true;
                }
            }
        }
    }
}
