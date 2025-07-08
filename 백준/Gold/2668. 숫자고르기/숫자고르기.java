import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    static boolean[] ck;
    static Queue<Integer> que = new LinkedList<>();
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n+1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        ck = new boolean[n+1];
        //StringBuilder result = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            que.clear();
            if (!ck[i]) {
                if (!dfs(i, i)) {
                    while(!que.isEmpty()) {
                        ck[que.poll()] = false;
                    }
                }
                else {
                    while(!que.isEmpty()) {
                        result.add(que.poll());
                    }
                }
            }
        }

        Collections.sort(result);
        StringBuilder sb = new StringBuilder();
        sb.append(result.size() + "\n");
        for (int r: result) {
            sb.append(r + "\n");
        }

        System.out.print(sb);
    }

    private static boolean dfs(int start, int cur) {

        ck[cur] = true;
        que.offer(cur);

        int next = arr[cur];
        if (next == start) {
            return true;
        }
        if (!ck[next]) {
            return dfs(start, next);
        }

        return false;
    }
}
