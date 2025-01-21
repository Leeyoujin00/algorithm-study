import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int x;
    static int[][] dp; // idx로 만들기 위해 행하는 연산 횟수 저장

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        x = Integer.parseInt(br.readLine());

        dp = new int[x+1][2];
        // dp[i][0] = i로 만들기 위해 행하는 최소 연산 횟수
        // dp[i][1] = 바로 전에 수행한 연산 종류

        for (int i = 1; i <= x; i++) {
            for (int j = 0; j < 2; j++) {
                dp[i][0] = 1000000;
                dp[i][1] = -1;
            }
        }
        dp[x][0] = 0;
        dp[x][1] = -1;

        dfs(x);

        System.out.println(dp[1][0]);

        List<Integer> ans = new ArrayList<>();
        int t = 1;
        while (t != x) {
            ans.add(t);
            if (dp[t][1] == 1) {
                t *= 3;
            }
            else if (dp[t][1] == 2) {
                t *= 2;
            }
            else if (dp[t][1] == 3) {
                t += 1;
            }
        }
        ans.add(t);

        for (int i = ans.size()-1; i >= 0; i--) {
            System.out.print(ans.get(i) + " ");
        }

    }

    static void dfs(int num) {

        if (num == 1) {
            return;
        }

        // 연산1
        if (num % 3 == 0) {
            if (dp[num/3][0] > dp[num][0]+1) {
                dp[num/3][0] = dp[num][0]+1;
                dp[num/3][1] = 1;
                dfs(num/3);
            }
        }
        // 연산2
        if (num % 2 == 0) {
            if (dp[num/2][0] > dp[num][0]+1) {
                dp[num/2][0] = dp[num][0]+1;
                dp[num/2][1] = 2;
                dfs(num/2);
            }
        }
        // 연산3
        if (num -1 > 0) {
            if (dp[num-1][0] > dp[num][0]+1) {
                dp[num-1][0] = dp[num][0]+1;
                dp[num-1][1] = 3;
                dfs(num-1);
            }
        }

    }
}
