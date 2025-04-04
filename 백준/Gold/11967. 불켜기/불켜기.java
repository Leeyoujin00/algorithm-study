import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m;
    static int[][] arr;
    static List<Pos>[][] linkedInfo;
    //static boolean[][] isLight; // 불 켜져있는지 나타내는 배열
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    static class Pos {
        int x;
        int y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][n];
        linkedInfo = new ArrayList[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                linkedInfo[i][j] = new ArrayList<Pos>();
            }
        }

        int x,y,a,b;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken())-1;
            y = Integer.parseInt(st.nextToken())-1;
            a = Integer.parseInt(st.nextToken())-1;
            b = Integer.parseInt(st.nextToken())-1;
            linkedInfo[x][y].add(new Pos(a, b));
        }

        int result = bfs();

        System.out.print(result);

    }

    // 베시는 불이 켜져있는 방으로만 들어갈 수 있고,
    // 각 방에서는 상하좌우에 인접한 방으로 움직일 수 있다.
    private static int bfs() {
        Queue<Pos> que = new LinkedList<>();
//        que.offer(new Pos(0,0));
        boolean[][] isLight = new boolean[n][n];
        isLight[0][0] = true;
//        boolean[][] v = new boolean[n][n];
//        v[0][0] = true;

        int count = 1;
        boolean flag = true;
        while(flag) {
            flag = false;
            que.offer(new Pos(0,0));
            boolean[][] v = new boolean[n][n];
            v[0][0] = true;
            while(!que.isEmpty()) {
                Pos cur = que.poll();

                // 현재 위치에서 불을 켤 수 있는 방의 불을 모두 켠다.
                for (Pos p : linkedInfo[cur.x][cur.y]) {
                    if (!isLight[p.x][p.y]) {
                        count++;
                        flag = true;
                    }
                    isLight[p.x][p.y] = true;
                }
                //System.out.println(cur.x + " " + cur.y + " = " + count);

                // 상하좌우의 인접한 방 중, 불이 켜져있는 방으로 이동한다.
                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n || v[nx][ny]) continue;
                    // 불이 꺼져있으면 방문 X
                    if (!isLight[nx][ny]) continue;

                    v[nx][ny] = true;
                    que.offer(new Pos(nx,ny));
                }
            }
        }

        return count;
    }
}
