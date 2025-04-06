import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n,m,h;
    static int[][][] arr;
    static int[] dh = {0,0,0,0,1,-1};
    static int[] dx = {1,-1,0,0,0,0};
    static int[] dy = {0,0,1,-1,0,0};
    static Queue<Tomato> que = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        arr = new int[h][n][m];

        int t = 0;
        for (int height = 0; height < h; height++) {
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    arr[height][i][j] = Integer.parseInt(st.nextToken());
                    // 익은 토마토들을 큐에 저장한다.
                    if (arr[height][i][j] == 1) {
                        que.offer(new Tomato(height, i, j));
                    }
                    if (arr[height][i][j] == 0) t++;
                }
            }
        }

        System.out.print(bfs(t));

    }

    private static class Tomato{
        int h;
        int x;
        int y;

        public Tomato(int h, int x, int y) {
            this.h = h;
            this.x = x;
            this.y = y;
        }
    }

    /**
     * 토마토의 상태가 상하좌우앞뒤 토마토의 영향을 받음
     */
    private static int bfs(int t) {

        boolean[][][] v = new boolean[h][n][m];

        int day = 0, cnt = 0;
        boolean flag = true;
        Queue<Tomato> tmp = new LinkedList<>();

        while (flag) {
            flag = false;
            //v = new boolean[h][n][m];
            while (!que.isEmpty()) {

                Tomato cur = que.poll();

                for (int i = 0; i < 6; i++) {
                    int nh = cur.h + dh[i];
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    // 탐색범위 벗어나면 진행 X
                    if (nh < 0 || nh >= h || nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                    // 이미 익은 토마토이거나 벽일 경우 전파 X, 이미 방문했을 경우 진행 X
                    if (arr[nh][nx][ny] == 1 || arr[nh][nx][ny] == -1 || v[nh][nx][ny]) continue;

                    v[nh][nx][ny] = true;
                    tmp.offer(new Tomato(nh,nx,ny));
                    flag = true;
                }
            }
            if (!flag) break;
            for (Tomato next : tmp) {
                que.offer(next);
                arr[next.h][next.x][next.y] = 1;
                cnt++;
            }
            tmp.clear();
            day++;
        }

        if (cnt != t) return -1;
        return day;
    }
}
