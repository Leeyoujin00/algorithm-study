import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static char[] chars;
    static char[] selected;
    static PriorityQueue<String> queue = new PriorityQueue<>();
    static Stack<Character> stack = new Stack<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            int length = word.length();
            chars = new char[26];

            for (int j = 0; j < length; j++) {
                chars[word.charAt(j)-'a']++;
            }

            dfs(length, chars);
            for (String s : queue) {
                sb.append(s).append("\n");
            }
            queue.clear();
        }
        System.out.println(sb.toString());
    }

    private static void dfs(int limit, char[] chars) {
        // 단어 구성 완료
        if (stack.size() == limit) {
            StringBuilder sb = new StringBuilder();
            for (char c : stack) {
                sb.append(c);
            }
            queue.add(sb.toString());
        }

        for (int i = 0; i < 26; i++) {
            if (chars[i] > 0) {
                chars[i]--;
                stack.push((char)(i+'a'));
                dfs(limit, chars);
                stack.pop();
                chars[i]++;
            }
        }
    }
}
