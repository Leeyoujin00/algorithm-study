import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m;
    static List<Integer>[] indegree;
    static int[] inNum;
    static List<Integer> answer = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        indegree = new List[n+1];
        inNum = new int[n+1];

        for (int i = 0; i <= n; i++) {
            indegree[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            indegree[a].add(b);
            inNum[b]++;
        }

        solution();

        for(int a: answer) {
            System.out.print(a + " ");
        }

    }

    // 위상정렬 알고리즘
    private static void solution() {
        Queue<Integer> que = new LinkedList<>();
        boolean[] check = new boolean[n+1];

        // 차수 0인 노드를 큐에 넣음
        for (int i = 1; i <= n; i++) {
            if (inNum[i] == 0) {
                que.offer(i);
            }
        }

        while(!que.isEmpty()) {
            int node = que.poll();
            check[node] = true;
            answer.add(node);

            for (int next : indegree[node]) {
                if(--inNum[next] == 0) {
                    que.offer(next);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (!check[i]) answer.add(i);
        }
    }
}
