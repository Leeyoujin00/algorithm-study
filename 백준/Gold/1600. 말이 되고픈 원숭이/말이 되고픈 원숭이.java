import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int k, w, h;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int[] hx = {1,1,-1,-1,2,2,-2,-2};
    static int[] hy = {-2,2,-2,2,1,-1,1,-1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new int[h][w];

        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.print(bfs());

    }

    static int bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0,0,0,0));
        visited = new boolean[h][w][k+1];
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.x == h-1 && cur.y == w-1) return cur.step;

            int nx, ny, ns, nk;
            for (int i = 0; i < 4; i++) {
                nx = cur.x + dx[i];
                ny = cur.y + dy[i];
                ns = cur.step + 1;
                nk = cur.k;

                if (nx < 0 || nx >= h || ny < 0 || ny >= w || map[nx][ny] == 1) {
                    continue;
                }

                if (!visited[nx][ny][nk]) {
                    visited[nx][ny][nk] = true;
                    queue.offer(new Node(nx,ny,ns,nk));
                }
            }

            if (cur.k < k) {
                for (int i = 0; i < 8; i++) {
                    nx = cur.x + hx[i];
                    ny = cur.y + hy[i];
                    ns = cur.step + 1;
                    nk = cur.k + 1;

                    if (nx < 0 || nx >= h || ny < 0 || ny >= w || map[nx][ny] == 1) {
                        continue;
                    }

                    if (!visited[nx][ny][nk]) {
                        visited[nx][ny][nk] = true;
                        queue.offer(new Node(nx,ny,ns,nk));
                    }
                }
            }
        }

        return -1;

    }

    static class Node {
        int x;
        int y;
        int step;
        int k;

        public Node(int x, int y, int step, int k) {
            this.x = x;
            this.y = y;
            this.step = step;
            this.k = k;
        }
    }
}
