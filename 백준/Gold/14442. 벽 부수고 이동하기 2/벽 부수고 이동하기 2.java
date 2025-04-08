import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    static int n,m,k;
    static int[][] arr;
    static boolean[][][] dp;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        dp = new boolean[n][m][k+1];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }

        System.out.print(bfs());
    }

    private static class Node {
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

    private static int bfs() {

        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(0,0,1,0));
        dp[0][0][0] = true;

        while(!que.isEmpty()) {
            Node cur = que.poll();
            if (cur.x == n-1 && cur.y == m-1) return cur.step;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int nStep = cur.step+1;
                int nk = cur.k;

                // 탐색범위 벗어나면 진행 x
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                // 빈칸일 경우
                if (arr[nx][ny] == 0) {
                    if (!dp[nx][ny][nk]) {
                        que.offer(new Node(nx,ny,nStep,nk));
                        dp[nx][ny][nk] = true;
                    }
                }
                // 벽이고, 부술 횟수가 남아있는 경우
                else if (nk < k) {
                    if (!dp[nx][ny][nk+1]) {
                        que.offer(new Node(nx,ny,nStep,nk+1));
                        dp[nx][ny][nk+1] = true;
                    }
                }

            }
        }

        return -1;
    }

}
