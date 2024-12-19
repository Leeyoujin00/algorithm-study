import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n,h,d;
    static Character[][] arr;
    static boolean[][] visited;
    static int min = Integer.MAX_VALUE;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        arr = new Character[n][n];
        visited = new boolean[n][n];

        int r = 0;
        int c = 0;
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = s.charAt(j);
                if (arr[i][j] == 'S') {
                    r = i;
                    c = j;
                }
            }
        }
        bfs(r,c);
        int result = min == Integer.MAX_VALUE ? -1 : min;
        System.out.println(result);

    }

    static class Node {
        int r;
        int c;
        int hp;
        int up;
        int move;

        public Node(int r, int c, int hp, int up, int move) {
            this.r = r;
            this.c = c;
            this.hp = hp;
            this.up = up;
            this.move = move;
        }
    }

    // 최단 경로이므로, bfs 진행
    public static void bfs(int r, int c) {

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(r,c,h,0,0));
        int[][] point = new int[n][n];

        while (!queue.isEmpty()) {

            Node cur = queue.poll();

            for (int i = 0; i < 4; i++) {

                int nr = cur.r + dx[i];
                int nc = cur.c + dy[i];
                int nHp = cur.hp;
                int nUp = cur.up;
                int nMove = cur.move;

                if (nr < 0 || n <= nr || nc < 0 || n <= nc) {
                    continue;
                }
                if (nHp == 0) {
                    continue;
                }

                // 이동할 곳이 우산이라면, up 갱신
                if (arr[nr][nc] == 'U') {
                    nUp = d;
                }
                // 이동할 곳이 안전지대라면, min 갱신
                if (arr[nr][nc] == 'E') {
                    min = Math.min(min, nMove+1);
                    continue;
                }
                // 빈칸이라면
                else {
                    if (nUp <= 0) {
                        nHp--;
                    }
                    else {
                        nUp--;
                    }
                }

                // 방문 처리
                if (point[nr][nc] < nHp+nUp) {
                    point[nr][nc] = nHp+nUp;
                    queue.offer(new Node(nr,nc,nHp,nUp,nMove+1));
                }
            }

        }

    }

}
