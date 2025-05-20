import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 절댓값, 원래값을 저장하는 배열
        // 절댓값 작은 순, 원래값 작은순으로 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> (o1[0] != o2[0] ? Integer.compare(o1[0],o2[0]) : Integer.compare(o1[1],o2[1])));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x == 0) {
                if (pq.isEmpty()) sb.append(0 + "\n");
                else sb.append(pq.poll()[1] + "\n");
            }
            else pq.offer(new int[] {Math.abs(x), x});
        }

        System.out.print(sb);

    }
}
