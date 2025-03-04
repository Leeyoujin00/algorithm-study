import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int n = 5;
    static char[][] arr;
    static int cnt = 0;
    static int[] dr = {0,0,1,-1};
    static int[] dc = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = new char[n][n];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = s.charAt(j);
            }
        }

        int[] selected = new int[7];
        // 칠공주를 결성할 수 있는 모든 경우의 수를 구한다.
        backtracking(0,0,0, selected);
        System.out.println(cnt);
    }

    // 25개의 문자 중, 7개를 선택한다. (조합)
    public static void backtracking(int start, int c, int yNum, int[] selected) {
        if (c == 7) {
            //System.out.println("확인");
            // 칠공주 결성 여부 확인
            if (isSeven(selected)) cnt++;
            return;
        }

        // S가 적어도 4 이상, Y는 최대 3
        for (int i = start; i < n*n; i++) {
            char ch = arr[i/n][i%n];
            if (ch == 'Y') {
                if (yNum < 3) {
                    selected[c] = i;
                    backtracking(i+1, c+1, yNum+1, selected);
                }
            }
            else if (ch == 'S') {
                selected[c] = i;
                backtracking(i+1, c+1, yNum, selected);
            }
        }
    }

    // 해당 조합이 인접해있는지 확인한다.
    private static boolean isSeven(int[] selected) {

        // BFS
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(selected[0]/n, selected[0]%n));
        //boolean[][] visited = new boolean[n][n];
        boolean[] v = new boolean[7];
        v[0] = true;

        //visited[queue.peek().r][queue.peek().c] = true;
        int s = 1;
        while (!queue.isEmpty()) {

            Node cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;

                // 해당 위치가 selected 에 속할 때만 탐색 진행(큐에 삽입)
                for (int j = 0; j < 7; j++) {
                    if (!v[j] && selected[j] / n == nr && selected[j] % n == nc) {
                        v[j] = true;
                        queue.offer(new Node(nr,nc));
                        s++;
                    }
                }
            }
        }
        if (s == 7) {
            //System.out.println("성공");
            return true;
        }
        return false;
    }

    static class Node {
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
