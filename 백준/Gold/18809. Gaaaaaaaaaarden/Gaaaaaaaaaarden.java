import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m,g,r;
    static int[][] arr;
    static List<int[]> groundList = new ArrayList<>();
    static int groundCnt = 0;
    static int maxFlower = 0;
    // 탐색방향, 상하좌우
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                // 배양액을 뿌릴 수 있는 땅(2)을 저장한다.
                if (arr[i][j] == 2) {
                    groundList.add(new int[] {i,j});
                    groundCnt++;
                }
            }
        }

        int[] selected = new int[g+r];
        selectGround(0,0,selected);

        System.out.print(maxFlower);

    }

    // g+r 개의 땅을 선택한다. (조합)
    private static void selectGround(int cnt, int start, int[] selected) {
        // 선택 완료시, 초록 땅/빨간 땅 조합 진행
        if (cnt == g+r) {
            int[] green = new int[g];
            selectGreen(0,0,green,selected);
            return;
        }

        for (int i = start; i < groundCnt; i++) {
            selected[cnt] = i;
            selectGround(cnt+1, i+1, selected);
        }

    }



    // 초록 땅을 선택한다.
    private static void selectGreen(int cnt, int start, int[] green, int[] selected) {
        // 선택 완료 시, 피울 수 있는 꽃의 개수 구함
        if (cnt == g) {
            callBfs(green, selected);
            return;
        }

        for (int i = start; i < g+r; i++) {
            green[cnt] = i;
            //v[i] = true;
            selectGreen(cnt+1, i+1, green, selected);
        }
    }

    // 초록 땅과 빨간 땅을 구해 bfs를 호출한다.
    private static void callBfs(int[] green, int[] ground) {

        int[] red = new int[r];
        boolean[] v = new boolean[g+r];
        for (int i = 0; i < g; i++) {
            v[green[i]] = true;
        }
        int[] greenArr = new int[g];
        for (int i = 0; i < g; i++) {
            greenArr[i] = ground[green[i]];
        }
        int idx = 0;
        for (int i = 0; i < g+r; i++) {
            if (!v[i]) {
                red[idx++] = ground[i];
            }
        }
        bfs_(greenArr, red);
    }

    private static class Ground {
        int row;
        int col;
        char color;
        public Ground(int row, int col, char color) {
            this.row = row;
            this.col = col;
            this.color = color;
        }
    }

    private static class Node {
        int row;
        int col;
        char color;
        int time;

        public Node(int row, int col, char color, int time) {
            this.row = row;
            this.col = col;
            this.color = color;
            this.time = time;
        }
    }

    private static void bfs_(int[] green, int[] red) {
        // 탐색 큐
        Queue<Node> que = new LinkedList<>();
        //boolean[][] v = new boolean[n][m];
        int[][] greenTime = new int[n][m];
        int[][] redTime = new int[n][m];

        // 녹색 땅 표시
        for (int g : green) {
            int gr = groundList.get(g)[0], gc = groundList.get(g)[1];
            greenTime[gr][gc] = 1;
            que.offer(new Node(gr, gc, 'G', 1));
            //v[gr][gc] = true;
        }
        // 빨간 땅 표시
        for (int r : red) {
            int rr = groundList.get(r)[0], rc = groundList.get(r)[1];
            redTime[rr][rc] = 1;
            que.offer(new Node(rr, rc, 'R', 1));
            //v[rr][rc] = true;
        }

        int flower = 0;

        // 배양액을 전파
        while (!que.isEmpty()) {

            Node cur = que.poll();
            // 꽃이 피는지 확인
            if (greenTime[cur.row][cur.col] == redTime[cur.row][cur.col]) {
                continue;
            }

            // 상하좌우 전파
            for (int i = 0; i < 4; i++) {
                int nr = cur.row + dr[i];
                int nc = cur.col + dc[i];
                // 탐색범위 벗어나거나, 호수면 진행 X
                if (nr < 0 || nr >= n || nc < 0 || nc >= m || arr[nr][nc] == 0) {
                    continue;
                }

                // 초록 배양액일 경우
                if (cur.color == 'G') {
                    // 이미 초록 배양액 뿌린 경우
                    if (greenTime[nr][nc] > 0) {
                        continue;
                    }
                    // 이미 빨간 배양액 뿌린 경우
                    if (redTime[nr][nc] > 0 && redTime[nr][nc] <= cur.time) {
                        continue;
                    }
                    greenTime[nr][nc] = cur.time+1;

                    // 꽃이 피는 경우
                    if (redTime[nr][nc] == cur.time + 1) {
                        flower++;
                        continue;
                    }
                    que.offer(new Node(nr,nc,cur.color,cur.time+1));
                }

                // 빨간 배양액일 경우
                if (cur.color == 'R') {
                    // 이미 빨간 배양액 뿌렸다면
                    if (redTime[nr][nc] > 0) {
                        continue;
                    }
                    // 이미 초록 배양액 뿌렸다면
                    if (greenTime[nr][nc] > 0 && greenTime[nr][nc] <= cur.time) {
                        continue;
                    }
                    redTime[nr][nc] = cur.time+1;
                    // 꽃이 피는지 확인
                    if (greenTime[nr][nc] == cur.time+1) {
                        flower++;
                        continue;
                    }
                    que.offer(new Node(nr,nc,cur.color,cur.time+1));
                }
            }
        }

        maxFlower = Math.max(maxFlower, flower);

    }

//    private static void bfs(int[] green, int[] red) {
//
//        // 탐색 큐
//        Queue<Ground> que = new LinkedList<>();
//        boolean[][] visited = new boolean[n][m];
//        int[][] greenTime = new int[n][m];
//        int[][] redTime = new int[n][m];
//
//        // 녹색 땅 표시
//        for (int g : green) {
//            int gr = groundList.get(g)[0], gc = groundList.get(g)[1];
//            greenTime[gr][gc] = 0;
//            que.offer(new Ground(gr, gc, 'G'));
//            visited[gr][gc] = true;
//        }
//        // 빨간 땅 표시
//        for (int r : red) {
//            int rr = groundList.get(r)[0], rc = groundList.get(r)[1];
//            redTime[rr][rc] = 0;
//            que.offer(new Ground(rr, rc, 'R'));
//            visited[rr][rc] = true;
//        }
//
//        int flower = 0;
//        // 이번 초에 배양액이 옮겨간 땅을 저장
//        while (true) {
//            boolean[][][] ck = new boolean[n][m][2]; // 해당 위치에 꽃이 피는지 여부 [i][j][0] == 초록 도달 여부, [i][j][1] == 빨강 도달 여부
//            boolean flag = false;
//
//            while (!que.isEmpty()) {
//                Ground cur = que.poll();
//                int row = cur.row, col = cur.col;
//                char color = cur.color;
//
//                // 꽃이 폈는지 확인
//                if (color == 'G') {
//                    if (greenTime[row][col] == redTime[row][col]) {
//
//                    }
//                } else if (color == 'R') {
//
//                }
//
//                // 인접한 땅 탐색
//                for (int i = 0; i < 4; i++) {
//                    int nr = row + dr[i];
//                    int nc = col + dc[i];
//                    // 탐색범위 벗어나거나 이미 전파된 곳이면 진행 X
//                    if (nr < 0 || nr >= n || nc < 0 || nc >= m || visited[nr][nc]) {
//                        continue;
//                    }
//                    // 호수라면 진행 X
//                    if (arr[nr][nc] == 0) {
//                        continue;
//                    }
//                    //visited[nr][nc] = true;
//                    flag = true;
//                    // 꽃 피울 수 있는지 확인
//                    if (color == 'G') {
//                        ck[nr][nc][0] = true;
//                        //tmpList.add(new Ground(nr,nc,color));
//
//                    } else if (color == 'R') {
//                        ck[nr][nc][1] = true;
//                        //tmpList.add(new Ground(nr,nc,color));
//                    }
//                }
//            }
//            // 더 이상 옮겨갈 수 있는 땅이 없으면 종료
//            if (!flag) {
//                break;
//            }
//            // 꽃이 핀 땅을 구한다.
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < m; j++) {
//                    if (ck[i][j][0] && ck[i][j][1]) {
//                        flower++;
//                        visited[i][j] = true;
//                    }
//                    else if (ck[i][j][0]) { // 초록색만 전파된 곳임
//                        que.offer(new Ground(i,j,'G'));
//                        visited[i][j] = true;
//                    }
//                    else if (ck[i][j][1]) { // 빨간색만 전파된 곳임
//                        que.offer(new Ground(i,j,'R'));
//                        visited[i][j] = true;
//                    }
//                }
//            }
//        }
//        maxFlower = Math.max(maxFlower, flower);
//    }
}
