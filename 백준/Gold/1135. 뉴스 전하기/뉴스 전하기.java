import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static List<Integer>[] list; // 자식 노드를 저장
    static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        list = new ArrayList[n];
        dp = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rt = 0; // 루트 노드
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
            int a = Integer.parseInt(st.nextToken());
            if (a == -1) {
                rt = i;
            } else {
                list[a].add(i);
            }
        }

        System.out.print(solve(rt));
    }

    // 인자로 주어진 노드가 루트인 트리의 수행 시간을 반환
    static int solve(int idx) {
        // DFS 수행, 자식노드들 먼저 수행시간을 구한다.
        for (int nxt : list[idx]) {
            dp[nxt] = 1 + solve(nxt);
        }

        // 깊이 더 깊은 서브트리를 먼저 수행해야 함. 정렬
        Collections.sort(list[idx], new DepthComp());

        // 자식들 중 가장 오래 걸리는 서브트리 시간을 구함
        int res = 0;
        for (int i = 0; i < list[idx].size(); i++) {
            int num = list[idx].get(i);
            dp[num] += i;
            res = Math.max(res, dp[num]);
        }

        return res;
    }


    // 자식노드가 많은 트리를 먼저 탐색해야 한다. (깊이가 깊은 서브트리를 먼저 탐색)
    static class DepthComp implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            // dp값(트리 깊이) 기준 내림차순 정렬
            return dp[o2] - dp[o1];
        }
    }
}
