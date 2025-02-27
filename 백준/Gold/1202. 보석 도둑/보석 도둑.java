import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n,k;
    static int[][] info;
    static int[] c;
    //static PriorityQueue<Jewel> pq;
    static Jewel[] jewelries;

    static class Jewel {
        int weight;
        int value;

        public Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        jewelries = new Jewel[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewelries[i] = new Jewel(w,v);
        }

        // 무게가 가벼운 것부터, 무게가 같다면 가치가 높은 것부터 우선
        Arrays.sort(jewelries, (o1,o2) -> o1.weight != o2.weight ? Integer.compare(o1.weight,o2.weight) : Integer.compare(o2.value, o1.value));

        c = new int[k];
        // 가방 정보 입력
        for (int i = 0; i < k; i++) {
            c[i] = Integer.parseInt(br.readLine());
        }

        // 가방 무게 오름차순 정렬
        Arrays.sort(c);

        // 보석 가치가 높은 것이 우선순위 가지는 우선순위 큐 생성 (내림차순)
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        long sum = 0;
        // 모든 가방에 대해 담을 보석 구함
        for (int i = 0, j = 0; i < k; i++) {
            // 현재 가방 무게 이하의 보석(해당 보석의 가치)은 모두 큐에 담는다.
            while (j < n && jewelries[j].weight <= c[i]) {
                pq.offer(jewelries[j++].value);
            }

            if (!pq.isEmpty()) sum += pq.poll();
        }

        System.out.print(sum);

    }

}
