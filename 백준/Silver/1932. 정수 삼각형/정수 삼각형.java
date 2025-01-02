import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main{
    static int n;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        //nums = new int[n];
        dp = new int[n*(n+1)/2];
        int idx = 0;
        for (int i = 0 ; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < i+1; j++) {
                dp[idx++] = Integer.parseInt(st.nextToken());
            }
        }

        idx--;
        // 맨 밑줄부터 상위 경로의 합 갱신
        for (int line = n-1; line > 0; line--) {
            if (line != n-1) idx--;
            for (int i = line; i > 0; i--) {
                dp[idx-(line+1)] += Math.max(dp[idx], dp[idx-1]);
                idx--;
            }
        }

//        for (int d : dp) {
//            System.out.print(d + " ");
//        }

        System.out.print(dp[0]);

    }
}
