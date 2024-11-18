import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    static int n;
    static int[] minNutrient;
    static int[][] ingredients; // 각 식재료의 영양소,가격 저장
    static boolean[] visited;
    static int minPrice = 10000;
    static int[] selected;
    static int[] result;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        ingredients = new int[n][5];

        // 최소 영양성분 저장
        minNutrient = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            minNutrient[i] = Integer.parseInt(st.nextToken());
        }

        // 식재료 정보 저장
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                ingredients[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        visited = new boolean[n];
        selected = new int[n];
        combination(0, 0, n, selected);

        if (minPrice == 10000) {
            System.out.print(-1);
            System.exit(0);
        }

        System.out.println(minPrice);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }


    }

    static void combination(int start, int r, int num, int[] selected) {

        if (0 < r) {
            if (possibility(selected, r)) {
                if (isMinPrice(selected,r)) {
                    result = new int[r];
                    for (int j = 0; j < r; j++) {
                        result[j] = selected[j]+1;
                    }
                    return;
                }
            }
        }

        for (int i = start; i < num; i++) {
            if (!visited[i]) {
                visited[i] = true;
                selected[r] = i;
                combination(i+1, r+1, num, selected);
                visited[i] = false;
            }
        }
    }

    static boolean possibility(int[] selected, int num) {

        int p = 0;
        int f = 0;
        int s = 0;
        int v = 0;
        // 최소 영양성분 충족하는지 확인
        for (int i = 0; i < num; i++) {
            p += ingredients[selected[i]][0];
            f += ingredients[selected[i]][1];
            s += ingredients[selected[i]][2];
            v += ingredients[selected[i]][3];
        }

        if (minNutrient[0] <= p && minNutrient[1] <= f && minNutrient[2] <= s && minNutrient[3] <= v) {
            return true;
        }

        return false;
    }

    static boolean isMinPrice(int[] selected, int num) {

        int price = 0;
        // 최소 영양성분 충족하는지 확인
        for (int i = 0; i < num; i++) {
            price += ingredients[selected[i]][4];
        }
        if (price < minPrice) {
            minPrice = price; // 필요X?
            // 먼저 선택된 것이 사전 순으로 빠른 것이기 때문에 결과 출력 후 종료
//                System.out.println(price);
//                for (int i = 0; i < num; i++) {
//                    System.out.print(selected[i] + " ");
//                }
            return true;
        }
        return false;
    }
}
