import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static long[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new long[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(arr);
        long min = Long.MAX_VALUE;

        // 두 포인터가 처음 위치에서 함께 시작
        int left = 0, right = 0;
        while (left < n && right < n) {

            long dif = arr[right] - arr[left];

            if (dif == m) {
                min = m;
                break;
            }
            if (dif < m) {
                right++;
            }
            else if (dif > m) {
                min = Math.min(min, dif);
                left++;
            }
        }

        System.out.print(min);
    }
}
