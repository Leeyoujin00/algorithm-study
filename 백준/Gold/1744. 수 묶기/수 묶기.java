import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // 양수만 저장
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(); // 음수만 저장
        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(br.readLine());
            if (a > 0) pq.offer(a);
            else pq2.offer(a);
        }

        int sum = 0;
        while (!pq.isEmpty()) {
            if (pq.peek() == 1) break;
            int num = pq.poll();

            if (!pq.isEmpty()) {
                if (pq.peek() > 1) {
                    sum += num * pq.poll();
                }
                else sum += num;
            }
            else {
                sum += num;
            }
        }

        while (!pq.isEmpty()) {
            sum += pq.poll();
        }

        // 0 이하부터는 제일 작은 수끼리 묶는다.
        while (!pq2.isEmpty()) {
            int num = pq2.poll();
            if (!pq2.isEmpty()) {
                sum += num * pq2.poll();
            }
            else {
                sum += num;
            }
        }


        System.out.print(sum);
    }
}
