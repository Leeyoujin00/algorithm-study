import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n,m;
    static int[][] arr;
    static int[][] group;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static List<int[]> zeroQ = new ArrayList<>();
    static List<Integer> groupSize = new ArrayList<>();
    static boolean[][] visited;
    static int result = 0;
    // 각 그룹을 표시한다.

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        group = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 0) zeroQ.add(new int[] {i,j});
            }
        }

        visited = new boolean[n][m];
        int num = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && arr[i][j] == 1) {
                    setGroup(i, j, num++);
                }
            }
        }

        for (int[] z : zeroQ) {
            //boolean[] ck = new boolean[groupSize.size()+1];
            changeZero(z[0], z[1]);
        }

        System.out.print(result);
    }

    private static class Node {
        int x;
        int y;
        public Node (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static void setGroup(int x, int y, int num) {

        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(x,y));
        visited[x][y] = true;
        group[x][y] = num;

        int cnt = 1;
        while (!que.isEmpty()) {
            Node cur = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny] || arr[nx][ny] == 0) {
                    continue;
                }

                que.offer(new Node(nx, ny));
                visited[nx][ny] = true;
                group[nx][ny] = num;
                cnt++;
            }
        }

        groupSize.add(cnt);
    }
    // 바꿀 0이 있는 곳의 좌표에 인접한 그룹이 있는지 확인한다.
    private static void changeZero(int x, int y) {

        int size = 1;
        Set<Integer> check = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                continue;
            }
            int groupNum = group[nx][ny];
            if (check.contains(groupNum)) continue;
            if (groupNum != 0) { // 모양 크기가 해당 그룹의 크기만큼 증가
                size += groupSize.get(groupNum-1);
                check.add(groupNum);
            }
        }

        result = Math.max(result, size);
    }
}
