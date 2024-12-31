import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] parents;
    //static List<Integer>[] child;
    //static List<Integer> delete = new ArrayList<>();
    static Map<Integer, List<Integer>> hash = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        parents = new int[n];

        for (int i = 0; i < n; i++) {
            //child[i] = new ArrayList<>();
            hash.put(i, new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int p = Integer.parseInt(st.nextToken());
            if (p != -1) { // 루트노드가 아니라면
                //child[p].add(i);
                parents[i] = p;
                hash.get(p).add(i);
            }
        }

        st = new StringTokenizer(br.readLine());
        int del = Integer.parseInt(st.nextToken());

        int delP = hash.get(parents[del]).size();
        dfs(del);

        int cnt = 0; // 자식 노드 없는 노드. 즉 리프 노드의 개수를 센다.
        for (List<Integer> l : hash.values()) {
            if (l.size() == 0) cnt++;
        }
        if (delP == 1) cnt++;

        //System.out.println(hash.get(0).get(0));
        System.out.print(cnt);
    }

    // 주어진 노드와 모든 자손 노드 제거
    static void dfs(int idx) {
        // 자식 노드가 있다면 자식 노드 삭제
        if (hash.get(idx).size() != 0) {
            for (int c: hash.get(idx)) {
                dfs(c);
            }
        }
        hash.remove(idx);
    }
}
