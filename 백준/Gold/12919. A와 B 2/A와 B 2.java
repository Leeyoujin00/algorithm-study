import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String t = br.readLine();

        recur(s, t);
        System.out.print(0);
    }

    // t에서 시작해 연산 거꾸로 수행하면,
    // 불필요한 경우의 수 탐색 안해도 됨
    private static void recur(String s, String t) {
        if (s.equals(t)) {
            System.out.print(1);
            System.exit(0);
        }

        if (s.length() >= t.length()) {
            return;
        }

        // t가 A로 끝나면, 연산 1 수행한 것임
        if (t.charAt(t.length()-1) == 'A') {
            recur(s, t.substring(0, t.length()-1));
        }

        // t가 B로 시작하면, 연산 2 수행한 것임
        // t 뒤집고, 맨 끝의 B 제거
        if (t.charAt(0) == 'B') {
            StringBuilder sb = new StringBuilder(t);
            recur(s, sb.reverse().substring(0, sb.length()-1));
        }
    }
}
