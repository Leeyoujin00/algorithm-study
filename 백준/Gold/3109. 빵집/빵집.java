import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int r,c;
    static char[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];

        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 맨 위 행부터 차례대로 각 시작점이 각 도착점 향해 탐색하도록 할 경우,
        // 최적해로 구할 수 있음
        int n = 0;
        for (int i = 0; i < r; i++) {
            if (check(i, 0)) {
                n++;
            }
        }

        System.out.println(n);
    }
    // dfs 활용
    private static boolean check(int x, int y) {

        // 방문 표시
        map[x][y] = '-';
        // 도착지 도달했을 경우
        if (y == c-1) {
            return true;
        }
        // 오른쪽 대각선 위
        if (0 < x && map[x-1][y+1] == '.') {
            if (check(x-1, y+1)) {
                return true;
            }
        }
        // 오른쪽
        if (map[x][y+1] == '.') {
            if (check(x, y+1)) {
                return true;
            }
        }
        // 오른쪽 아래
        if (x < r-1 && map[x+1][y+1] == '.') {
            if (check(x+1, y+1)) {
                return true;
            }
        }

        return false;
    }
}
