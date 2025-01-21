import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static int[][] arr;
    static List<int[]> chickenPos = new ArrayList<>();
    static List<int[]> housePos = new ArrayList<>();
    static int[] d;
    static int[] selected;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 2) {
                    // 치킨집 위치 저장
                    chickenPos.add(new int[] {i,j});
                }
                else if (arr[i][j] == 1) {
                    // 집 위치 저장
                    housePos.add(new int[] {i,j});
                }
            }
        }

        d = new int[chickenPos.size()];

        selected = new int[m];
        backtracking(0, 0);
        System.out.println(min);
    }

    // 치킨집 m개를 선택
    static void backtracking(int start, int r) {

        if (r == m) {
            min = Math.min(min, getMinD());
            return;
        }

        for (int i = start; i < chickenPos.size(); i++) {
            selected[r] = i;
            backtracking(i+1, r+1);
        }
    }

    // 각각의 집과 가장 가까운 치킨집과의 거리들의 합
    static int getMinD() {

        int sum = 0;
        for (int i = 0; i < housePos.size(); i++) {
            int min = 2500;
            int d;
            for (int j = 0; j < m; j++) {
                d = Math.abs(chickenPos.get(selected[j])[0] - housePos.get(i)[0]) + Math.abs(chickenPos.get(selected[j])[1] - housePos.get(i)[1]);
                min = Math.min(min, d);
            }
            sum += min;
        }

        return sum;
    }
}
