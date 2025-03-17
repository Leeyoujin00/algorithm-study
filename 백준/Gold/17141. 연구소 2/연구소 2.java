import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] map;
    static List<int[]> virus = new ArrayList<>();
    static int n,m;
    static int min = Integer.MAX_VALUE;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int cntEmpty = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    virus.add(new int[] {i,j});
                }
                if (map[i][j] == 1) cntEmpty++;
            }
        }

        int[] selected = new int[m];
        select(0,0,selected);

        if (min == 2500) System.out.print(-1);
        else System.out.print(min);
    }

    // 바이러스 선택
    private static void select(int start, int r, int[] selected) {
        // m 개 선택 완료
        if (r == m) {
            int ans = bfs(getMap(), selected);
            //System.out.println("부분 해 = " + ans);
            min = Math.min(min, ans);
            return;
        }

        for (int i = start; i < virus.size(); i++) {
            selected[r] = i;
            select(i+1, r+1, selected);
        }
    }

    // 최소시간 구하는 과정의 map 초기화
    private static int[][] getMap() {
        int[][] copyMap = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 벽 표시
                if (map[i][j] == 1) {
                    copyMap[i][j] = -1;
                }
                //if (map[i][j] == 2) map[i][j] = 0;
                else copyMap[i][j] = 2500;
            }
        }

        return copyMap;
    }


    // 바이러스가 퍼지는 최소시간 구함
    private static int bfs(int[][] map, int[] selected) {

        Queue<int[]> que = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];

        for (int idx : selected) {
            int x = virus.get(idx)[0];
            int y = virus.get(idx)[1];
            que.offer(new int[] {x, y, 0});
            visited[x][y] = true;
            map[x][y] = 0;
        }

        // 바이러스 전파를 진행한다.
        while (!que.isEmpty()) {
            int[] cur = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                int nStep = cur[2] + 1;

                // 탐색 범위 벗어났거나, 이미 방문했거나, 벽일 경우 진행 x
                if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny] || map[nx][ny] == -1) {
                    continue;
                }

                if (map[nx][ny] > nStep) {
                    map[nx][ny] = nStep;
                    que.offer(new int[] {nx, ny, nStep});
                    visited[nx][ny] = true;
                }
            }
        }

        int time = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //System.out.print(map[i][j] + " ");
                // 전파되지 않은 칸 있음
                if (map[i][j] == 2500) {
                    return 2500;
                }
                time = Math.max(time, map[i][j]);
            }
            //System.out.println();
        }


        return time;
    }
}
