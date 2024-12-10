import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<List<Integer>> graph;
    static int[] colors; // 처음에는 모든 정점이 색칠되지 않은 상태임.
    static final int RED = 1;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < K; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph = new ArrayList<>();
            colors = new int[v+1];

            // 그래프 초기화
            for (int vertex = 0; vertex <= v; vertex++) {
                graph.add(new ArrayList<>());
            }

            // 그래프 연결
            for (int edge = 0; edge < e; edge++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graph.get(from).add(to);
                graph.get(to).add(from);
            }

            boolean rst = false;
            // 1. 색칠되지 않은 모든 정점에 대해서
            for (int i = 0; i < v; i++) {
                if (colors[i] == 0) {
                    colors[i] = RED;
                    rst = isBipartiteGraph(i);
                }
                if (!rst) break;
            }
            
            if (rst) System.out.println("YES");
            else System.out.println("NO");
        }

    }

    public static boolean isBipartiteGraph(int node) {

        // 현재 노드의 모든 인접 노드 방문
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int i = 0; i < graph.get(now).size(); i++) {
                int next = graph.get(now).get(i);
                // 만약 아직 색칠되지 않았다면, 현재 노드와 다른 색으로 칠해줌
                if (colors[next] == 0) {
                    colors[next] = colors[now] * -1;
                    queue.add(next);
                }
                // 만약 색칠되어 있고, 현재 노드와 같은 색이면 false 반환
                else if (colors[next] == colors[now]) {
                    return false;
                }
            }
        }

        return true;
    }
}
