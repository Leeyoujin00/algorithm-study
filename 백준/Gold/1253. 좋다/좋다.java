import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int cnt = 0;
        // 투포인터 활용
        // 서로 다른 두 수의 합으로 자신이 만들어져야 함.
        for (int i = 0; i < n; i++) {
            int findNum = arr[i];
            int left = 0;
            int right = n-1;
            int sum = 0;

            while (left < right) {
                sum = arr[left] + arr[right];

                if (sum == findNum) {
                    // 포인터 값이 자신이 아니어야 함.
                    if (left == i) {
                        left++;
                    }
                    else if (right == i) {
                        right--;
                    }
                    else {
                        cnt++;
                        break;
                    }
                }

                if (arr[left] + arr[right] < findNum) {
                    left++;
                }
                else if (arr[left] + arr[right] > findNum) {
                    right--;
                }
            }
        }

        System.out.print(cnt);
    }
}
