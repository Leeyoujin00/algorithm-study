import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static int n;
    static int[] card;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        card = new int[n];

        // 작은 값부터 우선순위 가지는 우선순위 큐
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            int c = Integer.parseInt(br.readLine());
            pq.offer(c);
        }

        int cnt = 0;
        while(!pq.isEmpty()) {
            // 현재 가장 작은 두 카드를 선택
            int c1 = pq.poll();
            if (pq.isEmpty()) {
                break;
            }
            int c2 = pq.poll();
            pq.offer(c1 + c2);
            cnt += c1+c2;
        }

        System.out.print(cnt);
    }
}
