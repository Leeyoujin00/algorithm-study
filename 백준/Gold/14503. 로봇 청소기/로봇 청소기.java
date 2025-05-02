import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static int[][] map;
    // 북 동 남 서 (상 우 하 좌)
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static boolean[][] v;
    static int r,c,d;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        v = new boolean[n][m];

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean flag = true;
        while (flag) {
            flag = bfs();
//            System.out.println("=============");
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < m; j++) {
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println();
//            }
        }

        System.out.print(cnt);

    }

    private static boolean bfs() {

        // 1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
        if (!v[r][c]) {
            v[r][c] = true;
            cnt++;
            map[r][c] = 2;
        }

        boolean go = canGo(r,c);
        // 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
        if (!go) {
            // 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고
            // 1번으로 돌아간다.
            int nx = r - dx[d];
            int ny = c - dy[d];
            if (nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] == 1) return false;

            r = nx;
            c = ny;
            return true;
        } else {

            // 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
            // 반시계 방향으로 90도 회전한다.
            d--;
            if (d < 0) d = 3;
            int nx = r + dx[d];
            int ny = c + dy[d];
            if (nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] == 1 || v[nx][ny]) return true;

            // 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈칸인 경우 한칸 전진한다.
            r = nx;
            c = ny;
            return true;
        }

        
    }

    // 현재 칸의 주변 네 칸중 청소되지 않은 빈 칸 있는지 확인
    private static boolean canGo(int r, int c) {

        for (int i = 0; i < 4; i++) {
            int nx = r + dx[i];
            int ny = c + dy[i];
            if (nx < 0 || nx >= n || ny < 0 || ny >= m || v[nx][ny] || map[nx][ny] == 1) continue;
            return true;
        }
        return false;
    }
}
