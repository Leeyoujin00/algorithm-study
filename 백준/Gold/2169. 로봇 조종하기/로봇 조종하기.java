import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map, dp, temp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dp = new int[N][M];
        temp = new int[2][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 첫째 줄 왼 -> 오 방향으로 값 계산
        dp[0][0] = map[0][0];
        for (int i = 1; i < M; i++) {
            dp[0][i] = dp[0][i-1] + map[0][i];
        }

        // 나머지 줄 값 계산
        for (int i = 1; i < N; i++) {
            // 왼쪽 vs 위쪽 값 비교
            dp[i][0] = dp[i-1][0] + map[i][0];
            temp[0][0] = dp[i][0];
            for (int j = 1; j < M; j++) {
                temp[0][j] = Math.max(temp[0][j-1], dp[i-1][j]) + map[i][j];
            }

            // 오른쪽 vs 위쪽 값 비교
            dp[i][M-1] = dp[i-1][M-1] + map[i][M-1];
            temp[1][M-1] = dp[i][M-1];
            for (int j = M-2; j >= 0; j--) {
                temp[1][j] = Math.max(temp[1][j+1], dp[i-1][j]) + map[i][j];
            }

            for (int j = 0; j < M; j++) {
                dp[i][j] = Math.max(temp[0][j], temp[1][j]);
            }
        }

        bw.write(String.valueOf(dp[N-1][M-1]));
        bw.flush();
        bw.close();
    }
}
