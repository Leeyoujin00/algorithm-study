import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n,m;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 투포인터 활용
        int left = 0;
        int right = 0;
        long sum = arr[0];
        int cnt = 0;

        if (sum == m) cnt++;
        /**
         * 10 5
         * 1 2 3 4 2 5 3 1 1 2
         *
         * 6 3
         * 10 1 3 7 7 7
         */
        while (right < n) {

            // 왼쪽 포인터 이동
            if (sum >= m) {
                sum -= arr[left++];
            }
            // 오른쪽 포인터 이동
            else if (sum < m) {
                right++;
                if (right == n) break;
                sum += arr[right];
            }
            // cnt 증가
            if (sum == m) {
                cnt++;
            }
        }

        System.out.print(cnt);
    }
}
