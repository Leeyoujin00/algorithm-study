import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static char[][] map;
    static int n = 5;
    // 상하좌우
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int res = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 좌석 배치
        map = new char[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        int[] selected = new int[7];
        backtracking(0,0,0, selected);

        System.out.print(res);

    }

    // 25개의 자리 중, 7자리 선택 (조합)
    // 선택한 자리에서 S가 4명이상이어야 함, 즉 Y는 4명 미만이어야 함
    private static void backtracking(int start, int r, int yCnt, int[] selected) {

        if (r == 7) {
            bfs(selected);
            return;
        }

        for (int i = start; i < 25; i++) {
            char student = map[i/5][i%5];
            if (student == 'Y') {
                if (yCnt >= 3) continue;
                selected[r] = i;
                backtracking(i+1, r+1, yCnt+1, selected);
            }
            else {
                selected[r] = i;
                backtracking(i+1, r+1, yCnt, selected);
            }
        }
    }

    // 선택한 일곱 자리가 인접해있는지 확인
    private static void bfs(int[] selected) {

        boolean[] ck = new boolean[7];

        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] {selected[0]/5, selected[0]%5});
        ck[0] = true;

        int validCnt = 0;
        while (!que.isEmpty()) {

            int[] cur = que.poll();
            validCnt++;

            // 상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                // 선택한 조합에 속하는지 확인
                for (int j = 0; j < 7; j++) {
                    if (selected[j] == (nx*5 + ny) && !ck[j]) {
                        que.offer(new int[] {nx,ny});
                        ck[j] = true;
                    }
                }
            }
        }

        if (validCnt == 7) res++;
    }
}
