import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,k;
    static int[][] map;
    // 각 좌표에 말의 정보를 저장한다.
    static Deque<Integer>[][] mapDq;
    // 이동방향 - 우좌상하
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    // 반대 이동방향
    static int[] rmv = {1,0,3,2};
    // 각 말의 이동방향(0-3), x좌표, y좌표 저장
    static int[][] piecesInfo;
    static Deque<Integer> nodes = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        mapDq = new Deque[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mapDq[i][j] = new LinkedList<>();
            }
        }

        piecesInfo = new int[k][3];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int mv = Integer.parseInt(st.nextToken())-1;
            nodes.addLast(i);
            piecesInfo[i][0] = mv;
            piecesInfo[i][1] = x;
            piecesInfo[i][2] = y;
            mapDq[x][y].addLast(i);
        }

        System.out.print(solve());
    }

    private static int solve() {
        int turn = 0;

        /**
         * 턴 한번 = 1~k 번 말 이동시킴
         * 한 말이 이동할 때 위에 올려져 있는 말도 함께 이동함
         */
        while (true) {
            turn++;
            if (turn > 1000) return -1;
            for (int i = 0; i < k; i++) {
                int cur = nodes.removeFirst();

                int x = piecesInfo[cur][1], y = piecesInfo[cur][2], mv = piecesInfo[cur][0];
                // 현재 말 위에 있는 모든 말은 함께 이동한다.
                List<Integer> list = new ArrayList<>();
                boolean flag = false;
                int size = mapDq[x][y].size();
                for (int j = 0; j < size; j++) {
                    int pNum = mapDq[x][y].removeFirst();
                    if (pNum == cur) {
                        flag = true;
                    }
                    if (flag) { // 현재 말 위의 말
                        list.add(pNum);
                    }
                    else {
                        mapDq[x][y].addLast(pNum);
                    }
                }

                int nx = x + dx[mv];
                int ny = y + dy[mv];
                // 체스판 벗어나는 경우 == 파란색인 경우
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    // 말의 이동방향을 반대로 하고 한칸 이동한다. 이동하려는 칸도 파란색인 경우는 가만히 있는다.
                    mv = rmv[mv];
                    nx = x + dx[mv];
                    ny = y + dy[mv];
                    // 이동하려는 칸이 파란색이면 이동x
                    if (map[nx][ny] == 2) {
                        nx = x;
                        ny = y;
                        for (int p: list) {
                            piecesInfo[p][1] = nx;
                            piecesInfo[p][2] = ny;
                            mapDq[nx][ny].addLast(p);
                        }
                    }
                    else if (map[nx][ny] == 1) { // 이동하려는 칸이 빨간색인 경우
                        for (int j = list.size()-1; j >= 0; j--) {
                            piecesInfo[list.get(j)][1] = nx;
                            piecesInfo[list.get(j)][2] = ny;
                            mapDq[nx][ny].addLast(list.get(j));
                        }
                    }
                    else { // 흰색인 경우 한 칸이동
                        for (int p: list) {
                            piecesInfo[p][1] = nx;
                            piecesInfo[p][2] = ny;
                            mapDq[nx][ny].addLast(p);
                        }
                    }
                    piecesInfo[cur][0] = mv;
                }
                else if (map[nx][ny] == 2) {
                    // 말의 이동방향을 반대로 하고 한칸 이동한다. 이동하려는 칸도 파란색인 경우는 가만히 있는다.
                    mv = rmv[mv];
                    nx = x + dx[mv];
                    ny = y + dy[mv];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny] == 2) { // 이동하려는 칸이 파란색이면 이동x
                        nx = x;
                        ny = y;
                        for (int p : list) {
                            piecesInfo[p][1] = nx;
                            piecesInfo[p][2] = ny;
                            mapDq[nx][ny].addLast(p);
                        }
                    }
                    else if (map[nx][ny] == 1) { // 이동하려는 칸이 빨간색인 경우
                        for (int j = list.size()-1; j >= 0; j--) {
                            piecesInfo[list.get(j)][1] = nx;
                            piecesInfo[list.get(j)][2] = ny;
                            mapDq[nx][ny].addLast(list.get(j));
                        }
                    }
                    else { // 흰색 -> 한 칸이동
                        for (int p : list) {
                            //piecesInfo[p][0] = mv;
                            piecesInfo[p][1] = nx;
                            piecesInfo[p][2] = ny;
                            mapDq[nx][ny].addLast(p);
                        }
                    }
                    piecesInfo[cur][0] = mv;
                }
                else if (map[nx][ny] == 0) { // 흰색인 경우 그 칸으로 이동
                    for (int p: list) {
                        piecesInfo[p][1] = nx;
                        piecesInfo[p][2] = ny;
                        mapDq[nx][ny].addLast(p);
                    }
                }
                else if (map[nx][ny] == 1) { // 빨간색인 경우, 이동한 후 자신과 위에 있는 모든 말의 순서 반대로함
                    for (int j = list.size()-1; j >= 0; j--) {
                        piecesInfo[list.get(j)][1] = nx;
                        piecesInfo[list.get(j)][2] = ny;
                        mapDq[nx][ny].addLast(list.get(j));
                    }
                }


                // 이동 후, 해당 칸의 말 4개 이상이면 중단
                if (mapDq[nx][ny].size() >= 4) {
//                    while(!mapDq[nx][ny].isEmpty()) {
//                        System.out.println(mapDq[nx][ny].removeFirst());
//                    }
//                    System.out.println();
                    return turn;
                }

                nodes.addLast(cur);
            }
        }
    }
}
