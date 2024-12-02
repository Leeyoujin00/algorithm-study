import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static List<Integer>[] relations;
    static boolean[] visited;
    static int[] score; // 각 회원의 점수 저장
    static int min;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        relations = new ArrayList[n+1];
        score = new int[n+1];

        for (int i = 0; i <= n; i++) {
            relations[i] = new ArrayList<>();
        }

        // 친구관계 저장
        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == -1) {
                break;
            }

            relations[a].add(b);
            relations[b].add(a);
        }

        min = 50;
        for (int i = 1; i <= n; i++) {
            bfs(i);
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (score[i] == min) {
                result.add(i);
            }
        }

        System.out.println(min + " " + result.size());
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i) + " ");
        }

    }

    // 각 회원의 점수 계산
    static void bfs(int member) {

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {member, 0});
        visited = new boolean[n+1];
        visited[member] = true;
        int maxDepth = 0;

        while (!queue.isEmpty()) {

            int m = queue.peek()[0];
            int depth = queue.poll()[1];

            // 해당 회원과 친구인 회원 방문 처리
            for (int i = 0; i < relations[m].size(); i++) {
                int next = relations[m].get(i);
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(new int[] {next, depth+1});
                    maxDepth = Math.max(maxDepth, depth+1);
                }
            }
        }

        score[member] = maxDepth;
        min = Math.min(min, maxDepth);

    }
}
