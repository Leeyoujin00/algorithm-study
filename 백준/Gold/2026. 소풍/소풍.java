import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,k,f;
    static boolean[] selected;
    static boolean[][] isFriend; // 친구관계 인접행렬
    static int[] friendCnt;
    static boolean isDone = false;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        f = Integer.parseInt(st.nextToken());

        isFriend = new boolean[n+1][n+1];
        friendCnt = new int[n+1];
        for (int i = 0; i < f; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            isFriend[a][b] = true;
            isFriend[b][a] = true;

            friendCnt[a]++;
            friendCnt[b]++;
        }

        selected = new boolean[n+1];
        for (int i = 1; i <= n; i++) {
            // 친구가 k-1명 미만인 사람은 검증할 필요 없음
            if (friendCnt[i] < k-1) continue;
            selected[i] = true;
            dfs(i, 1);
            selected[i] = false;
        }

        if (!isDone) System.out.print(-1);

    }

    // dfs 의 cur 노드는 항상 선택된 노드여야 한다.
    public static void dfs(int cur, int depth) {

        if (isDone) return;

        if (depth == k) {
            isDone = true;
            // 출력
            print();
            return;
        }

        // cur의 친구중, selected 멤버들과도 모두 친구인 경우, selected에 포함
        for (int i = 1; i <= n; i++) {
            if (isFriend[cur][i]) { // cur의 친구임
                if (check(i)) { // selected로 선택 가능
                    selected[i] = true;
                    dfs(i, depth+1);
                    selected[i] = false;
                }
            }
        }
    }

    // selected 멤버들과 모두 친구인지 확인
    private static boolean check(int target) {
        for (int i = 1; i <= n; i++) {
            if (selected[i] && !isFriend[i][target]) return false;
        }
        return true;
    }

    public static void print() {
        for (int i = 1; i <= n; i++) {
            if (selected[i]) System.out.println(i);
        }
    }
}
