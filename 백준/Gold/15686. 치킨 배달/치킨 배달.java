import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int m;
    static List<int[]> house;
    static List<int[]> chicken;
    static int chickenNum = 0;
    static boolean[] visited;
    static int min = 3000;

    public static void main(String[] args) throws IOException {

        /**
         * 30 * 13 * 13
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        house = new ArrayList<>();
        chicken = new ArrayList<>();


        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int s = Integer.parseInt(st.nextToken());
                if (s == 1) { // 집 위치 저장
                    house.add(new int[] {i,j});
                }
                else if (s == 2) { // 치킨집 위치 저장
                    chicken.add(new int[] {i,j});
                    chickenNum++;
                }
            }
        }

        visited = new boolean[chickenNum];
        for (int i = 1; i <= m; i++) {
            combination(visited, 0, 0, i);
        }

        System.out.print(min);

    }

    static void combination(boolean[] visited, int start, int r, int m) {

        if (r == m) {
            calculate(visited);
            return;
        }

        for (int i = start; i < chickenNum; i++) {
            if (!visited[i]) {
                visited[i] = true;
                combination(visited, i+1, r+1, m);
                visited[i] = false;
            }
        }
    }

    static void calculate(boolean[] visited) {

        int d = 0;

        for (int i = 0; i < house.size(); i++) {
            int minC = 100;
            for (int j = 0; j < chickenNum; j++) {
                if (visited[j]) {
                    minC = Math.min(minC, Math.abs(chicken.get(j)[0] - house.get(i)[0]) + Math.abs(chicken.get(j)[1] - house.get(i)[1]));
                }
            }
            d += minC;
        }

        min = Math.min(d, min);
    }


}
