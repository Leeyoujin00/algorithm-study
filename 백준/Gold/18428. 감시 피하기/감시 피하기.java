import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static char[][] map;
    //static boolean[][] visited;
    static List<Integer> empty;
    static List<int[]> teacher = new LinkedList<>();
    static int k = 0;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static boolean flag;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        //visited = new boolean[n][n];
        empty = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = st.nextToken().charAt(0);
                if (map[i][j] == 'X') {
                    empty.add(i*n + j%n);
                    k++;
                }
                else if (map[i][j] == 'T') {
                    teacher.add(new int[] {i,j});
                    //visited[i][j] = true;
                }
            }
        }

        int[] selected = new int[3];
        backtracking(0,0, selected);

        System.out.print("NO");
    }

    // 3개의 자리 선택
    private static void backtracking(int start, int r, int[] selected) {
        if (r == 3) {
            flag = true;
            //visited = new boolean[n][n];

            for (int i = 0; i < 3; i++) {
                map[selected[i]/n][selected[i]%n] = 'O';
            }
//            for (int[] t: teacher) {
//                visited[t[0]][t[1]] = true;
//            }
            for (int[] t: teacher) {
                for (int i = 0; i < 4; i++) {
                    dfs(t[0], t[1], i);
                }
            }
            for (int i = 0; i < 3; i++) {
                map[selected[i]/n][selected[i]%n] = 'X';
            }
            if (flag) {
                System.out.print("YES");
                System.exit(0);
            }
            return;
        }

        for (int i = start; i < k; i++) {
            selected[r] = empty.get(i);
            backtracking(i+1, r+1, selected);
        }
    }

    private static void dfs(int x, int y, int dir) {

        //visited[x][y] = true;
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        if (!(nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny] == 'O')) {
            if (map[nx][ny] == 'S') {
                flag = false;
            }
            else {
                dfs(nx, ny, dir);
            }
        }
    }

    
}
