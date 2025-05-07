import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n,m,k;
    static int[][] map;
    static int[] dx = {0,0,1,-1,0};
    static int[] dy = {1,-1,0,0,0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(Character.toString(str.charAt(j)));
            }
        }

        System.out.print(bfs());
    }

    private static class Node {

        int x;
        int y;
        int step;
        int k;
        boolean isNoon;

        public Node(int x, int y, int step, int k, boolean isNoon) {
            this.x = x;
            this.y = y;
            this.step = step;
            this.k = k;
            this.isNoon = isNoon;
        }
    }

    // 한번 이동마다 낮밤이 바뀜
    // 이동 시기에 가만히 있는 것도 가능함
    // 벽을 k개까지 부수고 이동해도 됨 - 단, 벽은 낮에만 부술 수 있음
    private static int bfs() {

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0,0,1,0,true));
        boolean[][][] visited = new boolean[n][m][k+1];
        visited[0][0][0] = true;
        
        if (n == 1 && m == 1) return 1;

        while (!q.isEmpty()) {
            Node cur = q.poll();
//            boolean next = cur.isNoon;
//            if (!next) {
//                System.out.print("밤");
//            }
//            else System.out.print("낮");
//            System.out.println(cur.x + " " + cur.y + " " + cur.step + ", k =" + cur.k);

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                if (nx == n-1 && ny == m-1) {
                    return cur.step + 1;
                }
                // 만약 이동 위치가 벽이 아니라면
                if (map[nx][ny] == 0) {
                    if (visited[nx][ny][cur.k]) continue;
                    q.offer(new Node(nx,ny,cur.step+1,cur.k,!cur.isNoon));
                    visited[nx][ny][cur.k] = true;
                    continue;
                }
                // 이동 위치가 벽이라면
                // -> 현재 낮일 경우
                if (cur.isNoon) {
                    // 가만히 있을 수 없다. (이동거리만 늘어나기 때문에)
                    if (i == 4) continue;

                    // 벽을 부술 수 있다.
                    if (cur.k < k) {
                        if (visited[nx][ny][cur.k+1]) continue;
                        q.offer(new Node(nx,ny,cur.step+1,cur.k+1,!cur.isNoon));
                        visited[nx][ny][cur.k+1] = true;
                    }


                } else {
                    // 가만히 있을 수 있다.
                    if (cur.k < k) {
                        //System.out.println(nx + "," + ny + " : 밤, 기다림 " + cur.step+1);
                        q.offer(new Node(cur.x,cur.y,cur.step+1,cur.k,!cur.isNoon));
                    }
                }

            }
        }

        return -1;
    }
}
