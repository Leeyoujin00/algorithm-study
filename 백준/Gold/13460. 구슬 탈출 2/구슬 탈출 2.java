import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static char[][] map;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        int y1 = 0, x1 = 0, y2 = 0, x2 = 0;
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'R') {
                    y1 = i;
                    x1 = j;
                    map[i][j] = '.';
                }
                if (map[i][j] == 'B') {
                    y2 = i;
                    x2 = j;
                    map[i][j] = '.';
                }
            }
        }

        // int[] red = new int[]{y1,x1};
        // int[] blue = new int[] {y2,x2};
        dfs(y1,x1,y2,x2,0);

        int result = min == Integer.MAX_VALUE ? -1 : min;
        System.out.print(result);

    }
    private static void print(int y1, int x1, int y2, int x2) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == y1 && j == x1) System.out.print('R');
                else if (i == y2 && j == x2) System.out.print('B');
                else System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void dfs(int y1, int x1, int y2, int x2, int step) {

//        System.out.println("step = " + step + "=========");
//        print(y1,x1,y2,x2);

        if (10 < step) return;
        // 파란구멍이 구멍에 빠지면 실패
        if (map[y2][x2] == 'O') return;

        // 빨간구슬과 파란구슬이 동시에 구멍에 빠져도 실패
        //if (red[0] == blue[0] && red[1] == blue[1]) return;

        // 빨간 구슬이 구멍에 빠지면 성공
        if (map[y1][x1] == 'O') {
            min = Math.min(min, step);
            return;
        }

        // 상하좌우로 굴림
        for (int i = 0; i < 4; i++) {
            int[] red = new int[] {y1,x1};
            int[] blue = new int[] {y2,x2};
            if (isRedFirst(red, blue, i)) {
                roll(red, blue, i);
            }
            else {
                roll(blue, red, i);
            }
            // 만약 빨강과 파랑 모두 이동 X면 중단
            if ((red[0] == y1 && red[1] == x1) && (blue[0] == y2 && blue[1] == x2)) continue;
            dfs(red[0], red[1], blue[0], blue[1], step+1);

        }
    }

    // 구슬을 굴림
    private static void roll(int[] first, int[] second, int dir) {

        int y = first[0], x = first[1], i = 0;
        // first 구슬 먼저 이동
        while (true) {
            int ny = y + dy[dir]*i;
            int nx = x + dx[dir]*i;
            i++;
            // 보드 벗어나거나, 벽 있는 곳이면 이동 멈춤
            if (ny < 0 || ny >= n || nx < 0 || nx >= m || map[ny][nx] == '#') break;

            first[0] = ny;
            first[1] = nx;
            // 구멍 마주치면 이동 멈춤
            if (map[ny][nx] == 'O') break;
        }

        y = second[0]; x = second[1]; i = 0;
        // second 구슬 이동
        while (true) {
            int ny = y + dy[dir]*i;
            int nx = x + dx[dir]*i;
            i++;
            // 보드 벗어나거나, 벽 있는 곳이면 이동 멈춤
            if (ny < 0 || ny >= n || nx < 0 || nx >= m || map[ny][nx] == '#') break;

            // 구멍 마주치면 이동 멈춤
            if (map[ny][nx] == 'O') {
                second[0] = ny;
                second[1] = nx;
                break;
            }
            // 먼저 이동한 구슬 마주치면 이동 멈춤
            if (ny == first[0] && nx == first[1]) break;
            second[0] = ny;
            second[1] = nx;
        }

    }

    // dir 방향으로 먼저인 구슬 확인 -> true: 빨강 먼저, false : 파랑 먼저
    private static boolean isRedFirst(int[] red, int[] blue, int dir) {

        switch (dir) {
            case 0:
                return red[0] <= blue[0];
            case 1:
                return red[0] > blue[0];
            case 2:
                return red[1] <= blue[1];
            case 3:
                return red[1] > blue[1];
        }

        return false;
    }


}
