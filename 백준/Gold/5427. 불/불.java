import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int tc, w, h;
    static Character[][] map;
    static int sx,sy;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static Queue<int[]> queue = new LinkedList<>();
    static int result = -1;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        tc = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < tc; testcase++) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            map = new Character[h][w];
            queue.clear();
            result = -1;
            for (int i = 0; i < h; i++) {
                String str = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i][j] = str.charAt(j);
                    if (map[i][j] == '*') {
                        queue.offer(new int[] {i,j});
                    }
                    else if (map[i][j] == '@') {
                        sx = i;
                        sy = j;
                    }
                }
            }
            bfs();
            if (result == -1) {
                System.out.println("IMPOSSIBLE");
            }
            else {
                System.out.println(result);
            }
        }
    }

    static void bfs() {

        int time = 0;
        List<int[]> nextList = new ArrayList<>();

        Queue<int[]> sQueue = new LinkedList<>();
        sQueue.offer(new int[] {sx, sy});
        List<int[]> nextSG = new ArrayList<>();

        while (true) {
            // 1초마다 반복
            time++;
            // 불이 옮겨붙는 과정 처리
            while (!queue.isEmpty()) {
                int x = queue.peek()[0];
                int y = queue.poll()[1];

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    // 탐색 위치를 벗어났거나, 해당 위치가 벽이거나 불이면 탐색 x
                    if (nx < 0 || h <= nx || ny < 0 || w <= ny || map[nx][ny] == '#' || map[nx][ny] == '*') {
                        continue;
                    }
                    // 빈 공간일 경우, 불이 옮겨 붙는다.
                    nextList.add(new int[] {nx,ny});
                    map[nx][ny] = '*';
//                    if (map[nx][ny] == '.') {
//                        nextList.add(new int[] {nx,ny});
//                        map[nx][ny] = '*';
//                    }
                    // 상근이일 경우
//                    else if (map[nx][ny] == '@') {
//
//                    }
                }
            }

            for (int i = 0; i < nextList.size(); i++) {
                queue.offer(nextList.get(i));
            }
            nextList.clear();

            //boolean flag = false;
            // 상근이 이동 수행
            while (!sQueue.isEmpty()) {
                int snx = sQueue.peek()[0];
                int sny = sQueue.poll()[1];

                for (int i = 0; i < 4; i++) {
                    int nextX = snx + dx[i];
                    int nextY = sny + dy[i];

                    // 탐색 위치를 벗어났다면 탈출 성공 의미
                    if (nextX < 0 || h <= nextX || nextY < 0 || w <= nextY) {
                        result = time;
                        return;
                    }

                    // 빈공간이고, 방문한 적 X라면 이동
                    if (map[nextX][nextY] == '.') {
                        // 다시 방문하지 않도록, 방문 표시
                        map[nextX][nextY] = ',';
                        nextSG.add(new int[] {nextX, nextY});
                    }
                }
            }
            // 상근이가 더 이상 이동할 수 없으므로 IMPOSSIBLE
            if (nextSG.size() == 0) {
                return;
            }
            for (int i = 0; i < nextSG.size(); i++) {
                sQueue.offer(nextSG.get(i));
            }
            nextSG.clear();

        }
    }
}
