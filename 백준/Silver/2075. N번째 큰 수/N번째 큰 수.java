import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int num;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                // 큐의 크기가 n보다 작다면, 큐에 해당 수를 넣는다.
                num = Integer.parseInt(st.nextToken());
                if (pq.size() < n) pq.offer(num);
                else {
                    // 큐의 수들 중 현재 수보다 작은 수를 제외한다.
                    if (pq.peek() < num) {
                        pq.poll();
                        pq.offer(num);
                    }
                }
            }
        }

        System.out.println(pq.peek());


    }
}
