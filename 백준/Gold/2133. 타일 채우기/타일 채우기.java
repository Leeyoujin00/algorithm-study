import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int n;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        if (n % 2 == 1) {
            System.out.println(0);
            System.exit(0);
        }
        
        dp = new int[n+1];

        // f(0) = 1, f(2) = 3, 타일을 놓는 방법
        // f(4) = f(2) * f(2) + 2 = 11
        // f(6) = f(2) * f(4) + 2 + 2 * f(2)
        // f(8) = f(2) f(6) + 2 + (2 * f(2)) + (2 * f(4)) + () + ()

        dp[0] = 1;
        dp[2] = 3;
        for (int i = 4; i <= n; i+= 2) {
            dp[i] += 2 + dp[2] * dp[i-2];
            for (int j = i-4; j >= 2; j -= 2) {
                dp[i] += 2 * dp[j];
            }
        }
       

        System.out.println(dp[n]);

    }

}
