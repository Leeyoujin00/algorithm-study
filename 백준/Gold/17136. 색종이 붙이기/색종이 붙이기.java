import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int n = 10;
    static int min = Integer.MAX_VALUE;
    static int[] paper = {5,5,5,5,5};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sol(0,0,0);

        if (min == Integer.MAX_VALUE) System.out.print(-1);
        else System.out.print(min);
    }

    private static void sol(int x, int y, int cnt) {

        if (y == n) {
            sol(x+1, 0, cnt);
            return;
        }
        if (x == n) {
            min = Math.min(min, cnt);
            return;
        }

        if (map[x][y] == 0) {
            sol(x, y+1, cnt);
            return;
        }

        for (int i = 5; i >= 1; i--) {
            if (paper[i-1] > 0 && check(x,y,i)) {
                paste(x, y, i);
                paper[i-1]--;
                sol(x, y+1, cnt+1);
                unpaste(x, y, i);
                paper[i-1]++;
            }
        }
    }

    private static boolean check(int x, int y, int size) {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (x+i < 0 || x+i >= n || y+j < 0 || y+j >= n) return false;
                if (map[x+i][y+j] == 0) return false;
            }
        }
        return true;
    }

    private static void paste(int x, int y, int size) {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[x+i][y+j] = 0;
            }
        }
    }

    private static void unpaste(int x, int y, int size) {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[x+i][y+j] = 1;
            }
        }
    }
}
