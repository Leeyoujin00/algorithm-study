import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m;
    static int[][] arr;
    static int[][] move;
    static int[] dx = {0,-1,-1,-1,0,1,1,1};
    static int[] dy = {-1,-1,0,1,1,1,0,-1};
    static Queue<Cloud> que = new LinkedList<>();
    // 대각선 방향으로 거리가 1인 칸
    static int[] diagonalX = {1,1,-1,-1};
    static int[] diagonalY = {1,-1,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][n];
        // 격자 입력
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 비바라기 시전
        que.offer(new Cloud(n-1,0));
        que.offer(new Cloud(n-1,1));
        que.offer(new Cloud(n-2,0));
        que.offer(new Cloud(n-2,1));

        // 이동정보 입력
        move = new int[m][2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            move[i][0] = Integer.parseInt(st.nextToken())-1;
            move[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < m; i++) {
            simulation(i);
        }

        // 이동이 모두 끝난 후, 바구니에 들어있는 물의 양의 합 구하기
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += arr[i][j];
            }
        }

        System.out.print(sum);
    }

    private static void simulation(int cnt) {

        List<Cloud> movedCloud = new ArrayList<>();
        boolean[][] visited = new boolean[n][n];

        // 1. 구름 이동
        while(!que.isEmpty()) {
            Cloud cur = que.poll();

            int d = move[cnt][0];
            int s = move[cnt][1];

            // 구름 이동
            int nx = cur.x + s * dx[d];
            int ny = cur.y + s * dy[d];
            if (nx >= n) {
                nx %= n;
            }
            else if (nx < 0) {
                nx = (n - (Math.abs(nx) % n)) % n;
            }
            if (ny >= n) {
                ny %= n;
            }
            else if (ny < 0) {
                ny = (n - (Math.abs(ny) % n)) % n;
            }

            cur.x = nx;
            cur.y = ny;

            // 이동한 구름의 좌표 저장
            if (!visited[cur.x][cur.y]) {
                movedCloud.add(cur);
                visited[cur.x][cur.y] = true; // 현재 이동에서 구름 있는 위치 저장
            }
        }

        // 2. 구름 있는 칸 물 양 1 증가
        for (Cloud c : movedCloud) {
            arr[c.x][c.y] += 1;
        }

        // 3. 구름 사라짐

        // 4. 물이 증가한 칸(구름 있던 칸) 물복사버그 마법
        // 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 물의 양 증가
        // 경계를 넘어가는 칸은 대각선 방향으로 거리가 1인 칸 아님
        for (int i = 0; i < movedCloud.size(); i++) {
            int plus = 0;
            int curX = movedCloud.get(i).x;
            int curY = movedCloud.get(i).y;
            // 해당 구름의 대각선 위치 물 양 검사
            for (int j = 0; j < 4; j++) {
                int nextX = curX + diagonalX[j];
                int nextY = curY + diagonalY[j];
                // 경계 넘어가면 진행 x
                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n) {
                    continue;
                }
                if (arr[nextX][nextY] >= 1) {
                    plus++;
                }
            }
            arr[curX][curY] += plus;
        }

        // 5. 바구니에 저장된 물의 양이 2이상인 모든 칸에 구름이 생기고,
        // 물의 양이 2 줄어든다.
        // 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 함
        // visited로 방문 내용 기록
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] >= 2) {
                    if (!visited[i][j]) {
                        arr[i][j] -= 2;
                        que.offer(new Cloud(i,j));
                        //visited[i][j] = true;
                    }
                }
            }
        }


    }

    private static class Cloud {
        int x;
        int y;

        public Cloud(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
