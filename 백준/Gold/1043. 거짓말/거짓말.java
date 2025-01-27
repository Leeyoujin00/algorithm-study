import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static int[] parents;
    static List<Integer> eList = new ArrayList<>();
    static ArrayList<Integer>[] partyList;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 분리집합 초기화
        parents = new int[n+1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int tn = Integer.parseInt(st.nextToken());

        for (int i = 0; i < tn; i++) {
            eList.add(Integer.parseInt(st.nextToken()));
        }

        partyList = new ArrayList[m];
        for (int i = 0; i < m; i++) {
            partyList[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int pn = Integer.parseInt(st.nextToken());

            int x = Integer.parseInt(st.nextToken());
            partyList[i].add(x);
            for (int j = 1; j < pn; j++) {
                int y = Integer.parseInt(st.nextToken());
                partyList[i].add(y);
                union(x,y);
            }
        }

        int cnt = 0;
        boolean flag;
        // 각 파티원들의 부모노드가 진실을 아는 사람인지 확인
        for (int i = 0; i < m; i++) {
            flag = true;
            for (int a: partyList[i]) {
                //System.out.println(findParent(a));
                if (eList.contains(findParent(a))) {
                    flag = false;
                    break;
                }
            }
            if (flag) cnt++;
        }

        System.out.println(cnt);
    }

    // 노드 x가 속한 집합의 루트 노드 반환
    static int findParent(int x) {
        if (parents[x] == x) return x;
        return parents[x] = findParent(parents[x]);
    }

    // x가 속한 집합과 y가 속한 집합 병합
    static void union(int x, int y) {
        int px = findParent(x);
        int py = findParent(y);

        // 진실을 아는 사람이 있을 경우, 그 사람을 집합의 부모로 만든다.
        if (eList.contains(py)) {
            int tmp = px;
            px = py;
            py = tmp;
        }
        parents[py] = px;
    }
}
