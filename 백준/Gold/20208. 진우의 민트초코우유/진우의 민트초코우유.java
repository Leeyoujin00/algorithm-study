import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n,m,h;
    static List<int[]> milkPos = new ArrayList<>();
    static int homeX, homeY;
    static int maxCnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int x = Integer.parseInt(st.nextToken());
                // 우유 있는 좌표 저장
                if (x == 2) {
                    milkPos.add(new int[] {i,j});
                }
                else if (x == 1) {
                    milkPos.add(new int[] {i,j});
                    homeX = i;
                    homeY = j;
                }
            }
        }

        boolean[] visited = new boolean[milkPos.size()];
        backtracking(visited, homeX, homeY, 0);

        System.out.print(maxCnt);
    }

    // 민초O 지역들의 방문순서 지정
    static void backtracking(boolean[] visited, int preX, int preY, int cnt) {

        if (preX == homeX && preY == homeY && cnt != 0) {
            maxCnt = Math.max(maxCnt, cnt-1);
            return;
        }

        for (int i = 0; i < milkPos.size(); i++) {
            if (!visited[i]) {
                // 해당 위치로 이동할 수 있으면 백트래킹 진행
                int distance = Math.abs(preX-milkPos.get(i)[0]) + Math.abs(preY-milkPos.get(i)[1]);
                if (distance <= m) {
                    m -= distance;
                    m += h;
                    visited[i] = true;
                    backtracking(visited, milkPos.get(i)[0], milkPos.get(i)[1], cnt+1);
                    visited[i] = false;
                    m += distance;
                    m -= h;
                }
            }
        }


    }
}
