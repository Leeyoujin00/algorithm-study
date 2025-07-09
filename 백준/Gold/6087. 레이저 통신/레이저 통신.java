import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int w, h, sx, sy;
    static char[][] map;
    // 상 우 하 좌
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int minMirror = Integer.MAX_VALUE;
    static int[][] mirrorCnt;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new char[h][w];

        int cx = 0, cy = 0;
        boolean flag = false;
        for (int i = 0; i < h; i++) {
            String line = br.readLine();
            for (int j = 0; j < w; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'C') {
                    if (!flag) {
                        cx = i;
                        cy = j;
                        flag = true;
                    }
                    else {
                        sx = i;
                        sy = j;
                    }
                }
            }
        }

//        mirrorCnt = new int[h][w];
//        for (int i = 0; i < h; i++) {
//            Arrays.fill(mirrorCnt[i], Integer.MAX_VALUE);
//        }
//        mirrorCnt[cx][cy] = 0;

        int[][][] ck = new int[h][w][4];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                Arrays.fill(ck[i][j], Integer.MAX_VALUE);
            }
        }

        for (int i = 0; i < 4; i++) {
            ck[cx][cy][i] = 0;
            dfs(cx, cy, i, 0, ck);
        }

        System.out.print(minMirror);

    }

    // 모든 경로를 탐색하되, 꺾은 횟수 저장
    // 이동 가능한 방향 : 이전 방향 그대로 / 반시계 90도 회전 / 시계 90도 회전
    // 이전에 방문한 곳이라도, 현재 방향에 따라 재방문해야 함
    private static void dfs(int x, int y, int dir, int m, int[][][] ck) {

        // 목적지 도달하면 종료
        if (x == sx && y == sy) {
            minMirror = Math.min(minMirror, m);
            return;
        }

        // 이전 방향 그대로
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        int nd = dir;
        // 탐색범위 내이고, 벽 아님
        if (0 <= nx && nx < h && 0 <= ny && ny < w && map[nx][ny] != '*') {
            if (m < ck[nx][ny][nd]) {
                ck[nx][ny][nd] = m;
                dfs(nx, ny, nd, m, ck);
            }
        }

        // 반시계 90도
        nd = (((dir-1)%4)+4)%4;
        nx = x + dx[dir];
        ny = y + dy[dir];
        if (0 <= nx && nx < h && 0 <= ny && ny < w && map[nx][ny] != '*') {
            if (m+1 < ck[nx][ny][nd]) {
                ck[nx][ny][nd] = m+1;
                dfs(nx, ny, nd, m+1, ck);
            }
        }

        nd = (dir+1) % 4;
        nx = x + dx[dir];
        ny = y + dy[dir];
        // 시계 90도
        if (0 <= nx && nx < h && 0 <= ny && ny < w && map[nx][ny] != '*') {
            if (m+1 < ck[nx][ny][nd]) {
                ck[nx][ny][nd] = m+1;
                dfs(nx, ny, nd, m+1, ck);
            }
        }
    }
}
