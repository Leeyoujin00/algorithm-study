import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.valueOf(st.nextToken());
        m = Integer.valueOf(st.nextToken());
        map = new int[n+1][n+1];

        int INF = n+1;
        for (int i = 0; i <= n; i++) {
            Arrays.fill(map[i], INF);
        }

        // 플로이드 워셜 알고리즘 활용
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            // a = 무거운거, b = 가벼운거
            int a = Integer.valueOf(st.nextToken());
            int b = Integer.valueOf(st.nextToken());
            map[a][b] = 1;
            //map[b][a] = 1;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i != j && map[i][k] == 1 && map[k][j] == 1) {
                        map[i][j] = 1;
                    }
                }
            }
        }

//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= n; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }

        // 자신보다 가벼운 구슬의 개수가 n/2보다 많으면 절대 중간이 될 수 없다.
        // 무게가 중간이 절대로 될 수 없는 구슬이다.
        int mid = n/2;
        int ans = 0;

        for (int i = 1; i <= n; i++) {
            int heavyNum = 0;
            int lightNum = 0;
            for (int j = 1; j <= n; j++) {
                if (map[i][j] == 1) {
                    lightNum++;
                }
                else if (map[j][i] == 1) {
                    heavyNum++;
                }
            }
            if (heavyNum > mid || lightNum > mid) ans++;
        }

        System.out.print(ans);
    }
}
