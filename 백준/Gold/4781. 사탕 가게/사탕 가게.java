import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] c,p;
    //static int[][] dp;
    static List<Integer> ans = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = (int)(Double.parseDouble(st.nextToken())*100 + 0.5);

            if (n == 0) break;

            //dp = new int[n+1][m+1];
            c = new int[n+1];
            p = new int[n+1];

            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());
                c[i] = Integer.parseInt(st.nextToken());
                p[i] = (int)(Double.parseDouble(st.nextToken()) * 100 + 0.5);;
            }

            int[] dp = new int[m+1];

            // 각 사탕에 대해 진행
            for (int i = 1; i <= n; i++) {
                int cc = c[i];
                int cp = p[i];
                // 각 가격에 대해 진행
                for (int j = cp; j <= m; j++) {
                    dp[j] = Math.max(dp[j], dp[j-cp]+cc);
                }
            }

            int max = 0;
            for (int i = 1; i <= m; i++) {
                max = Math.max(max, dp[i]);
            }

            ans.add(max);
        }

        for (int a : ans) {
            System.out.println(a);
        }

    }
}
