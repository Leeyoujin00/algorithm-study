import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] step;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n,m;

        step = new int[101][2];

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 2; i < 101; i++) {
            step[i][0] = 100;
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            step[Integer.parseInt(st.nextToken())][1] = Integer.parseInt(st.nextToken());
        }

        for (int j = 0; j < m; j++) {
            st = new StringTokenizer(br.readLine());
            step[Integer.parseInt(st.nextToken())][1] = Integer.parseInt(st.nextToken());
        }

        Queue<Integer> que = new LinkedList<>();
        que.offer(1);

        while (!que.isEmpty()) {
            int idx = que.poll();
            int stp = step[idx][0];
            int flag = step[idx][1];

            if (flag != 0) {
                if (step[flag][0] > stp) {
                    step[flag][0] = stp;
                }
                que.offer(flag);
                continue;
            }

            for (int i = 1; i < 7; i++) {

                if (idx+i < 101 && step[idx+i][0] > stp+1) {
                    step[idx+i][0] = stp+1;
                    que.offer(idx+i);
                }
            }

        }

        //System.out.println(step[11][0]);
        System.out.println(step[100][0]);

    }
}
