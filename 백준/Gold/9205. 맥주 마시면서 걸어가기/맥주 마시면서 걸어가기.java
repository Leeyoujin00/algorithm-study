import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static int t, n;
    static int[][] pos;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine());
            pos = new int[n+2][2];

            for (int j = 0; j < n+2; j++) {
                st = new StringTokenizer(br.readLine());
                pos[j][0] = Integer.parseInt(st.nextToken());
                pos[j][1] = Integer.parseInt(st.nextToken());
            }

            if (bfs()) {
                System.out.println("happy");
            }
            else System.out.println("sad");
        }
    }

    // 시작지점 부터 인접노드 bfs 로 탐색, 목적지 도착 여부 반환
    static boolean bfs() {

        // 각 노드별 인접 노드 저장
        List<Integer>[] edges = new ArrayList [pos.length];
        for (int i = 0; i < pos.length; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < pos.length; i++) {
            for (int j = 0; j < pos.length; j++) {
                // 두 노드간 거리가 1000이하이면 연결된 것임
                if (Math.abs(pos[i][0] - pos[j][0]) + Math.abs(pos[i][1] - pos[j][1]) <= 1000) {
                    edges[i].add(j);
                    edges[j].add(i);
                }
            }
        }

        // 시작노드부터 연결노드들 탐색
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n+2];
        queue.add(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int i = 0; i < edges[now].size(); i++) {
                int next = edges[now].get(i);
                // 마지막 노드(목적지)에 도달했으면 true 반환
                if (next == pos.length-1) {
                    return true;
                }
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }

        return false;
    }


}
