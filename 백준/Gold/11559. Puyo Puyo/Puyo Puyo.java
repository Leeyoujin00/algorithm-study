import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{

    static char[][] map;
    static int r = 12, c = 6;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new char[r][c];
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        int answer = 0;

        boolean flag = true;
        while (flag) {
            flag = simulation();
            if (flag) answer++;

//            for (int i = 0; i < r; i++) {
//                for (int j = 0; j < c; j++) {
//                    System.out.print(map[i][j]);
//                }
//                System.out.println();
//            }
//
//            System.out.println();
            //flag = false;
        }

        System.out.print(answer);
    }

    private static class Node {
        int x;
        int y;
        char color;

        public Node(int x, int y, char color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }

    private static boolean simulation() {
        boolean[][] visited = new boolean[r][c];
        //Queue<Node> que = new LinkedList<>();

        // 맨 밑 행에 대해 진행
        boolean flag = false;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (!visited[i][j] && map[i][j] != '.') {
                    if (bfs(visited, i, j)) {
                        flag = true;
                    }
                }
            }
        }
//        for (int i = 0; i < c; i++){
//            if (!visited[r-1][i] && map[r-1][i] != '.') {
//                if (bfs(visited,r-1,i)) {
//                    flag = true;
//                }
//            }
//        }

        goDown();

        return flag;
    }

    private static boolean bfs(boolean[][] visited, int x, int y) {
        Queue<Node> que = new LinkedList<>();
        ArrayList<int[]> pos = new ArrayList<>();

        que.offer(new Node(x,y,map[x][y]));
        pos.add(new int[] {x,y});
        visited[x][y] = true;

        int cnt = 1;

        while(!que.isEmpty()) {
            Node cur = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= r || ny < 0 || ny >= c || visited[nx][ny]) continue;
                if (cur.color != map[nx][ny]) continue;

                que.offer(new Node(nx,ny,cur.color));
                visited[nx][ny] = true;
                pos.add(new int[] {nx,ny});
                cnt++;
            }
        }

        //System.out.println("색: " + map[x][y] + "개수 :" + cnt);

        if (cnt < 4) return false;
        // 뿌요들을 터뜨린다.
        for (int[] p : pos) {
            map[p[0]][p[1]] = '.';
        }

        return true;
    }

    // 인자로 받은 좌표 위의 원소들을 모두 밑으로 떨어트린다.
    private static void goDown() {

        Queue<Character> que = new LinkedList<>();


        for (int i = 0; i < c; i++) {
            for (int j = r-1; j >= 0; j--) {
                if (map[j][i] != '.') {
                    que.offer(map[j][i]);
                    map[j][i] = '.';
                }
            }
            for (int k = r-1; k >= 0; k--) {
                if (!que.isEmpty()) {
                    map[k][i] = que.poll();
                }
                else break;
            }
        }
    }
}
