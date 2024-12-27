import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int k;
    static int[][] map;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    //static boolean[][] visited;
    static int count = 0;
    static int move = 0;
    //static int empty = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        k = Integer.parseInt(st.nextToken());
        map = new int[5][5];

        for (int idx = 0; idx < k; idx++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            map[i-1][j-1] = 1; // 사과나무가 없는 칸 저장
        }

        boolean[][] visited = new boolean[5][5];
        visited[0][0] = true;
        dfs(0,0,visited);
        System.out.println(count);

    }

    static void dfs(int x, int y, boolean[][] visited) {
        if (x == 4 && y == 4 && move == 24-k) {
            count++;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || map[nx][ny] == 1 || visited[nx][ny]) {
                continue;
            }

            move++;
            visited[nx][ny] = true;
            dfs(nx,ny,visited);
            move--;
            visited[nx][ny] = false;
        }
    }
}
