import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m;
    static List<Node>[] graph;
    static int MAX = 1000000000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n+1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            // a번 섬과 b번 검 사이에 중량제한이 c인 다리가 존재
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        // 공장이 위치해있는 섬의 번호
        st = new StringTokenizer(br.readLine());
        int f1 = Integer.parseInt(st.nextToken());
        int f2 = Integer.parseInt(st.nextToken());


        System.out.print(dijkstra(f1, f2));

    }

    /**
     * 경로 탐색시, 중량제한 더 큰 값 선택하며 경로 탐색
     * 만약 목적지에 도달 못하고 끝나는 경로라면 고려X
     * 계산된 경로 중, 가장 중량제한 작은 값을 답으로 출력
     */

    static class Node {
        int num;
        int weight;

        public Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }
    }

    static class Node2 {
        int num;
        int weight;
        long wSum;

        public Node2(int num, int weight, long wSum) {
            this.num = num;
            this.weight = weight;
            this.wSum = wSum;
        }
    }

    // f1 -> f2 로 가는 경로 중, 중량제한 값들 중 최솟값 반환
    private static long dijkstra(int f1, int f2) {

        long min = Long.MAX_VALUE;

        // weight가 큰 경로 먼저 선택해야 함
        PriorityQueue<Node2> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o2.weight, o1.weight));
        pq.offer(new Node2(f1, 0, 0));
        long[] distance = new long[n+1];
        Arrays.fill(distance, Long.MAX_VALUE);
        distance[f1] = 0;

        while (!pq.isEmpty()) {
            Node2 node = pq.poll();
            //System.out.println("선택 노드 = " + node.num + "중량 = " + node.weight);

            distance[node.num] = node.wSum;
            if (f1 != node.num) {
                min = Math.min(min, node.weight); // 최단경로로 가는 weight중 최솟값
            }

            if (node.num == f2) {
                break;
            }

            for (Node next : graph[node.num]) {
                if (distance[next.num] > distance[node.num] + next.weight) {
                    pq.offer(new Node2(next.num, next.weight, distance[node.num] + next.weight));
                }
            }
        }

        return min;

    }
}
