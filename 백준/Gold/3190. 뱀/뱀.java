import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n,k,l;
    static int[][] map;
    // 우, 하, 좌, 상 순서
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    static Queue<Move> moveList = new LinkedList<>();
    // 뱀이 있는 곳들의 좌표를 저장하는 덱
    static Deque<Pos> snake = new LinkedList<>();

    private static class Move {
        int x;
        char c;

        public Move(int x, char c) {
            this.x = x;
            this.c = c;
        }
    }

    private static class Pos {
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        map = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            map[r][c] = 1; // 사과가 있는 칸
        }

        l = Integer.parseInt(br.readLine());
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())+1;
            char c = st.nextToken().charAt(0);
            moveList.offer(new Move(x,c));
        }

        // 처음에는 맨 왼쪽 위에 뱀이 위치한다.
        snake.offer(new Pos(0,0));
        map[0][0] = 2;
        // 처음 방향은 오른쪽을 향한다.
        int dir = 0;

        boolean flag = true;
        int time = 0;

        while (flag) {
            time++;
            if (!moveList.isEmpty() && moveList.peek().x == time) {
                Move mv = moveList.poll();
                if (mv.c == 'D') dir = (dir+1) % 4;
                else {
                    if (--dir < 0) {
                        dir = 3;
                    }
                }
            }
            flag = move(dir);
//            System.out.println("======== " + time + " ========");
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println();
//            }

        }
        System.out.print(time);
    }

    private static boolean move(int dir) {

        // 뱀의 현재 머리 위치
        Pos head = snake.peekFirst();
        // 뱀의 현재 꼬리 위치
        Pos tail = snake.peekLast();
        // 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
        int nr = head.r + dr[dir];
        int nc = head.c + dc[dir];

        // 만약 벽이나 자기자신의 몸과 부딪히면 게임이 끝난다.
        if (nr < 0 || nr >= n || nc < 0 || nc >= n || map[nr][nc] == 2) {
            return false;
        }

        // 만약 이동한 칸에 사과가 있다면
        if (map[nr][nc] == 1) {
            // 그 칸에 있던 사과는 없어지고 꼬리는 움직이지 않는다.
            map[nr][nc] = 2;
            snake.offerFirst(new Pos(nr,nc));
        }
        else {
            // 만약 이동한 칸에 사과가 없다면
            // 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다.
            // (즉, 몸길이는 변하지 않는다.)
            map[nr][nc] = 2;
            snake.offerFirst(new Pos(nr,nc));
            map[tail.r][tail.c] = 0;
            snake.pollLast();
        }
        return true;
    }
}
