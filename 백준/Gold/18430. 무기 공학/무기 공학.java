import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int n,m,max;
    static boolean[][] visit;
    // 부메랑 만들 수 있는 두 좌표 들어있는 배열
    static int[][] di = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    static int[][] dj = {{-1, 0}, {-1, 0}, {0, 1}, {0, 1}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visit = new boolean[n][m];
        max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0,0);
        System.out.println(max);
    }

    static void dfs(int num, int sum) {

        // 배열의 모든 숫자 탐색했으면 종료
        if (num == n*m) {
            max = Math.max(max, sum);
            return;
        }

        int nowI = num / m;
        int nowJ = num % m;

        if (!visit[nowI][nowJ]) {
            // 현재 위치에서 부메랑 만들 수 있는지 확인
            for (int i = 0; i < 4; i++) {
                int ni1 = nowI + di[i][0];
                int nj1 = nowJ + dj[i][0];
                int ni2 = nowI + di[i][1];
                int nj2 = nowJ + dj[i][1];

                if (isPossible(ni1, nj1, ni2, nj2)) {
                    visit[ni1][nj1] = true;
                    visit[nowI][nowJ] = true;
                    visit[ni2][nj2] = true;
                    dfs(num+1, sum + map[ni1][nj1] + 2*map[nowI][nowJ] + map[ni2][nj2]);
                    visit[ni1][nj1] = false;
                    visit[nowI][nowJ] = false;
                    visit[ni2][nj2] = false;
                }
            }
        }

        dfs(num+1, sum);

    }

    static boolean isPossible(int i1, int j1, int i2, int j2) {

        // 탐색범위 벗어나면 false 반환
        if (i1 < 0 || n <= i1 || i2 < 0 || n <= i2 || j1 < 0 || m <= j1 || j2 < 0 || m <= j2) {
            return false;
        }
        // 방문한 적 있으면 flase 반환
        if (visit[i1][j1] || visit[i2][j2]) return false;

        return true;
    }
}
