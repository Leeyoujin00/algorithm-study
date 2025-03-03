import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static List<Node>[] graph;
    static int[] set;
    static int[][] edges;
    static boolean[] selected;
    static int max = 0;

    static class Node {
        int v;
        int cost;
        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        set = new int[n+1];
        for (int i = 0; i <= n; i++) {
            set[i] = i;
        }

        edges = new int[m][3];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b,c));
            graph[b].add(new Node(a,c));
            edges[i][0] = a;
            edges[i][1] = b;
            edges[i][2] = c;
        }

        Arrays.sort(edges, (o1,o2) -> Integer.compare(o1[2], o2[2]));

        int sum = 0;
        for (int i = 0; i < m; i++) {
            // 두 정점이 속한 집합 구함
            int s1 = find(edges[i][0]);
            int s2 = find(edges[i][1]);

            // 사이클 만들지 않는다면 선택
            if (s1 != s2) {
                union(edges[i][0], edges[i][1]);
                sum += edges[i][2];
                max = Math.max(max, edges[i][2]);
            }
        }

        // 최소 스패닝 트리 구하고, 가장 값이 큰 간선만 빼준다.
        System.out.println(sum - max);
    }

    public static int find(int x) {

        if (x == set[x]) return x;
        else return set[x] = find(set[x]);
    }

    public static void union(int x, int y) {

        x = find(x);
        y = find(y);

        if (x == y) return;
        if (x < y) set[y] = x;
        else set[x] = y;
    }
}
