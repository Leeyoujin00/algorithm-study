import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int h = 0;
    static int[][] map;
    static int max = 0;
    // 상하좌우
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                h = Math.max(h, map[i][j]);
            }
        }

        for (int i = 0; i <= h; i++) {
            max = Math.max(max, solution(i));
        }

        System.out.print(max);
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int solution(int height) {

        int cnt = 0;
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && map[i][j] > height) {
                    bfs(i,j,height);
                    cnt++;
                }
            }
        }

        return cnt;
    }

    private static void bfs(int x, int y, int height) {

        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(x,y));
        visited[x][y] = true;

        while (!que.isEmpty()) {

            Node cur = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) continue;
                if (map[nx][ny] <= height) continue;

                que.offer(new Node(nx,ny));
                visited[nx][ny] = true;
            }
        }

    }
}
