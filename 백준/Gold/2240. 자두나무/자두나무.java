import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int t,w,tree;
    static int[][] dp;
    static List<Integer> s = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        t = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());


        dp = new int[t+1][w+1];

        for (int i = 1; i <= t; i++) {
            tree = Integer.parseInt(br.readLine());

            for (int j = 0; j <= w; j++) {
                if (j == 0) { // 아직 이동하기 전
                    if (tree == 1) dp[i][j] = dp[i-1][j] + 1;
                    else dp[i][j] = dp[i-1][j];
                    continue;
                }
                if (j % 2  == 0) { // 현재위치가 1번 나무 밑
                    if (tree == 1) { // 현재위치와 자두위치가 같음
                        // 이전 위치에서 이동해 온 경우와, 그대로 있는 경우의 값 중 최댓값 선택
                        dp[i][j] = Math.max(dp[i-1][j]+1, dp[i-1][j-1]+1);
                    }
                    else { // 현재위치와 자두위치가 다름
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]);
                    }
                }
                if (j % 2 == 1) { // 현재위치가 2번 나무 밑
                    if (tree == 2) { // 현재위치와 자두위치가 같음
                        dp[i][j] = Math.max(dp[i-1][j]+1, dp[i-1][j-1]+1);
                    }
                    else { // 현재위치와 자두위치가 다름
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]);
                    }
                }
            }
        }

        int max = 0;
        for (int cnt : dp[t]) {
            max = Math.max(max, cnt);
        }

        System.out.print(max);

    }
}
