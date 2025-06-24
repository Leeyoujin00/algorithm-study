import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int m,n,k;
    static boolean[][] map;
    static boolean[][] visited;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new boolean[m][n];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            setSquare(x1,y1,x2,y2);
        }

        visited = new boolean[m][n];
        // 격자의 분리된 영역의 개수를 센다.
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 직사각형이 없는 칸이고, 아직 방문하지 않았다면 탐색 진행
                if (!map[i][j] && !visited[i][j]) {
                    result.add(bfs(i,j));
                }
            }
        }

        Collections.sort(result);
        System.out.println(result.size());
        for (int size : result) {
            System.out.print(size + " ");
        }
    }

    private static void setSquare(int x1, int y1, int x2, int y2) {
        // 직사각형 영역을 표시한다.

        for (int i = y1; i < y2; i++) {
            for (int j = x1; j < x2; j++) {
                map[i][j] = true;
            }
        }
    }

    private static int bfs(int row, int col) {
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] {row, col});
        visited[row][col] = true;

        int size = 1;
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int y = cur[0], x = cur[1];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny < 0 || ny >= m || nx < 0 || nx >= n || visited[ny][nx] || map[ny][nx]) {
                    continue;
                }

                visited[ny][nx] = true;
                que.offer(new int[] {ny, nx});
                size++;

            }
        }

        return size;
    }
}
