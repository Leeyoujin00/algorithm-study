import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int k;
    static boolean[] visited;
    static String[] words;
    static int max = 0;

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();

        sc.nextLine();
        words = new String[n];

        for (int i = 0; i < n; i++) {
            String str = sc.nextLine();
            str = str.replace("anta", "");
            str = str.replace("tica", "");
            words[i] = str;
        }

        if (k < 5) { // a c i n t 의 개수가 5개 이므로
            System.out.println(0);
            return;
        }
        else if (k == 26) { // 모든 알파벳 읽을 수 있음
            System.out.println(n);
            return;
        }

        // 각 알파벳 비웠는지 여부 저장하는 배열
        visited = new boolean[26];

        visited['a'-'a'] = true;
        visited['c'-'a'] = true;
        visited['i'-'a'] = true;
        visited['n'-'a'] = true;
        visited['t'-'a'] = true;

        backtracking(0,0);
        System.out.println(max);

    }

    static void backtracking(int alpha, int len) {

        if (len == k-5) { // 알파벳 다 선택했다면
            int result = 0;
            // 몇 개의 단어 포함 가능한지 계산
            for (int i = 0; i < n; i++) {
                boolean read = true;
                String w = words[i];
                for (int j = 0; j < w.length(); j++) {
                    if (!visited[w.charAt(j)-'a']) {
                        read = false;
                        break;
                    }
                }
                if (read) {
                    result++;
                }
            }
            max = Math.max(max, result);
            return;
        }

        for (int i = alpha; i < 26; i++) {
            if (!visited[i]) {
                visited[i] = true;
                backtracking(i+1, len+1);
                visited[i] = false;
            }
        }
    }
}
