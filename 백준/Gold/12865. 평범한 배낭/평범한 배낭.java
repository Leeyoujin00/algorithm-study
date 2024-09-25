import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int n, k;
    static int dp[][], w[], v[]; // dp배열과 무게, 가치배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dp = new int[n+1][k+1];

        w = new int[n+1];
        v = new int[n+1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                // 기본적으로 이전 아이템의 가치를 저장한다.
                dp[i][j] = dp[i-1][j];
                // 만약 담을 수 있는 무게가 자신의 무게 이상이라면,
                if (w[i] <= j) {
                    dp[i][j] = Math.max(dp[i][j], v[i]+dp[i-1][j-w[i]]);
                }
            }
        }

        bw.write(String.valueOf(dp[n][k]));
        bw.flush();
        bw.close();

    }
}
