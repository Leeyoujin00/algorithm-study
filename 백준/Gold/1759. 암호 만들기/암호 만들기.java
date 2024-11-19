import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int l;
    static int c;
    static String[] s;
    static boolean[] visited;
    static Character[] selected;
    static int n = 0; // 모음 수
    static int m = 0; // 자음 수
    static Character[] moeum = {'a', 'e', 'i', 'o', 'u'};


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        s = br.readLine().split(" ");

        Arrays.sort(s);
        visited = new boolean[c];
        selected = new Character[l];

        //result = new ArrayList<>();

        //Collections.sort(result);

        combination(0,0,selected);

    }

    static void combination(int start, int r, Character[] selected) {

        if (r == l) {
            if (0 < n && 1 < m) {
                StringBuilder sb = new StringBuilder("");
                for (int i = 0; i < l; i++) {
                    sb.append(selected[i]);
                }
                System.out.println(sb);
            }
            return;
        }

        for (int i = start; i < c; i++) {
            if (!visited[i]) {
                if (isMoeum(s[i].charAt(0))) {
                    n++;
                    visited[i] = true;
                    selected[r] = s[i].charAt(0);
                    combination(i+1, r+1, selected);
                    visited[i] = false;
                    n--;
                }
                else {
                    m++;
                    visited[i] = true;
                    selected[r] = s[i].charAt(0);
                    combination(i+1, r+1, selected);
                    visited[i] = false;
                    m--;
                }
            }
        }
    }

    static boolean isMoeum(Character c) {
        for (int i = 0; i < 5; i++) {
            if (c.equals(moeum[i])) {
                return true;
            }
        }
        return false;
    }
}
