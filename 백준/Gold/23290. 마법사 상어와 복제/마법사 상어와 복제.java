import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int m,s;
    static int[][] fNum = new int[4][4]; // 물고기 개수 표시
    static int[][] smellT = new int[4][4]; // 물고기 냄새 표시
    static int sharkX, sharkY;
    // 8가지 물고기 이동 방향
    static int[][] df = {{0,-1}, {-1,-1}, {-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}};
    // 4가지 상어 이동 방향
    static int[][] ds = {{-1,0}, {0,-1}, {1,0}, {0,1}};
    static List<int[]> sharkMvList = new ArrayList<>();

    static Queue<Fish> que = new LinkedList<>();
    static class Fish {
        int row;
        int col;
        int dir;
        public Fish(int row, int col, int dir) {
            this.row = row;
            this.col = col;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int fx = Integer.parseInt(st.nextToken()) - 1;
            int fy = Integer.parseInt(st.nextToken()) - 1;
            int fd = Integer.parseInt(st.nextToken()) - 1;
            que.offer(new Fish(fx,fy,fd));
            fNum[fx][fy]++;
        }

        st = new StringTokenizer(br.readLine());
        sharkX = Integer.parseInt(st.nextToken())-1;
        sharkY = Integer.parseInt(st.nextToken())-1;

        int[] selected = new int[3];
        setSharkMv(0, selected);

        for (int i = 0; i < s; i++) {
            simulation();
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (smellT[j][k] > 0) {
                        smellT[j][k]++;
                    }
                }
            }
        }

        // 격자에 있는 물고기 수를 센다.
        int result = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result += fNum[i][j];
            }
        }
        System.out.print(result);

    }

    private static void setSharkMv(int r, int[] selected) {
        if (r == 3) {
            int[] arr = new int[3];
            for (int i = 0; i < 3; i++) {
                arr[i] = selected[i];
            }
            sharkMvList.add(arr);
            return;
        }

        for (int i = 0; i < 4; i++) {
            selected[r] = i;
            setSharkMv(r+1, selected);
        }
    }

    private static int checkMv(int[] arr) {
        int cnt = 0;
        boolean[][] ck = new boolean[4][4];
        int row = sharkX, col = sharkY;

        for (int i = 0; i < 3; i++) {
            int dir = arr[i];
            // 상어가 이동
            int nr = row + ds[dir][0], nc = col + ds[dir][1];
            // 만약 격자 범위 벗어나게 되면 불가능한 경우이다.
            if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4) {
                return -1;
            }
            // 만약 가다가 물고기가 있는 캍은 칸으로 이동하게 된다면, 그 칸에 있는 모든 물고기는 격자에서 제외,
            if (fNum[nr][nc] > 0 && !ck[nr][nc]) {
                cnt += fNum[nr][nc];
            }
            ck[nr][nc] = true;
            row = nr;
            col = nc;
        }

        return cnt;
    }

    private static void print() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(fNum[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static List<int[]> sharkGo(int[] arr) {

        List<int[]> visitedPos = new ArrayList<>();

        //boolean[][] ck = new boolean[4][4];
        int row = sharkX, col = sharkY;

        for (int i = 0; i < 3; i++) {
            int dir = arr[i];
            // 상어가 이동
            int nr = row + ds[dir][0], nc = col + ds[dir][1];
            // 만약 가다가 물고기가 있는 캍은 칸으로 이동하게 된다면, 그 칸에 있는 모든 물고기는 격자에서 제외,
            if (fNum[nr][nc] > 0) {
                fNum[nr][nc] = 0;
                // 물고기 냄새를 남긴다.
                smellT[nr][nc] = 1;
                visitedPos.add(new int[] {nr, nc});
            }
            //ck[nr][nc] = true;
            row = nr;
            col = nc;
        }
        sharkX = row;
        sharkY = col;
        return visitedPos;
    }

    private static void simulation() {
        /**
         * 1. 물고기 복제
         */
        List<Fish> copyFishes = new ArrayList<>();
        for (Fish fish : que) {
            copyFishes.add(new Fish(fish.row, fish.col, fish.dir));
        }

        /**
         * 2. 모든 물고기가 한 칸 이동
         */
        List<Fish> movedFishes = new ArrayList<>();
        while (!que.isEmpty()) {
            Fish curFish = que.poll();
            int row = curFish.row, col = curFish.col, dir = curFish.dir;
            boolean flag = false; // 이동가능 여부 flag
            int v = 0;

            for (int i = 0; i < 8; i++) {
                int nd = dir - v;
                v++;
                if (nd < 0) {
                    nd += 8;
                }
                int nr = row + df[nd][0], nc = col + df[nd][1];
                // 격자 범위 벗어나거나, 상어가 있거나, 물고기 냄새가 있다면 이동 불가
                if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4 || (sharkX == nr && sharkY == nc) || smellT[nr][nc] > 0) {
                    continue;
                }

                // 이동 가능하므로, 이동
                fNum[row][col]--;
                fNum[nr][nc]++;
                movedFishes.add(new Fish(nr, nc, nd));
                flag = true;
                break;
            }

            // 모든 방향으로 이동 불가인 경우, 이동 X
            if (!flag) {
                movedFishes.add(new Fish(row, col, dir));
            }
        }
//        System.out.println("물고기 이동");
//        print();

        /**
         * 3. 상어가 연속해서 3칸 이동
         */
        /**
         * 3-1) 가능한 이동 방법 중, 제외되는 물고기 수 가장 많은 방법 선택
         */
        int max = -1;
        int selectedIdx = -1;
        for (int i = 0; i < sharkMvList.size(); i++) {
            int cnt = checkMv(sharkMvList.get(i));
            if (cnt != -1 && max < cnt) {
                max = cnt;
                selectedIdx = i;
            }
        }

        /**
         * 3-2) 상어 실제로 이동
         */
        List<int[]> visitedPos = sharkGo(sharkMvList.get(selectedIdx));
        for (int i = 0; i < movedFishes.size(); i++) {
            boolean flag2 = true;
            for (int[] v : visitedPos) {
                if (movedFishes.get(i).row == v[0] && movedFishes.get(i).col == v[1]) {
                    // 격자에서 제외되므로, que에 넣지 않는다.
                    flag2 = false;
                }
            }
            if (flag2) {
                que.offer(movedFishes.get(i));
            }
        }
//        System.out.println("상어 이동");
//        print();

        /**
         * 4. 두 번 전 연습에서 생긴 물고기의 냄새가 격자에서 사라짐
         */
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (smellT[i][j] >= 3) {
                    smellT[i][j] = 0;
                }
            }
        }

        /**
         * 5. 복제 마법 완료 -> 물고기 냄새가 있는 곳에 복제될 경우?
         */
        for (Fish fish : copyFishes) {
            fNum[fish.row][fish.col]++;
            que.offer(fish);
        }
//        System.out.println("복제 마법 완료");
//        print();

    }
}
