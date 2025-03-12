import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 선택된 로프들 중 최소 중량 * 선택된 개수 = 정답
        // 모든 로프 선택하며 각 경우 계산
        int min = 0;
        int cnt = 0;
        int sum = 0;

        Arrays.sort(arr);

        // 100 20 10
        for (int i = n-1; i >= 0; i--) {
            cnt++;
            if (sum < arr[i] * cnt) {
                min = arr[i];
                sum = min * cnt;
            }
        }

        System.out.print(sum);
    }
}
