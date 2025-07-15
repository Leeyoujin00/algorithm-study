import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m;
    static int[][] map;
    // 이동방향, 상하좌우
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int result = 0;
    //static boolean[][] ck;
    static int s = 1;
    static int[][] ck;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                char c = line.charAt(j);
                if (c == 'U') map[i][j] = 0;
                else if (c == 'D') map[i][j] = 1;
                else if (c == 'L') map[i][j] = 2;
                else if (c == 'R') map[i][j] = 3;
            }
        }

        ck = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (ck[i][j] == 0) { // 방문한 적 없는 경로
                    bfs(i,j);
                    s++;
                }
            }
        }

        System.out.print(result);
    }

    private static class Node {
        int x;
        int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 순환 경로 내 한 위치을 SAFE ZONE 으로 정함
    private static void bfs(int x, int y) {

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x,y));
        ck[x][y] = s;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            int dir = map[cur.x][cur.y];

            int nx = cur.x + dx[dir];
            int ny = cur.y + dy[dir];

            // 이동 위치가 지도를 벗어나는 곳이라면, 현재 위치는 안전구역으로 지정해야함
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                break;
            }
            // 안전구역 생긴 경로로 들어가게 되므로, 새로운 안전경로를 만들 필요 없음
            if (ck[nx][ny] != 0 && ck[nx][ny] < s) {
                return;
            }
            else if (ck[nx][ny] == s) { // 순환 발생한 경로 발견
                break;
            }
            ck[nx][ny] = s;
            q.offer(new Node(nx,ny));
        }

        result++;
    }

}
