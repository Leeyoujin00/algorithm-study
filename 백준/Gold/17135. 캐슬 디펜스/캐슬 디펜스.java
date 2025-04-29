import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m,d;
    static int[][] map;
    static int[] pos;
    static int max = 0;
    static int enemy = 0;
    // 좌 상 우
    static int[] dx = {0,-1,0};
    static int[] dy = {-1,0,1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) enemy++;
            }
        }

        pos = new int[3];
        place(pos,0,0);

        System.out.print(max);
    }

    // m개의 성 위치 중 궁수가 위치할 곳 3개의 위치 선정
    private static void place(int[] pos, int r, int start) {

        if (r == 3) {
            max = Math.max(max, solve(pos));
            return;
        }

        for (int i = start; i < m; i++) {
            pos[r] = i;
            place(pos, r+1, i);
        }
    }

    static class Node {
        int x;
        int y;
        int distance;
        int from; // 몇번 궁수의 공격인지를 나타냄

        public Node(int x, int y, int distance, int from) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.from = from;
        }
    }

    // 궁수의 공격으로 제거할 수 있는 적의 최대 수 반환
    private static int solve(int[] pos) {

        int cnt = 0, out = 0;
        int[][] arr = copyMap();

        // 1. 거리가 가장 가까움 2. 가장 왼쪽에 있음
        PriorityQueue<Node> que = new PriorityQueue<>((o1,o2) -> o1.distance != o2.distance ? Integer.compare(o1.distance,o2.distance) : Integer.compare(o1.y, o2.y));

        boolean[][] v;
        boolean[] valid;
        // 모든 적이 격자판에서 제외되면 게임이 끝난다.
        while (out < enemy) {

            v = new boolean[n][m];
            for (int i = 0; i < 3; i++) {
                que.offer(new Node(n, pos[i], 0,i));
            }

            valid = new boolean[3];
            Arrays.fill(valid, true);

            while (!que.isEmpty()) {
                Node cur = que.poll();

                int x = cur.x;
                int y = cur.y;
                int distance = cur.distance;
                int from = cur.from;

                for (int i = 0; i < 3; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    // 탐색 범위 벗어나거나, 이미 방문한 위치는 진행 x
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                    // 공격 가능한 거리 벗어났다면, 진행 x
                    if (distance+1 > d) continue;
                    if (!valid[from]) continue;

                    // 적이 있는 위치라면
                    if (arr[nx][ny] == 1) {
                        // 해당 공격이 사라진다.
                        //arr[nx][ny] = 0;
                        if (!v[nx][ny]) {
                            cnt++;
                            out++;
                        }
                        v[nx][ny] = true;
                        valid[from] = false;
                        continue;
                        // 해당 궁수의 공격이 유효하다면 (이미 사용되지 않았다면)
//                        if (valid[from]) {
//                            // 해당 공격이 사라진다.
//                            arr[nx][ny] = 0;
//                            cnt++;
//                            out++;
//                            valid[from] = false;
//                            continue;
//                        }
                    }

                    que.offer(new Node(nx, ny, distance+1, from));
                    //v[nx][ny] = true;
                }
            }
            // 적 제거
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (v[i][j]) arr[i][j] = 0;
                }
            }

            // 적들이 아래로 한 칸 이동한다.
            out += moveDown(arr);
        }


        return cnt;
    }

    private static int[][] copyMap() {

        int[][] cp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cp[i][j] = map[i][j];
            }
        }

        return cp;
    }

    private static int moveDown(int[][] arr) {
        int cnt = 0;

        for (int i = 0; i < m; i++) {
            if (arr[n-1][i] == 1) {
                cnt++;
                arr[n-1][i] = 0;
            }
        }

        for (int i = n-2; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1) {
                    arr[i][j] = 0;
                    arr[i+1][j] = 1;
                }
            }
        }

        return cnt;
    }
}
