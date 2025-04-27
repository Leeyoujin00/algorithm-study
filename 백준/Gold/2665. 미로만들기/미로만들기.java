import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static int n;
    static int[][] map;

    // 상하좌우
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main (String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.print(bfs());
    }

    /**
     * BFS 알고리즘으로 진행하되, 가장 적은 toWhiteNum 인 좌표가 우선순위를 갖는 우선순위 큐를 활용한다.
     */
    private static int bfs() {

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> Integer.compare(o1[2], o2[2]));
        pq.offer(new int[] {0,0,0});

        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;

        while(!pq.isEmpty()) {

            int[] cur = pq.poll();
            int x = cur[0], y = cur[1];
            int toWhiteNum = cur[2];

            if (x == n-1 && y == n-1) return toWhiteNum;

            for (int i = 0; i < 4; i++) {

                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) continue;

                // 만약 검은 방이라면
                if (map[nx][ny] == 0) {
                    pq.offer(new int[] {nx,ny,toWhiteNum+1});
                }
                else {
                    pq.offer(new int[] {nx,ny,toWhiteNum});
                }
                visited[nx][ny] = true;
            }
        }

        return 0;
    }
}
