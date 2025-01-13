import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class Main {
    static int n,m;
    static int[][] farm;
    static int[] dx = {1,-1,0,0,1,-1,1,-1};
    static int[] dy = {0,0,1,-1,-1,-1,1,1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        farm = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                farm[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[n][m];
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (farm[i][j] != 0 && !visited[i][j]) {
                    if (bfs(i,j,farm[i][j])) {
                        //System.out.println(i + " " + j + " " + farm[i][j]);
                        answer++;
                    }
                }
            }
        }

        System.out.println(answer);

    }

    // 인접한 위치의 높이 같은 격자들 모두 확인
    static boolean bfs(int startX, int startY, int h) {

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {startX,startY});
        boolean[][] v = new boolean[n][m];
        v[startX][startY] = true;

        List<int[]> adj = new ArrayList<>();
        adj.add(new int[] {startX, startY});

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            // 인접한 격자들 큐에 넣음
            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || v[nx][ny]) continue;

                v[nx][ny] = true;
                if (farm[nx][ny] == h) {
                    queue.offer(new int[] {nx,ny});
                    adj.add(new int[] {nx,ny});
                }
            }
        }

        // 인접한 격자들의 집합의 인접한 곳에 더 높은 위치 없는지 확인
        for (int[] pos : adj) {
            if (!check(pos[0], pos[1], h)) return false;
        }

        return true;
    }

    // 해당 위치가 봉우리인자 확인
    // 팔방에 더 높은 곳 있으면 봉우리 아님

    static boolean check(int x, int y, int h) {

        visited[x][y] = true;

        // 팔방 확인
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= n || ny < 0 || ny >= m || farm[nx][ny] == 0) {
                continue;
            }
            if (farm[nx][ny] > h) return false;
            //visited[nx][ny] = true;
        }

        return true;
    }
}
