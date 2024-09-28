import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static char[] str1;
    static char[] str2;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line1 = br.readLine();
        String line2 = br.readLine();

        int n1 = line1.length();
        int n2 = line2.length();

        str1 = new char[n1+1];
        str2 = new char[n2+1];
        dp = new int[n1+1][n2+1];

        for (int i = 1; i <= n1; i++) {
            str1[i] = line1.charAt(i-1);
        }

        for (int i = 1; i <= n2; i++) {
            str2[i] = line2.charAt(i-1);
        }

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (str1[i] == str2[j]) {
                    dp[i][j] = dp[i-1][j-1]+1;
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        int max = 0;
        // 최댓값 구하기
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (max < dp[i][j]) max = dp[i][j];
            }
        }

        System.out.print(max);
    }
}
