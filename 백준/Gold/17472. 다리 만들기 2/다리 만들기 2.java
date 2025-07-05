import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class Main {
    static int n,m;
    static int[][] map;
    static boolean[] ck; // 각 섬이 다리로 연결돼있는지 여부
    static List<Node> islandPos = new ArrayList<>(); // 각 섬의 좌표들 저장
    // 상하좌우
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    // 각 섬으로 가는 다리의 최단 길이
    static int[][] minDistance;
    // 섬개수
    static int cnt = 0;
    static final int INF = Integer.MAX_VALUE;
    // 그래프
    static List<GraphNode>[] graph;
    static class Node {
        List<int[]> positions;
        public Node() {
            positions = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] islands = new boolean[n][m];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!islands[i][j] && map[i][j] != 0) {
                    islandPos.add(new Node());
                    //map[i][j] = idx+1;
                    markIsland(i,j,islands, idx);
                    idx++;
                    cnt++;
                }
            }
        }

        minDistance = new int[cnt+1][cnt+1];
        for (int i = 0; i <= cnt; i++) {
            Arrays.fill(minDistance[i], INF);
        }

        // 각 노드에서 다른 노드로 가는 가장 가까운 다리의 길이를 구한다.
        for (Node island : islandPos) {
            for (int[] pos : island.positions) {
                for (int i = 0; i < 4; i++) {
                    int row = pos[0] + dx[i], col = pos[1] + dy[i];
                    if (row < 0 || row >= n || col < 0 || col >= m || map[row][col] != 0) continue;
                    dfs(row, col, i, 0, map[pos[0]][pos[1]]);
                }
            }
        }

        // 출력해봄
//        System.out.println();
//        for (int i = 1; i <= cnt; i++) {
//            for (int j = 1; j <= cnt; j++) {
//                System.out.print(minDistance[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();

        graph = new ArrayList[cnt+1];
        for (int i = 1; i <= cnt; i++) {
            graph[i] = new ArrayList<>();
            for (int j = 1; j <= cnt; j++) {
                if (i != j && minDistance[i][j] != INF) {
                    graph[i].add(new GraphNode(j, minDistance[i][j]));
                }
            }
        }

        System.out.print(prim(1));

    }

    private static class GraphNode {
        int num;
        int cost;

        public GraphNode(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }

    // 프림 알고리즘 수행 -> 모든 섬 연결하는 최단경로의 길이를 구함
    private static int prim(int start) {

        boolean[] visited = new boolean[cnt+1];
        // 선택할 정점을 cost 작은순으로 선택
        PriorityQueue<GraphNode> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        pq.offer(new GraphNode(start, 0));

        int totalCost = 0;
        int totalNode = 0;
        while(!pq.isEmpty()) {
            GraphNode cur = pq.poll();

            if (visited[cur.num]) continue;

            visited[cur.num] = true;
            totalCost += cur.cost;
            totalNode++;

            for (GraphNode next : graph[cur.num]) {
                if (!visited[next.num]) {
                    pq.offer(next);
                }
            }
        }

        if (totalNode < cnt) return -1;
        return totalCost;
    }


    // 각 다리의 모든 위치에서 네가지 방향으로 갔을 때, 만나는 섬이 있는지 확인한다.
    private static void dfs(int row, int col, int dir, int len, int islandNum) {
        
        if (map[row][col] == islandNum) return;
        // 다른 섬을 만났다면 탐색 종료
        if (map[row][col] != 0) {
            if (len < 2) {
                return;
            }
            minDistance[islandNum][map[row][col]] = Math.min(minDistance[islandNum][map[row][col]], len);
            minDistance[map[row][col]][islandNum] = Math.min(minDistance[map[row][col]][islandNum], len);
            return;
        }

        int nx = row + dx[dir], ny = col + dy[dir];
        if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
            return;
        }

        dfs(nx, ny, dir, len+1, islandNum);
    }

    private static void markIsland(int row, int col, boolean[][] islands, int idx) {
        islands[row][col] = true;
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] {row, col});
        map[row][col] = idx+1;
        islandPos.get(idx).positions.add(new int[] {row, col});

        while(!que.isEmpty()) {
            int[] cur = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || islands[nx][ny] || map[nx][ny] == 0) continue;

                islands[nx][ny] = true;
                que.offer(new int[] {nx,ny});
                islandPos.get(idx).positions.add(new int[] {nx,ny});
                map[nx][ny] = idx+1;
            }
        }
    }
}
