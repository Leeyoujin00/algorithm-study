import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n; // 사람 수
    static int m; // 친구관계 수
    static Map<Integer, List<Integer>> hash;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        hash = new HashMap<>();
        for (int i = 0; i < n; i++) {
            hash.put(i, new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            hash.get(a).add(b);
            hash.get(b).add(a);
        }

        visited = new boolean[n];
        //dfs(0, 0);

        for(int i = 0; i < n; i++) {
            visited[i] = true;
            dfs(i,1);
            visited[i] = false;
        }
        System.out.print(0);

    }

    static void dfs(int start, int r) {

        if (r == 5) {
            System.out.print(1);
            System.exit(0);
        }


        for (int i = 0; i < hash.get(start).size(); i++) {
            if (!visited[hash.get(start).get(i)]) {
                visited[hash.get(start).get(i)] = true;
                dfs(hash.get(start).get(i), r+1);
                visited[hash.get(start).get(i)] = false;
            }
        }
    }
}
