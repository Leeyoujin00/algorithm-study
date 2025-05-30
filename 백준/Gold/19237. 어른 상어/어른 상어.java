import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m,k;
    static int[][][] map;
    // 각 상어의 방향
    static int[] directions;
    // 각 상어의 이동방향 우선순위
    static int[][][] mvPriority;
    // 이동방향 - 위,아래,왼쪽,오른쪽
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static PriorityQueue<Shark> sharkQ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][n][2];
        // 번호가 낮은 상어가 이동의 우선순위를 갖는다.
        sharkQ = new PriorityQueue<>((o1,o2) -> Integer.compare(o1.num, o2.num));
        // 격자 상태
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j][0] = Integer.parseInt(st.nextToken());
                if (map[i][j][0] != 0) {
                    map[i][j][1] = k;
                    sharkQ.offer(new Shark(i,j,map[i][j][0]));
                }
            }
        }
        // 각 상어의 방향
        directions = new int[m+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            directions[i] = Integer.parseInt(st.nextToken())-1;
        }

        // 각 상어의 이동우선순위
        mvPriority = new int[m+1][4][4];

        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    mvPriority[i][j][k] = Integer.parseInt(st.nextToken())-1;
                }
            }
        }

        System.out.print(simulation());
    }

    // 상어의 정보를 나타냄
    private static class Shark {
        int row;
        int col;
        int num;
        public Shark(int row, int col, int num) {
            this.row = row;
            this.col = col;
            this.num = num;
        }
    }

    private static int simulation() {

        int time = 0;

        boolean[][] ck = new boolean[n][n];
        // 이동한 상어의 정보를 담은 리스트 선언
        List<Shark> nextShark = new ArrayList<>();

        while (time < 1000) {
            time++;

            // 매 이동마다 각 칸의 방문여부 초기화
            ck = new boolean[n][n];

            // 매 시간 상어의 이동을 진행
            while (!sharkQ.isEmpty()) {
                Shark s = sharkQ.poll();
                int row = s.row, col = s.col, num = s.num;
                boolean flag = false;
                // 1. 인접한 칸 중 비어있는 칸을 탐색
                for (int i = 0; i < 4; i++) {
                    // 이동 우선순위에 따름
                    int dir = mvPriority[num][directions[num]][i];
                    int nr = row + dr[dir];
                    int nc = col + dc[dir];
                    // 탐색범위 벗어나면 진행 x
                    if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
                        continue;
                    }
                    // 해당 위치가 비어있다면
                    if (map[nr][nc][0] == 0) {
                        // 높은 우선순위의 상어가 차지하지 않았다면
                        flag = true;
                        directions[num] = dir;
                        if (!ck[nr][nc]) {
                            ck[nr][nc] = true;
                            nextShark.add(new Shark(nr,nc,num));
                            //flag = true;
                            //continue;
                        }
                        break;
                    }
                }

                if (flag) {
                    continue;
                }

                // 2. 인접한 칸 중 자신의 냄새가 있는 칸을 탐색
                for (int i = 0; i < 4; i++) {
                    // 이동 우선순위에 따름
                    int dir = mvPriority[num][directions[num]][i];
                    int nr = row + dr[dir];
                    int nc = col + dc[dir];
                    // 탐색범위 벗어나면 진행 x
                    if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
                        continue;
                    }
                    // 해당 위치에 자신의 냄새가 있다면
                    if (map[nr][nc][0] == num) {
                        directions[num] = dir;
                        nextShark.add(new Shark(nr,nc,num));
                        break;
                    }
                }
            }

            // 각 냄새의 유효시간이 1 줄어든다.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j][0] != 0) {
                        if (--map[i][j][1] == 0) {
                            map[i][j][0] = 0;
                        }
                    }
                }
            }

            // 이동한 상어의 정보를 격자에 표시
            for (Shark shark : nextShark) {
                map[shark.row][shark.col][0] = shark.num;
                map[shark.row][shark.col][1] = k;
                sharkQ.offer(shark);
            }
            nextShark.clear();

            // 1번 상어 한마리만 남았다면 종료
            if (sharkQ.size() == 1) {
                return time;
            }

            // 출력해봄
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    System.out.print(map[i][j][0] + " ");
//                }
//                System.out.println();
//            }
//
//            System.out.println("============");
        }

        return -1;
    }
}
