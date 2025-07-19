import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] map;
    static int INF = 125 * 125 * 10;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        int t = 0;
        while (true) {
            t++;
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;

            map = new int[n][n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append("Problem " + t  + ": "+ bfs(n)+"\n");
        }

        System.out.println(sb);
    }

    static class Node {
        int x;
        int y;
        int cost;
        public Node (int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    static int bfs(int n) {
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(0,0, map[0][0]));
        // 각 위치에 도달하기 위한 cost가 최소여야 함.
        int[][] ck = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(ck[i], INF);
        }
        ck[0][0] = map[0][0];

        while (!que.isEmpty()) {
            Node cur = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }
                int nc = cur.cost + map[nx][ny];
                // 이미 더 적은 cost로 해당 위치 방문했다면 진행 X
                if (ck[nx][ny] <= nc) {
                    continue;
                }

                que.offer(new Node(nx,ny,nc));
                ck[nx][ny] = nc;
            }
        }

        return ck[n-1][n-1];
    }
}
