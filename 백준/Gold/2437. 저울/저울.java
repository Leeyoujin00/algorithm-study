import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] weight = new int[n+1];

        for (int i = 1; i <= n; i++) {
            weight[i] = Integer.valueOf(st.nextToken());
        }

        // 추들을 사용해 측정할 수 없는 양의 정수 무게 중 최솟값을 구한다.
        // 누적합 + 1 > 다음 원소이면, 해당 무게는 구할 수 있는 것이다.
        Arrays.sort(weight);
        int[] sum = new int[n+1];
        //sum[0] = weight[0];
        long ans = 0;

        if (n == 1) {
            if (1 < weight[1]) {
                System.out.println(1);
            }
            else System.out.println(2);
            System.exit(0);
        }
        if (weight[1] != 1) {
            System.out.print(1);
            System.exit(0);
        }

        for (int i = 1; i <= n-1; i++) {
            sum[i] = sum[i-1] + weight[i];
            // 누적합 + 1 과 다음 원소의 무게를 비교한다.
            if (sum[i]+1 < weight[i+1]) {
                ans = sum[i]+1;
                break;
            }
        }

        if (ans == 0) {
            ans = sum[n-1] + weight[n] + 1;
        }

        System.out.print(ans);



    }
}
