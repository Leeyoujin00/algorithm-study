import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] map;
    static int n;
    static int m;
    static List<int[]> empty; // 빈칸의 좌표 저장
    static boolean[] visited;
    static List<int[]> virus; // 바이러스의 좌표 저장
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int zeroNum = 0;
    static int max = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        empty = new ArrayList<>();
        virus = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    empty.add(new int[] {i,j});
                    zeroNum++;
                }
                else if (map[i][j] == 2) {
                    virus.add(new int[] {i,j});
                }
            }
        }


        visited = new boolean[empty.size()];
        int[] selected = new int[3];

        //System.out.println("처음: " + zeroNum);
        combination(0, 0, selected);
        System.out.print(max);
    }

    static void combination(int start, int r, int[] selected) {
        if (r == 3) {
            // 선택된 위치에 벽 세우기
            for (int i = 0; i < 3; i++) {
                map[empty.get(selected[i])[0]][empty.get(selected[i])[1]] = 1;
            }
            bfs(); // 바이러스 퍼짐
            // 원래대로 빈칸 처리
            for (int i = 0; i < 3; i++) {
                map[empty.get(selected[i])[0]][empty.get(selected[i])[1]] = 0;
            }
            return;
        }

        for (int i = start; i < empty.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                selected[r] = i;
                combination(i+1, r+1, selected);
                visited[i] = false;
            }
        }
    }

    // 바이러스 퍼짐 처리
    static void bfs() {

        Queue<int[]> que = new LinkedList<>();
        for (int i = 0; i < virus.size(); i++) {
            que.offer(virus.get(i));
        }
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = map[i][j];
            }
        }
        //System.out.println(que.size());
        //queue.offer(new int[] {x, y});

        boolean[][] visited = new boolean[n][m];
        int num = 0;

        while (!que.isEmpty()) {
            int[] now = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                // 탐색 위치가 배열 범위 안
                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if (!visited[nx][ny] && arr[nx][ny] == 0) {
                        arr[nx][ny] = 2;
                        num++;
                        //System.out.println(num);
                        visited[nx][ny] = true;
                        que.offer(new int[] {nx, ny});
                    }
                }
            }
        }

        //System.out.println(zeroNum - num);
        max = Math.max(max, zeroNum - num - 3);

    }
}
