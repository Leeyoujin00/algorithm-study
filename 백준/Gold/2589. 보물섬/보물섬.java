import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int col, row;
    static Character[][] arr;
    static int[] dy = {0,0,1,-1};
    static int[] dx = {1,-1,0,0};
    static int max;
    static int result = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        arr = new Character[row][col];

        for (int i = 0; i < row; i++) {
            String s = br.readLine();
            for (int j = 0; j < col; j++) {
                arr[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (arr[i][j] == 'L') {
                    bfs(i,j);
                }
            }
        }

        System.out.print(result);

    }

    static void bfs(int r, int c) {

        //visited = new boolean[row][col];
        //visited[r][c] = true;
        int[][] distance = new int[row][col];
        distance[r][c] = -1;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {r,c,0});

        max = 0;

        while (!queue.isEmpty()) {
            int y = queue.peek()[0];
            int x = queue.peek()[1];
            int d = queue.poll()[2];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (0 > ny || row <= ny || 0 > nx || col <= nx || distance[ny][nx] != 0 || arr[ny][nx] == 'W') {
                    continue;
                }
                //visited[ny][nx] = true;
                distance[ny][nx] = d+1;
                queue.add(new int[] {ny,nx,d+1});
                max = Math.max(max, d+1);
            }

        }

        result = Math.max(result,max);

    }
}
