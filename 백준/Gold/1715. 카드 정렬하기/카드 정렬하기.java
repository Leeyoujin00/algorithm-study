import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // 가장 작은 것 두개 먼저 합침
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.offer(Long.parseLong(br.readLine()));
        }

        if (n == 1) {
            System.out.println(0);
            System.exit(0);
        }

        Long n1, n2;
        Long sum = 0L;
        while(!pq.isEmpty()) {
            n1 = pq.poll();
            if (!pq.isEmpty()) {
                n2 = pq.poll();
                pq.offer(n1+n2);
                sum += n1+n2;
            } else {
                break;
            }
        }

        System.out.println(sum);

    }
}
