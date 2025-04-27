import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static int[][] map;
    // 상우하좌
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static List<Cctv> cctvList = new ArrayList<>();
    static int cnt = 0;
    static int min = 100;

    static class Cctv {
        int x;
        int y;
        int type; //
        //int dir; // 회전 후 방향 (상/하/좌/우)

        public Cctv(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // cctv 정보 저장
                if (0 < map[i][j] && map[i][j] < 6) {
                    cctvList.add(new Cctv(i,j,map[i][j]));
                    cnt++;
                }
            }
        }

        int[] directions = new int[cnt];
        rotate(directions, 0);

        System.out.print(min);
    }

    // cctv들의 방향을 설정한다.
    private static void rotate(int[] directions, int r) {
        // 모든 cctv들의 방향 설정을 마쳤다면
        if (r == cnt) {
            min = Math.min(min, calculate(directions));
            return;
        }

        for (int i = 0; i < 4; i++) {
            directions[r] = i;
            rotate(directions, r+1);
        }
    }

    // cctv 사각지대를 계산한다.
    private static int calculate(int[] directions) {

        // 원본 배열을 복사해서 사용한다.
        int[][] cpMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cpMap[i][j] = map[i][j];
            }
        }

        boolean[][] v = new boolean[n][m];
        for (int i = 0; i < cnt; i++) {
            see(cctvList.get(i), directions[i], cpMap, v);
        }

        // 사각지대의 개수를 구한다.
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0 && !v[i][j]) {
                    count++;
                }
            }
        }

        return count;
    }

    private static void see(Cctv cctv, int dir, int[][] cpMap, boolean[][] v) {

        if (cctv.type == 1) {
            // dir 한 방향으로만 감시
            dfs(cctv.x, cctv.y, dir, cpMap, v);
        }
        if (cctv.type == 2) {
            // dir 방향, 반대 방향으로 감시
            dfs(cctv.x, cctv.y, dir, cpMap, v);
            dfs(cctv.x, cctv.y, (dir+2)%4, cpMap, v);
        }
        if (cctv.type == 3) {
            // dir 방향, 90도 한칸 간 방향으로 감시
            dfs(cctv.x, cctv.y, dir, cpMap, v);
            dfs(cctv.x, cctv.y, (dir+1)%4, cpMap, v);
        }
        if (cctv.type == 4) {
            // 세방향으로 감시
            dfs(cctv.x, cctv.y, dir, cpMap, v);
            dfs(cctv.x, cctv.y, (dir+1)%4, cpMap, v);
            dfs(cctv.x, cctv.y, (dir+2)%4, cpMap, v);
        }
        if (cctv.type == 5) {
            // 모든 방향으로 감시
            dfs(cctv.x, cctv.y, dir, cpMap, v);
            dfs(cctv.x, cctv.y, (dir+1)%4, cpMap, v);
            dfs(cctv.x, cctv.y, (dir+2)%4, cpMap, v);
            dfs(cctv.x, cctv.y, (dir+3)%4, cpMap, v);
        }
    }

    private static void dfs(int x, int y, int dir, int[][] cpMap, boolean[][] v) {
        // 배열 범위 벗어났다면 진행 x
        if (x < 0 || x >= n || y < 0 || y >= m) {
            return;
        }
        // 벽을 만났다면 더 진행 x
        if (cpMap[x][y] == 6) {
            return;
        }

        if (cpMap[x][y] == 0) {
            v[x][y] = true;
        }

        dfs(x+dx[dir], y+dy[dir], dir, cpMap, v);
    }
}
