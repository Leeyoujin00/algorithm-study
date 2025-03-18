import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n,m,k;
    static int[][] map;
    static int[][] dice; // 주사위 전개도 표현
    static int posX = 0; // 현재 x좌표
    static int posY = 0;  // 현재 y좌표
    // 동남서북 순서
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static int degree = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 주사위 값 초기화
        dice = new int[4][3];
        dice[0][1] = 2;
        dice[1][0] = 4;
        dice[1][1] = 1;
        dice[1][2] = 3;
        dice[2][1] = 5;
        dice[3][1] = 6;

        int ans = 0;
        for (int i = 0; i < k; i++) {
            ans += simulation();
        }

        System.out.print(ans);
    }

    // 지도의 오른쪽은 동쪽, 위쪽은 북쪽
    public static int simulation() {


        // 1. 주사위가 이동 방향으로 한 칸 굴러간다.
        rotate();

        // 2. 주사위가 도착한 칸에 대한 점수를 획득한다.
        int score = map[posX][posY];

        // 3. 주사위가 아랫면에 있는 정수 A와 주사위가 있는 칸에 있는 정수 B를
        //    비교해 이동 방향을 결정
        int a = dice[3][1];
        int b = map[posX][posY];

        //System.out.println("아랫면 = " + a);
        if (a > b) {
            // 이동 방향을 90도 시계 방향으로 회전
            degree = (degree+1) % 4;
        }
        else if (a < b) {
            // 이동 방향을 90도 반시계 방향으로 회전
            degree = (degree-1+4) % 4;
        }

        // 3. 동서남북 방향으로 연속해서 이동할 수 있는 칸의 수를 구함
        //System.out.println("좌표 = " + posX + " " + posY);
        int c = bfs(posX, posY);
        //System.out.println(c);

        return score * c;
    }


    // 주사위를 회전시킨다.
    public static void rotate() {

        // 이동방향에 칸이 없다면, 이동방향 반대로 한다음 굴러간다.
        posX += dx[degree];
        posY += dy[degree];
        // 이동방향에 칸이 없다면, 이동방향 반대로 한다음 굴러간다.
        if (posX < 0 || posX >= n || posY < 0 || posY >= m) {
            posX -= dx[degree];
            posY -= dy[degree];
            degree = (degree+2) % 4;
            posX += dx[degree];
            posY += dy[degree];
        }

        int tmp = 0;
        // 0,1,2,3 = 동,남,서,북
        switch (degree) {
            // 동쪽으로 회전
            case 0:
                tmp = dice[1][2];
                dice[1][2] = dice[1][1];
                dice[1][1] = dice[1][0];
                dice[1][0] = dice[3][1];
                dice[3][1] = tmp;
                break;
            // 남쪽 회전
            case 1:
                tmp = dice[3][1];
                dice[3][1] = dice[2][1];
                dice[2][1] = dice[1][1];
                dice[1][1] = dice[0][1];
                dice[0][1] = tmp;
                break;
            // 서쪽 회전
            case 2:
                tmp = dice[1][0];
                dice[1][0] = dice[1][1];
                dice[1][1] = dice[1][2];
                dice[1][2] = dice[3][1];
                dice[3][1] = tmp;
                break;
            // 북쪽 회전
            case 3:
                tmp = dice[0][1];
                dice[0][1] = dice[1][1];
                dice[1][1] = dice[2][1];
                dice[2][1] = dice[3][1];
                dice[3][1] = tmp;
                break;
        }

    }

    private static int bfs(int startX, int startY) {

        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] {startX, startY});
        boolean[][] visited = new boolean[n][m];
        visited[startX][startY] = true;

        int num = map[startX][startY];
        int count = 1;

        while (!que.isEmpty()) {

            int[] cur = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny] || map[nx][ny] != num) {
                    continue;
                }

                count++;
                que.offer(new int[] {nx,ny});
                visited[nx][ny] = true;
            }
        }

        return count;
    }

}
