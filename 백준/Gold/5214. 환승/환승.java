import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,k,m;
    static int[][] hyperTube;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        hyperTube = new int[m][k];
        // 각 노드는 자신을 포함하는 하이퍼튜브의 번호를 저장한다.
        graph = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            // 하나의 하이퍼튜브로 연결된 역들 사이의 거리는 모두 1이다.
            for (int j = 0; j < k; j++) {
                hyperTube[i][j] = Integer.parseInt(st.nextToken());
                graph[hyperTube[i][j]].add(i);
            }
        }

        System.out.print(bfs());
    }

    private static class Node {
        int idx;
        int step;

        public Node(int idx, int step) {
            this.idx = idx;
            this.step = step;
        }
    }
    private static int bfs() {

        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(1,1));
        // 각 역의 방문 여부
        boolean[] visited = new boolean[n+1];
        visited[1] = true;

        while (!que.isEmpty()) {
            Node cur = que.poll();
            int idx = cur.idx;
            int step = cur.step;

            if (idx == n) {
                return step;
            }

            // 현재 노드는, 자신이 포함된 하이퍼튜브들로 연결된 모든 역으로 이동 가능
            for (int hyperIdx : graph[idx]) {
                for (int nextNodeIdx : hyperTube[hyperIdx]) {
                    if (!visited[nextNodeIdx]) {
                        que.offer(new Node(nextNodeIdx, step+1));
                        visited[nextNodeIdx] = true;
                    }
                }
            }
        }

        return -1;
    }
}
