import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static List<Integer>[] graph;
    static int[] indegree;
    static int[] time;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        graph = new List[n+1];
        indegree = new int[n+1];
        time = new int[n+1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());

            int num = Integer.parseInt(st.nextToken());
            for (int k = 0; k < num; k++) {
                int prev = Integer.parseInt(st.nextToken());
                graph[prev].add(i);
                indegree[i]++;
            }
        }

        System.out.print(topologicalSort());
    }

    // 위상정렬 알고리즘 진행
    private static int topologicalSort() {

        Queue<Integer> que = new LinkedList<>();
        int[] result = new int[n+1];

        for (int i = 1; i <= n; i++) {
            result[i] = time[i];
            // 선행 작업 없는 작업을 먼저 실행한다.
            if (indegree[i] == 0) {
                que.offer(i);
            }
        }

        while (!que.isEmpty()) {
            int cur = que.poll();

            for (int next : graph[cur]) {
                // 다음 작업들의 시간은 선행 작업의 실행시간만큼 더해진다.
                result[next] = Math.max(result[next], result[cur] + time[next]);

                if (--indegree[next] == 0) {
                    que.offer(next);
                }
            }
        }

        // 모든 작업을 완료하기 위한 최소 시간 = 가장 나중에 끝난 작업의 끝난 시간
        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, result[i]);
        }

        return max;
    }
}
