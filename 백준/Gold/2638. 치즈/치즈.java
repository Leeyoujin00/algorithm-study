import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n,m;
    static int[][] map;
    static int cheese = 0;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
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
                if (map[i][j] == 1) cheese++;
            }
        }

        int cnt = 0;
        while (cheese != 0) {
            bfs();
            cnt++;
        }

        System.out.println(cnt);

    }

    static void bfs() {

        Queue<int[]> queue = new LinkedList<>();
        int[][] check = new int[n][m];
        queue.offer(new int[] {0,0});
        List<int[]> airPos = new ArrayList<>();
        airPos.add(new int[] {0,0});
        boolean[][] visited = new boolean[n][m];

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny] || map[nx][ny] == 1) {
                    continue;
                }

                queue.offer(new int[] {nx,ny});
                visited[nx][ny] = true;
                airPos.add(new int[] {nx,ny});
            }
        }

        for (int[] pos : airPos) {
            queue.offer(pos);
        }

        List<int[]> delete = new ArrayList<>();
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                // 인접한 곳이 치즈이면 count 증가
                if (map[nx][ny] == 1) {
                    check[nx][ny]++;
                    if (check[nx][ny] == 2) {
                        delete.add(new int[] {nx,ny});
                    }
                }
            }
        }

        for (int[] pos : delete) {
            map[pos[0]][pos[1]] = 0;
            cheese--;
        }
    }
}
