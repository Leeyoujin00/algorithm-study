import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n,m;
    static int[] parent;
    /**
     * Union-Find 알고리즘 활용.
     * 같은 부모 가지면 연결 O, 아니면 연결 X
     */
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        // 초기화, 처음에는 자신을 부모노드로 가진다.
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int c = Integer.parseInt(st.nextToken());
                // 연결되어있다면, union 수행
                if (c == 1) {
                    union(i,j);
                }
            }
        }

//        for (int p: parent) System.out.print(p + " ");
//        System.out.println();

        st = new StringTokenizer(br.readLine());
        int prev = Integer.parseInt(st.nextToken())-1;
        int next;
        for (int i = 1; i < m; i++) {
            next = Integer.parseInt(st.nextToken())-1;
            if (!isConnected(prev, next)) {
                System.out.print("NO");
                return;
            }
            prev = next;
        }

        System.out.print("YES");

    }

    public static boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    // 부모노드를 반환한다.
    public static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    // 두 노드를 병합한다. (같은 부모를 갖도록 만듦)
    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return;

        if (x > y) parent[y] = x;
        else parent[x] = y;
    }
}
