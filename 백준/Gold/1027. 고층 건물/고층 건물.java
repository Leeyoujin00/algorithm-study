import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] buildings;
    static int max = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        buildings = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            max = Math.max(max, count(i));
        }

        System.out.print(max);
    }

    // 각 빌딩에서 보이는 건물의 개수 반환
    static int count(int idx) {

        double min = 0;
        int cnt = 0;
        // 왼쪽 판단
        for (int i = idx-1; i >= 0; i--) {
            double slope = (double)(buildings[i] - buildings[idx]) / (i-idx);

            if (i == idx-1 || slope < min) {
                cnt++;
                min = slope;
            }
        }

        double max = 0;
        // 오른쪽 판단
        for (int i = idx+1; i < n; i++) {
            double slope = (double)(buildings[i] - buildings[idx]) / (i-idx);

            if (i == idx+1 || slope > max) {
                cnt++;
                max = slope;
            }
        }

        return cnt;
    }
}
