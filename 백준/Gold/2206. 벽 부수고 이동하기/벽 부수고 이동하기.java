import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static class Location {
        int i;
        int j;
        int cnt;
        boolean destroyed;

        public Location(int i, int j, int cnt, boolean destroyed) {
            this.i = i;
            this.j = j;
            this.cnt = cnt;
            this.destroyed = destroyed;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");

        int n = Integer.parseInt(inputs[0]);
        int m = Integer.parseInt(inputs[1]);

        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        int[] nx = {0, 0, -1, 1};
        int[] ny = {-1, 1, 0, 0};

        boolean[][][] visited = new boolean[n][m][2];

        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(0, 0, 1, false));

        while (!queue.isEmpty()) {
            Location now = queue.poll();
            int x = now.i;
            int y = now.j;

            if (x == n-1 && y == m-1) {
                System.out.print(now.cnt);
                return;
            }

            if (x < 0 || x >= n || y < 0 || y >= m) {
                continue;
            }

            if (map[x][y] == '0') { // 현재 위치가 벽이 아닐 경우
                if (!now.destroyed && !visited[x][y][0]) { // 이전에 벽을 뚫은 적이 없을 경우
                    // 방문 처리
                    visited[x][y][0] = true;
                    for (int i = 0; i < 4; i++) {
                        queue.add(new Location(x+nx[i], y+ny[i], now.cnt+1, false));
                    }
                }
                else if (now.destroyed && !visited[x][y][1]) { // 이전에 벽을 뚫은 적이 있을 경우
                    visited[x][y][1] = true;
                    for (int i = 0; i < 4; i++) {
                        queue.add(new Location(x+nx[i], y+ny[i], now.cnt+1, true));
                    }
                }
            }
            else if (map[x][y] == '1') { // 현재 위치가 벽일 경우
                // 이전에 벽을 뚫은 적이 없을 경우
                if (!now.destroyed) {
                    // 벽을 뚫는다.
                    visited[x][y][1] = true;
                    for (int i = 0; i < 4; i++) {
                        queue.add(new Location(x+nx[i], y+ny[i], now.cnt+1, true));
                    }
                }

                // 이전에 벽을 뚫은 적이 있다면 아무 처리도 할 수 없음
            }
        }
        System.out.print(-1);
    }
}
