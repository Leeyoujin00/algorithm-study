import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;

    private static int find(int x) {
        if (x == parent[x]) return x;
        return find(parent[x]);
    }

    private static int findParent(int x) {
        if (x == parent[x]) return x;
        return parent[x] = findParent(parent[x]);
    }

    private static boolean union(int x, int y) {

        int p1 = findParent(x);
        int p2 = findParent(y);

        // 두 노드가 이미 같은 집합에 포함되어 있으면, 사이클 발생 의미
        if (p1 == p2) return false;
        parent[p2] = p1;
        //else if (p2 < p1) parent[p1] = p2;
        return true;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 최상위 부모의 개수가 곧 그래프의 트리 개수이다.
        // 따라서, 분리집합 알고리즘을 통해 최상위 부모의 개수를 센다.
        StringBuilder sb = new StringBuilder();
        int t = 0; // 테스트케이스 수
        while (true) {
            t++;
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) break;

            // 분리집합 초기화
            parent = new int[n+1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }

            // 간선의 두 노드가 이미 같은 집합에 포함되어 있으면 사이클 발생한 것임
            // 사이클이 발생한 자식 노드들 저장
            Set<Integer> cycledChildSet = new HashSet<>();
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                if (!union(x,y)) {
                    cycledChildSet.add(x);
                    cycledChildSet.add(y);
                }
            }

            sb.append("Case " + t + ": ");
            Set<Integer> hashSet = new HashSet<>();
            for (int i = 1; i <= n; i++) {
                hashSet.add(parent[i]);
            }
            Set<Integer> resultP = new HashSet<>();
            for (int p: hashSet) {
                resultP.add(findParent(p));
            }

            Set<Integer> cycledParentSet = new HashSet<>();
            // 사이클 발생한 자식 노드들의 부모노드 (사이클의 수)를 찾는다.
            for (int node : cycledChildSet) {
                cycledParentSet.add(find(node));
            }

//            for (int p: resultP) System.out.print(p + " ");
//            System.out.println();
//            for (int p : cycledParentSet) System.out.print(p + " ");
//            System.out.println("================");

            int trees = resultP.size() - cycledParentSet.size();
            if (trees == 0) {
                sb.append("No trees.\n");
            }
            else if (trees == 1) {
                sb.append("There is one tree.\n");
            }
            else {
                sb.append("A forest of " + trees + " trees.\n");
            }

        }

        System.out.print(sb);
    }
}
