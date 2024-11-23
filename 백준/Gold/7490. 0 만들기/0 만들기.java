import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int t, n;
    static int[] exp = {0, 1, 2};
    static int[] selected;
    static boolean isFirst;
    static List<String> resultArr;
    static List[] r;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        t = Integer.parseInt(st.nextToken());

        r = new List[t];

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            selected = new int[n-1];
            isFirst = true;
            resultArr = new ArrayList<>();
            dfs(0, selected);
            r[i] = resultArr;
        }

        for (int i = 0; i < t-1; i++) {
            for (int j = 0; j < r[i].size(); j++) {
                System.out.println(r[i].get(j));
            }
            System.out.println();
        }

        for (int j = 0; j < r[t-1].size()-1; j++) {
            System.out.println(r[t-1].get(j));
        }
        System.out.print(r[t-1].get(r[t-1].size()-1));

    }

    static void dfs(int r, int[] selected) {

        if (r == n-1) {
            StringBuilder sb = new StringBuilder();
            sb.append(1);
            int num = 2;
            for (int i = 0; i < n-1; i++) {
                if (selected[i] == 0) {
                    sb.append(' ');
                }
                else if (selected[i] == 1) {
                    sb.append('+');
                }
                else sb.append('-');
                sb.append(num++);
            }
            printResult(sb.toString());
            return;
        }

        for (int i = 0; i < 3; i++) {
            selected[r] = i;
            dfs(r+1, selected);
        }

    }

    static void printResult(String str) {

        String s = str;
        //System.out.println(str);
        int idx = 0;
        StringBuilder sb = new StringBuilder();
        str = str.replace(" ", "");
        while(idx < str.length() && str.charAt(idx) != '+' && str.charAt(idx) != '-') {
            sb.append(str.charAt(idx++));
        }
        int result = Integer.parseInt(sb.toString());
        int m = 1;
        int num = 0;
        for (int i = str.length()-1; i >= idx; i--) {
            if (str.charAt(i) != '-' && str.charAt(i) != '+') {
                num += m * Integer.parseInt(Character.toString(str.charAt(i)));
                m *= 10;
            }
            else {
                if (str.charAt(i) == '+') {
                    result += num;
                }
                else {
                    result -= num;
                }
                m = 1;
                num = 0;
            }
            //System.out.println(num);
        }

        if (result == 0) {
            resultArr.add(s);
        }
    }
}
