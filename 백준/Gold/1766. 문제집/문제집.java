import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static int[] indegree;
    static List<Integer>[] sequence; // 자신의 후행작업 저장

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        indegree = new int[n+1];
        sequence = new List[n+1];
        for (int i = 0; i <= n; i++) {
            sequence[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int later = Integer.parseInt(st.nextToken());
            sequence[first].add(later);
            indegree[later]++;
        }

        System.out.print(topologicalSort());
    }

    // 위상 정렬 알고리즘 활용
    private static String topologicalSort() {

        // 선행 작업 없는 작업들 중, 문제 번호 작은 것이 우선순위를 가짐
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 선행 작업 없는 작업들 먼저 수행
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                pq.offer(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int curNode = pq.poll();
            sb.append(curNode + " ");

            for (int nextNode : sequence[curNode]) {
                if (--indegree[nextNode] == 0) {
                    pq.offer(nextNode);
                }
            }
        }

        return sb.toString();
    }
}
