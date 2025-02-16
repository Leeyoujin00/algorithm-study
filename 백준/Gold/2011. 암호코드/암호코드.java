import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int MOD = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] ch = br.readLine().toCharArray();

        // 맨 앞자리가 0이면 잘못된 입력임
        if (ch[0] == '0') {
            System.out.println(0);
            return;
        }

        /**
         * ch는 0번 인덱스부터 보고, dp는 dp[i-2]를 구하기 위해 1부터 계산한다.
         * 25114 에서 ch[0] = 2 이며 ch[0] = 2의 dp는 dp[1]이 된다.
         */
        int[] dp = new int[ch.length+1];
        dp[0] = dp[1] = 1;


        for (int i = 2; i <= ch.length; i++) {
            // 한 자릿수 계산했을 때 경우의 수 더하기
            if (ch[i-1] != '0') {
                dp[i] += dp[i-1] % MOD;
            }

            // 두 자릿수도 계산 가능하다면 경우의 수 더하기
            int temp = ((ch[i-2] - '0') * 10) + ch[i-1] - '0';
            if (temp >= 10 && temp <= 26) {
                dp[i] += dp[i-2] % MOD;
            }
        }
        System.out.println(dp[ch.length] % MOD);
    }
}