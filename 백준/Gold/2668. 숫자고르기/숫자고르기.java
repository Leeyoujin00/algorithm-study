import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    static int n;
    static int[] list;
    static boolean[] visited;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        list = new int[n+1];

        for (int i = 1; i <= n; i++) {
            list[i] = Integer.parseInt(br.readLine());
        }

        visited = new boolean[n+1];
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                pq.clear();
                // 사이클 생성되지 않는 경로임
                if (!dfs(i,i)) {
                    // 방문한 노드들 모두 방문 X 샹태로 복구
                    while(!pq.isEmpty()) {
                        visited[pq.poll()] = false;
                    }
                }
                else { // 결과에 저장
                    while(!pq.isEmpty()) {
                        result.add(pq.poll());
                    }
                }
            }
        }

        Collections.sort(result);
        StringBuilder sb = new StringBuilder(result.size() + "\n");
        for (int r : result) {
            sb.append(r + "\n");
        }

        System.out.print(sb);
    }

    private static boolean dfs(int curIdx, int startIdx) {

        visited[curIdx] = true;
        pq.offer(curIdx);
        
        int nextIdx = list[curIdx];
        if (nextIdx == startIdx) { // 사이클 생성됐다면, true 반환
            return true;
        }
        // 만약 방문한 적 없는 노드라면, 탐색 진행
        if (!visited[nextIdx]) {
            return dfs(nextIdx, startIdx);
        }

        return false; // 사이클 생성 안되고, 방문한 적 있는 노드라면 탐색 진행 X
    }
}
