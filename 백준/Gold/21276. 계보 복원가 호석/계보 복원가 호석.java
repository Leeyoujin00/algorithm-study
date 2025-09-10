import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m;
    static int[] indegree; // 자신의 선행 노드 개수 저장
    static List<Integer>[] graph;
    static String[] names;
    static List<String> starts = new ArrayList<>();
    static Map<String, Integer> map;
    static List<String>[] result;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new HashMap<>();
        names = new String[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            names[i] = st.nextToken();
            map.put(names[i], i);
        }

        graph = new ArrayList[n];
        result = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            result[i] = new ArrayList<>();
        }

        indegree = new int[n];
        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();

            indegree[map.get(a)]++;
            graph[map.get(b)].add(map.get(a));
        }

        topologicalSort();
    }

    private static void topologicalSort() {

        PriorityQueue<String> pq = new PriorityQueue<>();
        boolean[] ck = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                starts.add(names[i]);
                pq.offer(names[i]);
            }
        }

        System.out.println(starts.size());
        Collections.sort(starts);
        for (String name : starts) {
            System.out.print(name + " ");
        }
        System.out.println();

        while(!pq.isEmpty()) {

            int cur = map.get(pq.poll());

            for (int next : graph[cur]) {
                if (--indegree[next] == 0) {
                    pq.offer(names[next]);
                    result[cur].add(names[next]); // 자식 이름 저장
                }
            }
        }

        ArrayList<String> ans = new ArrayList<>();
        // 각각 자식 이름 사전순 정렬
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder(names[i]);
            Collections.sort(result[i]);
            sb.append(" " + result[i].size() + " ");
            for (String child : result[i]) sb.append(child + " ");

            ans.add(sb.toString());
        }

        Collections.sort(ans);
        for (String str : ans) {
            System.out.println(str);
        }
    }
}
