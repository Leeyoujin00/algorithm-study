import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static Integer[] dp;
    static int[] seq;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        seq = new int[N];
        dp = new Integer[N];


        st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        // 0~N-1까지 탐색
        for (int i = 0; i < N; i++) {
            LIS(i);
        }

        int max = dp[0];
        // 최댓값 찾기
        for (int i = 1; i < N; i++) {
            max = Math.max(max, dp[i]);
        }
        System.out.print(max);
    }

    public static int LIS(int n) {

        // 탐색하지 않았던 위치의 경우
        if (dp[n] == null) {
            dp[n] = 1; // 1로 초기화

            // n-1부터 0까지 중 n보다 작은 값들을 찾으면서 재귀 호출
            for (int i = n-1; i >= 0; i--) {
                if (seq[i] < seq[n]) {
                    dp[n] = Math.max(dp[n], LIS(i) + 1);
                }
            }
        }
        return dp[n];
    }
}
