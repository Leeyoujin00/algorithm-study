import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int r,c;
    static char[][] board;
    static int[] dx = {1,1,1,0,0,0,-1,-1,-1};
    static int[] dy = {-1,0,1,-1,0,1,-1,0,1};
    static int[] dirs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        board = new char[r][c];
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        String directions = br.readLine();
        dirs = new int[directions.length()];

        for (int i = 0; i < directions.length(); i++) {
            dirs[i] = Integer.parseInt(Character.toString(directions.charAt(i)))-1;
        }

        int mv = 0;
        for (int d : dirs) {
            Queue<Node> arduino = new LinkedList<>();
            Node cur = new Node(0,0);

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (board[i][j] == 'I') {
                        cur.x = i;
                        cur.y = j;
                    }
                    else if (board[i][j] == 'R') {
                        arduino.offer(new Node(i,j));
                    }
                }
            }

            mv++;
            if (!simulation(d, cur, arduino)) {
                System.out.print("kraj " + mv);
                return;
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }

    }

    private static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static boolean simulation(int direction, Node cur, Queue<Node> arduino) {

        // 1. 종수가 아두이노를 8가지 방향으로 이동시키거나, 그 위치에 그대로 놔둔다.

        // 2. 종수의 아두이노가 미친 아두이노가 있는 칸으로 이동한 경우에는,
        //      게임이 끝나게 되며, 종수는 게임을 지게 된다.
        if (board[cur.x + dx[direction]][cur.y + dy[direction]] == 'R') {
            return false;
        }

        board[cur.x][cur.y] = '.';
        cur.x += dx[direction];
        cur.y += dy[direction];
        board[cur.x][cur.y] = 'I';

        boolean[][] v = new boolean[r][c];
        // 3. 미친 아두이노는 8가지 방향 중에서 종수의 아두이노와 가장 가까워지는 방향으로 한 칸 이동한다.

        //ArrayList<Node> nextArduino = new ArrayList<>();
        while (!arduino.isEmpty()) {
            Node node = arduino.poll();

            int mvDir = 0;
            int minDistance = 10000;
            int nx = 0, ny = 0;

            for (int i = 0; i < 9; i++) {
                // 한 칸 이동해야 하므로, 그대로인 좌표는 진행 X
                if (i == 4) continue;

                nx = node.x + dx[i];
                ny = node.y + dy[i];
                if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
                    continue;
                }
                int d = getDistance(cur.x, cur.y, nx, ny);

                if (minDistance > d) {
                    minDistance = d;
                    mvDir = i;
                }
            }

            //System.out.println(mvDir + " = 미친아두이노 이동방향");
            nx = node.x + dx[mvDir];
            ny = node.y + dy[mvDir];
            // 미친 아두이노가 종수가 있는 칸으로 이동하면 게임 종료, 종수가 지게 됨
            if (board[nx][ny] == 'I') {
                return false;
            }

            if (!v[node.x][node.y]) {
                board[node.x][node.y] = '.';
            }

            // 5. 2개 또는 그 이상의 미친 아두이노가 같은 칸에 있는 경우에는 큰 폭발이 일어나고,
            //    그 칸에 있는 아두이노는 모두 파괴된다.
            if (v[nx][ny]) {
                board[nx][ny] = '.';
            }
            else {
                v[nx][ny] = true;
                board[nx][ny] = 'R';
            }
        }

        return true;
    }

    private static int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
