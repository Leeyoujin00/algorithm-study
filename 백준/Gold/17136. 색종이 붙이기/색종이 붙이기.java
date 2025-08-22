import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n = 10;
    static int[][] map;
    static int[] papers = {0,5,5,5,5,5}; // 남은 색종이 개수 저장
    static int ans = Integer.MAX_VALUE;

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

        dfs(0,0,0);
        if (ans == Integer.MAX_VALUE) System.out.print(-1);
        else System.out.print(ans);
    }

    // DFS + 백트래킹 활용
    public static void dfs(int x, int y, int cnt) {
        // 끝까지 도달했다면, ans 갱신
        if (x > 9) {
            //System.out.println(cnt);
            ans = Math.min(ans, cnt);
            return;
        }

        // y축 끝이라면, 다음 칸으로 이동
        if (y >= 10) {
            dfs(x+1, 0, cnt);
            return;
        }

        if (map[x][y] == 1) { // 색종이 붙일 수 있는 칸이라면
            // 크기 큰 종이부터 색종이를 붙임
            for (int i = 5; i >= 1; i--) {
                if (papers[i] > 0 && canAttach(x,y,i)) {
                    // 색종이를 붙임
                    papers[i]--;
                    attach(x,y,i,0);
                    dfs(x, y+1, cnt+1);
                    // 색종이를 뗌
                    papers[i]++;
                    attach(x,y,i,1);
                }
            }
        } else {
            dfs(x, y+1, cnt); // 다음칸 탐색
        }
    }

    // 색종이 붙이는 함수
    public static void attach(int x, int y, int size, int state) {

        for (int i = x; i < x+size; i++) {
            for (int j = y; j < y+size; j++) {
                map[i][j] = state;
            }
        }
    }

    // 색종이 붙일 수 있는지 확인
    public static boolean canAttach(int x, int y, int size) {

        for (int i = x; i < x+size; i++) {
            for (int j = y; j < y+size; j++) {
                if (i < 0 || i >= n || j < 0 || j >= n) return false;
                if (map[i][j] != 1) return false;
            }
        }

        return true;
    }
}
