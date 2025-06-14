import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        visit = new int[N + 1];
        Arrays.fill(visit, -1);

        Queue<Integer> q = new LinkedList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int n = Integer.parseInt(st.nextToken());
            q.offer(n);
            visit[n] = 0;
        }

        bw.write(bfs(q) + "");

        bw.flush();
        bw.close();
        br.close();
    }

    static int bfs(Queue<Integer> q) {
        int ans = 0;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int i = 0; i < 20; i++) {
                int next = now ^ (1 << i);
                if (N < next || visit[next] != -1) continue;
                visit[next] = visit[now] + 1;
                q.offer(next);
                ans = Math.max(ans, visit[next]);
            }
        }

        return ans;
    }
}