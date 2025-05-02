import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n+1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // i번째 계단을 j칸 연속 밟아서 얻은 점수 저장
        // 두 칸 연속 안밟을 수는 없다.
        int[][] dp = new int[n+1][3];
        for (int i = 1; i <= n; i++) {
            int score = arr[i];
            // 현재 칸 안 밟는 경우
            dp[i][0] = Math.max(dp[i-1][2], dp[i-1][1]);
            // 현재 칸이 밟는 첫번째 칸인 경우
            dp[i][1] = score + dp[i-1][0];
            // 현재 칸이 밟는 두번째 칸인 경우
            dp[i][2] = score + dp[i-1][1];
        }

        System.out.println(Math.max(dp[n][1], dp[n][2]));
    }
}
