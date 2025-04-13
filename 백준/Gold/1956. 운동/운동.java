import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int v,e;
    static int[][] map;
    static int INF = 5000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        map = new int[v+1][v+1];
        // 연결 정보를 초기화한다.
        for (int i = 0; i <= v; i++) {
            Arrays.fill(map[i], INF);
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[a][b] = c;
        }

        // 모든 노드에서 모든 노드로의 최단경로를 구한다.
        // => 플로이드 워셜 알고리즘
        for (int k = 1; k <= v; k++) {
            for (int i = 1; i <= v; i++) {
                for (int j = 1; j <= v; j++) {
                    if (map[i][k] == INF || map[k][j] == INF) continue;
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        int min = INF;
        // 시작 지점과 도착 지점이 같은 경우들 중 최단 거리를 찾는다.
        for (int i = 1; i <= v; i++) {
            min = Math.min(min, map[i][i]);
        }

        if (min == INF) System.out.print(-1);
        else System.out.print(min);
    }
}
