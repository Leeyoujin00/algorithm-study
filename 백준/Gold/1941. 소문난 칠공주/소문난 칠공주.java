import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static Character[][] seat;
    static int result = 0;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        seat = new Character[5][5];

        for (int i = 0; i < 5; i++) {
            String s = br.readLine();
            for (int j = 0; j < 5; j++) {
                seat[i][j] = s.charAt(j);
            }
        }

        int[] selected = new int[7];
        backtracking(0,0, selected, 0);
        System.out.print(result);

    }

    // 0-24 중 7명 선택
    public static void backtracking(int start, int r, int[] selected, int yNum) {

        if (r == 7) {
            check(selected);
            return;
        }

        for (int i = start; i < 25; i++) {
            selected[r] = i;
            // 선택한 학생까지 포함해, Y가 3이상이면 안됨
            if (seat[i/5][i%5] == 'Y') {
                if (yNum < 3) {
                    backtracking(i+1, r+1, selected, yNum+1);
                }
            }
            else {
                backtracking(i+1, r+1, selected, yNum);
            }
        }

    }

    // 선택된 7명의 학생이 연결되어 있는지 확인
    public static void check(int[] selected) {

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {selected[0]/5, selected[0]%5});
        boolean[] visited = new boolean[7];
        visited[0] = true;
        int s = 1;

        while (!queue.isEmpty()) {

            int r = queue.peek()[0];
            int c = queue.poll()[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + dx[i];
                int nc = c + dy[i];

                if (nr < 0 || 5 <= nr || nc < 0 || 5 <= nc) {
                    continue;
                }

                // 해당 위치가 선택된 학생 위치 중 하나일 때만 큐에 삽입
                for (int j = 0; j < 7; j++) {
                    if (selected[j]/5 == nr && selected[j]%5 == nc && !visited[j]) {
                        visited[j] = true;
                        s++;
                        queue.offer(new int[] {nr,nc});
                    }
                }
            }
        }

        if (s == 7) {
            result++;
        }
    }
}
