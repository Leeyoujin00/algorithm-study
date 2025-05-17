import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m;
    static List<Integer>[] indegree;
    static int[] count;
    static int info = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        indegree = new List[n+1];
        count = new int[n+1];

        for (int i = 0; i <= n; i++) {
            indegree[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int tmp = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num - 1; j++) {
                int a = tmp;
                int b = Integer.parseInt(st.nextToken());
                tmp = b;
                boolean flag = true;

                // 중복 확인
                for (int next : indegree[a]) {
                    if (next == b) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    indegree[a].add(b);
                    count[b]++;
                    info++;
                }
            }
        }

        System.out.print(solution());
    }

    // 위상정렬 알고리즘 활용
    private static String solution() {

        Queue<Integer> que = new LinkedList<>();
        boolean[] check = new boolean[n+1];

        for (int i = 1; i <= n; i++) {
            if (count[i] == 0) {
                que.offer(i);
                check[i] = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        while (!que.isEmpty()) {

            int cur = que.poll();
            cnt++;
            sb.append(cur + "\n");
            for (int next : indegree[cur]) {
                if (--count[next] == 0) {
                    que.offer(next);
                }
            }
        }

        if (cnt != n) return new String("0");

        return sb.toString();
    }
}
