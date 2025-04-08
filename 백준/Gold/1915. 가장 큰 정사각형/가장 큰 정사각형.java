import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static int[][] arr;
    static int[][] dp;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
                if (arr[i][j] == 1) max = 1;
            }
        }

        dp = new int[n][m];
        for (int i = 0; i < n; i++) dp[i][0] = arr[i][0];
        for (int i = 0; i < m; i++) dp[0][i] = arr[0][i];

        // 첫번째 행과 열에 대해서는 검사하지 않는다.
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                // 현재 좌표의 위, 왼쪽, 왼쪽 대각선 위의 값 중 최소값+1 로 갱신
                if (arr[i][j] == 0) continue;
                int up = dp[i-1][j];
                int left = dp[i][j-1];
                int diagonal = dp[i-1][j-1];

                dp[i][j] = Math.min(Math.min(up,left),diagonal)+1;
                max = Math.max(dp[i][j], max);
            }
        }

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                System.out.print(dp[i][j]);
//            }
//            System.out.println();
//        }

        System.out.println(max*max);

    }
}
