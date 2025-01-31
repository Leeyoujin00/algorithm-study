import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static List<Integer>[] pre;
    static int[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        pre = new List[n+1];
        num = new int[n+1];
        Arrays.fill(num, 1);

        for (int i = 0; i <= n; i++) {
            pre[i] = new ArrayList<>();
        }

        // 1 - 2,3 /2 - 5 /4 - 5
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 선수과목 정보 저장
            pre[a].add(b);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < pre[i].size(); j++) {
                num[pre[i].get(j)] = Math.max(num[pre[i].get(j)], num[i]+1);
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(num[i] + " ");
        }

    }
}
