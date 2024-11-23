import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] possibility;
    static int[] selected;
    static boolean[][] visited;
    static int[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static double result = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        possibility = new int[4];

        for (int i = 0; i < 4; i++) {
            possibility[i] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[29][29];
        selected = new int[n];
        visited[14][14] = true;
        dfs(14,14,0,selected);

        System.out.println(result);

    }

    static void dfs(int x, int y, int r, int[] selected) {

        if (r == n) {
            calculate(selected);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (0 <= nx && nx < 29 && 0 <= ny && ny < 29 && !visited[nx][ny] && possibility[i] > 0) {
                visited[nx][ny] = true;
                selected[r] = i; // 0/1/2/3 (동/서/남/북) 중 방향 선택
                dfs(nx, ny, r+1, selected);
                visited[nx][ny] = false;
            }
        }
    }

    static void calculate(int[] selected) {
        double p = 1;

        for (int i = 0; i < n; i++) {
            //System.out.print(selected[i] + " ");
            p *= (double)possibility[selected[i]] / 100;
        }
        //System.out.println();

        result += p;
    }
}
