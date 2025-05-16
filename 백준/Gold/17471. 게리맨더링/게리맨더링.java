import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int totalPop = 0;
    static int[] population;
    static List<Integer>[] graph;
    static int min = Integer.MAX_VALUE; // 인구수 차이의 최솟값
    static boolean flag = false; // 두 선거구로 나누는 것이 가능한지 여부

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 한 선거구에 포함되어 있는 구역은 모두 연결되어 있어야 함
        // 인접한 구역을 통해서 이동할 수 있다면, 두 구역은 연결된 것임
        // 두 선거구에 포함된 인구의 차이를 최소로 하려고 함
        n = Integer.valueOf(br.readLine());
        population = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            population[i] = Integer.valueOf(st.nextToken());
            totalPop += population[i];
        }

        graph = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 인접 구역 정보 저장
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.valueOf(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                graph[i].add(Integer.valueOf(st.nextToken()));
            }
        }

        int[] region;
        for (int i = 1; i < n; i++) {
            region = new int[i];
            backtracking(i, 1,0,region);
        }

        if (flag) System.out.print(min);
        else System.out.print(-1);

    }

    // 조합을 구한다.
    private static void backtracking(int size, int start, int r, int[] region) {

        if (size == r) {
            if (bfs(region) && bfs(getOtherRegion(region))) {
                flag = true;
                min = Math.min(min, cntDiff(region));
            }
            return;
        }

        for (int i = start; i <= n; i++) {
            region[r] = i;
            backtracking(size, i+1, r+1, region);
        }
    }

    // 선택된 정점들이 연결돼있는지 확인한다.
    private static boolean bfs(int[] region) {

        // 선택된 정점들 체크
        boolean[] ck = new boolean[n+1];
        for (int r: region) {
            ck[r] = true;
        }

        Queue<Integer> que = new LinkedList<>();
        que.offer(region[0]);
        boolean[] v = new boolean[n+1];
        v[region[0]] = true;

        while (!que.isEmpty()) {

            int curNode = que.poll();

            for (int i = 0; i < graph[curNode].size(); i++) {
                int nextNode = graph[curNode].get(i);
                if (ck[nextNode] && !v[nextNode]) {
                    v[nextNode] = true;
                    que.offer(nextNode);
                }
            }

//            for (int nextNode : graph[curNode]) {
//                if (ck[nextNode] && !v[nextNode]) {
//                    v[nextNode] = true;
//                    que.offer(nextNode);
//                }
//            }
        }

        for (int r : region) {
            if (!v[r]) return false;
        }

        return true;

    }

    private static int[] getOtherRegion(int[] region) {
        int size = n - (region.length);
        int[] otherRegion = new int[size];

        int idx = 0;
        for (int i = 1; i <= n; i++) {
            boolean flag = true;
            for (int j = 0; j < region.length; j++) {
                if (region[j] == i) {
                    flag = false;
                    break;
                }
            }
            if (flag) otherRegion[idx++] = i;
        }

        return otherRegion;
    }

    private static int cntDiff(int[] region) {

        int sum = 0;
        for (int r: region) {
            sum += population[r];
        }
        int other = totalPop-sum;

        return Math.abs(sum-other);
    }
}
