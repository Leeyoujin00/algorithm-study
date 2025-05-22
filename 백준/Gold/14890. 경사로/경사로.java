import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,l;
    static int[][] map;
    static boolean[][] ck; // 각 칸에 경사로 깔렸는지 여부

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        ck = new boolean[n][n];
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 길을 지나갈 수 있으려면, 길의 모든 칸의 높이가 같아야 함.
        int ableCnt = 0;
        // 가로 길 확인
        for (int i = 0; i < n; i++) {
            ck = new boolean[n][n];
            if (simulationRow1(i) && simulationRow2(i)) {
                ableCnt++;
            }
        }

        // 세로 길 확인
        for (int i = 0; i < n; i++) {
            ck = new boolean[n][n];
            if (simulationCol1(i) && simulationCol2(i)) {
                ableCnt++;
            }
        }

        System.out.print(ableCnt);
    }

    // 길에 속한 모든 칸의 높이가 같거나, 경사로를 놓아서 건널 수 있으면 됨
    // 가로길 확인 (왼 -> 오)
    private static boolean simulationRow1(int r) {

        // 현재 위치보다 낮은 위치일 경우, 경사로를 놓을 수 있는지 확인한다.
        int cur = 0;
        int next = 1;

        while (cur < n-1) {
            // 현재 위치와 다음 위치의 높이가 같음
            if (map[r][cur] == map[r][next]) {
                // 다음으로 넘어간다.
                cur++;
                next++;
                continue;
            }
            else if (map[r][cur] > map[r][next]) { // 현재 위치가 더 높음
                // 높이 차이가 1보다 크다면, 지나갈 수 없음
                if (map[r][cur] - map[r][next] > 1) return false;

                int nh = map[r][next];
                // 경사로를 만들 수 있는지 확인한다.
                for (int i = 0; i < l; i++) {
                    // l개의 칸이 next 칸의 높이와 같음 && 해당 칸에 경사로 없음
                    if (next+i >= n) return false;
                    if (nh != map[r][next+i]) return false;
                    if (ck[r][next+i]) return false;
                }

                // 경사로를 만든다.
                for (int i = 0; i < l; i++) {
                    ck[r][next+i] = true;
                }

                // 만든 경사로 이후의 위치로 탐색 위치 변경
                cur += l;
                next += l;
            }
            else { // 다음 위치가 더 높음
                cur++;
                next++;
            }
        }

        return true;
    }

    // 가로길 확인 (왼 <- 오)
    private static boolean simulationRow2(int r) {
        // 현재 위치보다 낮은 위치일 경우, 경사로를 놓을 수 있는지 확인한다.
        int cur = n-1;
        int next = n-2;

        while (cur >= 1) {
            // 현재 위치와 다음 위치의 높이가 같음
            if (map[r][cur] == map[r][next]) {
                // 다음으로 넘어간다.
                cur--;
                next--;
                continue;
            }
            else if (map[r][cur] > map[r][next]) { // 현재 위치가 더 높음
                // 높이 차이가 1보다 크다면, 지나갈 수 없음
                if (map[r][cur] - map[r][next] > 1) return false;

                int nh = map[r][next];
                // 경사로를 만들 수 있는지 확인한다.
                for (int i = 0; i < l; i++) {
                    // l개의 칸이 next 칸의 높이와 같음 && 해당 칸에 경사로 없음
                    if (next-i < 0) return false;
                    if (nh != map[r][next-i]) return false;
                    if (ck[r][next-i]) return false;
                }

                // 경사로를 만든다.
                for (int i = 0; i < l; i++) {
                    ck[r][next-i] = true;
                }

                // 만든 경사로 이후의 위치로 탐색 위치 변경
                cur -= l;
                next -= l;
            }
            else { // 다음 위치가 더 높음
                cur--;
                next--;
            }
        }

        return true;
    }


    //private static

    // 세로길  확인
    private static boolean simulationCol1(int c) {

        // 현재 위치보다 낮은 위치일 경우, 경사로를 놓을 수 있는지 확인한다.
        int cur = 0;
        int next = 1;

        while (cur < n-1) {
            // 현재 위치와 다음 위치의 높이가 같음
            if (map[cur][c] == map[next][c]) {
                // 다음으로 넘어간다.
                cur++;
                next++;
                continue;
            }
            else if (map[cur][c] > map[next][c]) { // 현재 위치가 더 높음
                // 높이 차이가 1보다 크다면, 지나갈 수 없음
                if (map[cur][c] - map[next][c] > 1) return false;

                int nh = map[next][c];
                // 경사로를 만들 수 있는지 확인한다.
                for (int i = 0; i < l; i++) {
                    // l개의 칸이 next 칸의 높이와 같음 && 해당 칸에 경사로 없음
                    if (next+i >= n) return false;
                    if (nh != map[next+i][c]) return false;
                    if (ck[next+i][c]) return false;
                }

                // 경사로를 만든다.
                for (int i = 0; i < l; i++) {
                    ck[next+i][c] = true;
                }

                // 만든 경사로 이후의 위치로 탐색 위치 변경
                cur += l;
                next += l;
            }
            else { // 다음 위치가 더 높음
                cur++;
                next++;
            }
        }

        return true;
    }

    // 세로길 확인 (위 <- 아래)
    private static boolean simulationCol2(int c) {
        // 현재 위치보다 낮은 위치일 경우, 경사로를 놓을 수 있는지 확인한다.
        int cur = n-1;
        int next = n-2;

        while (cur > 0) {
            // 현재 위치와 다음 위치의 높이가 같음
            if (map[cur][c] == map[next][c]) {
                // 다음으로 넘어간다.
                cur--;
                next--;
                continue;
            }
            else if (map[cur][c] > map[next][c]) { // 현재 위치가 더 높음
                // 높이 차이가 1보다 크다면, 지나갈 수 없음
                if (map[cur][c] - map[next][c] > 1) return false;

                int nh = map[next][c];
                // 경사로를 만들 수 있는지 확인한다.
                for (int i = 0; i < l; i++) {
                    // l개의 칸이 next 칸의 높이와 같음 && 해당 칸에 경사로 없음
                    if (next-i < 0) return false;
                    if (nh != map[next-i][c]) return false;
                    if (ck[next-i][c]) return false;
                }

                // 경사로를 만든다.
                for (int i = 0; i < l; i++) {
                    ck[next-i][c] = true;
                }

                // 만든 경사로 이후의 위치로 탐색 위치 변경
                cur -= l;
                next -= l;
            }
            else { // 다음 위치가 더 높음
                cur--;
                next--;
            }
        }

        return true;
    }
}
