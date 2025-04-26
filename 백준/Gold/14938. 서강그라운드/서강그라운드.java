import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n,m,r,t;
    static int[][] distance;
    static int[] items;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        items = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        distance = new int[n+1][n+1];
        int INF = 1500;
        for (int i = 0; i <= n; i++) {
            Arrays.fill(distance[i], INF);
        }

        // 지역간 거리 입력받음
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            distance[a][b] = l;
            distance[b][a] = l;
        }

        // 플로이드 워셜 알고리즘 활용 최단거리 갱신
        for (int k = 0; k <= n; k++) {
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= n; j++) {
                    if (i == j) continue;
                    if (distance[i][k] + distance[k][j] < distance[i][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }

        int max = 0;
        // 모든 정점에 대해, 수색범위 이하의 지역들의 아이템을 획득,
        // 가장 많은 아이템 개수 구한다.
        for (int i = 0; i <= n; i++) {
            int sum = items[i];
            for (int j = 0; j <= n; j++) {
                if (distance[i][j] <= m) {
                    sum += items[j];
                }
            }
            max = Math.max(max, sum);
        }

        System.out.print(max);

    }
}
