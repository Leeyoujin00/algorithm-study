import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m;
    static int[][] map1;
    static int[][] map2;
    // 상하좌우
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean[][] v;

    static class Node {
        int x;
        int y;
        public Node (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map1 = new int[n][m];
        map2 = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map2[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean ck = false;
        v = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map1[i][j] != map2[i][j] && !v[i][j]) {
                    if (ck == true) {
                        System.out.print("NO");
                        System.exit(0);
                    }
                    else {
                        if (solve(i,j,map2[i][j])) {
                            ck = true;
                        }
                        else {
                            System.out.print("NO");
                            System.exit(0);
                        }
                    }
                }
            }
        }

        System.out.print("YES");


    }

    private static boolean solve(int x, int y, int data) {

        int[][] cloneArr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cloneArr[i][j] = map1[i][j];
            }
        }

        Queue<Node> que = new LinkedList<>();
        v[x][y] = true;
        que.offer(new Node(x, y));
        cloneArr[x][y] = data;

        while (!que.isEmpty()) {
            Node cur = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                // 인접한 같은 데이터 가진 영역으로 전파
                if (0 <= nx && nx < n && 0 <= ny && ny < m && !v[nx][ny] && map1[nx][ny] == map1[x][y]) {
                    que.offer(new Node(nx,ny));
                    v[nx][ny] = true;
                    cloneArr[nx][ny] = data;
                }
            }
        }

        if (isIdentical(cloneArr)) return true;
        return false;
    }

    // 두 배열이 일치하는지 확인
    private static boolean isIdentical(int[][] arr) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] != map2[i][j]) return false;
            }
        }
        return true;
    }
}
