import java.util.*;

public class Main {
    static String s;
    static long cnt = 0;

    static boolean isMo(char ch) {
        return ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U';
    }

    static boolean isJa(char ch) {
        return !isMo(ch);
    }

    static void dfs(int idx, int mo, int ja, boolean l, long c) {
        if (mo >= 3 || ja >= 3) return;
        if (idx == s.length()) {
            if (l) cnt += c;
            return;
        }

        char currentChar = s.charAt(idx);
        if (currentChar == '_') {
            dfs(idx + 1, mo + 1, 0, l, c * 5); // 모음 대체
            dfs(idx + 1, 0, ja + 1, l, c * 20); // 자음 대체
            dfs(idx + 1, 0, ja + 1, true, c); // 'L' 대체
        } else {
            dfs(idx + 1, isMo(currentChar) ? mo + 1 : 0, isJa(currentChar) ? ja + 1 : 0, l || currentChar == 'L', c);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        s = scanner.nextLine();

        dfs(0, 0, 0, false, 1);
        System.out.println(cnt);
    }
}
