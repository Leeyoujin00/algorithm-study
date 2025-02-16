import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,k;
    static int min = Integer.MAX_VALUE;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dp = new int[100001][2];
        for (int i = 0; i <= 100000; i++) {
            dp[i][0] = 100001;
        }

        bfs();

        System.out.println(min);

        List<Integer> ans = new ArrayList<>();
//        ans.add(n);
        int nxt = k;
        //int next = n;
        while (true) {
            ans.add(nxt);

            if (nxt == n) break;

            int w = dp[nxt][1];

            if (w == 0) nxt++;
            else if (w == 1) nxt--;
            else if (w == 2) nxt /= 2;

        }

        for (int i = ans.size()-1; i >= 0; i--) {
            System.out.print(ans.get(i) + " ");
        }

    }

    private static void bfs() {

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(n, 0));
         while (!queue.isEmpty()) {
             Node cur = queue.poll();

             //System.out.println(cur.idx);

             if (cur.idx == k) {
                 min = Math.min(min, cur.step);
                 continue;
             }

             // 순간이동
             if (0 <= 2*cur.idx && 2*cur.idx <= 100000) {
                 if (dp[2*cur.idx][0] > cur.step+1) {
                     queue.offer(new Node(2*cur.idx, cur.step+1));
                     dp[2*cur.idx][0] = cur.step+1;
                     dp[2*cur.idx][1] = 2;
                 }
             }

             // 한 칸 뒤로 이동
             if (0 <= cur.idx-1 && cur.idx-1 <= 100000) {
                 if (dp[cur.idx-1][0] > cur.step+1) {
                     //System.out.println();
                     queue.offer(new Node(cur.idx-1, cur.step+1));
                     dp[cur.idx-1][0] = cur.step+1;
                     dp[cur.idx-1][1] = 0;
                 }
             }

             // 한 칸 앞으로 이동
             if (0 <= cur.idx+1 && cur.idx+1 <= 100000) {
                 if (dp[cur.idx+1][0] > cur.step+1) {
                     queue.offer(new Node(cur.idx+1, cur.step+1));
                     dp[cur.idx+1][0] = cur.step+1;
                     dp[cur.idx+1][1] = 1;
                 }
             }
         }
    }

    private static class Node {
        int idx;
        int step;

        public Node(int idx, int step) {
            this.idx = idx;
            this.step = step;
        }
    }
}
