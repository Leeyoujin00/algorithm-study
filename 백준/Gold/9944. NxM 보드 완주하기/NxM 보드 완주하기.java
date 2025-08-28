import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static char[][] board;
    static int blank;       
    static int ans;
    static final int INF = (int)1e9;
    static final int[] dx = {0, 0, 1, -1};
    static final int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringBuilder out = new StringBuilder();
        int tc = 1;

        while (true) {
            // 빈 줄 스킵, EOF 종료
            line = nextNonEmptyLine(br);
            if (line == null) break;
            StringTokenizer st = new StringTokenizer(line);
            if (st.countTokens() < 2) break;

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            board = new char[n][m];
            blank = 0;

            for (int i = 0; i < n; i++) {
                String row = br.readLine();
                for (int j = 0; j < m; j++) {
                    board[i][j] = row.charAt(j);
                    if (board[i][j] == '.') blank++;
                }
            }

            ans = INF;

            // 모든 시작 위치에서 시도 (빈 칸만)
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == '.') {
                        board[i][j] = '*';          // 시작 칸 방문 처리
                        backtracking(i, j, 1, 0);   // cnt=1, move=0
                        board[i][j] = '.';          // 복원
                    }
                }
            }

            out.append("Case ").append(tc++).append(": ").append(ans == INF ? -1 : ans).append('\n');
        }

        System.out.print(out.toString());
    }
    
    static void backtracking(int x, int y, int cnt, int move) {
        if (move >= ans) return;          // 가지치기
        if (cnt == blank) {               // 모든 빈칸 방문 완료
            ans = Math.min(ans, move);
            return;
        }

        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // 이 방향으로 '막힐 때까지' 굴리며 경로를 저장
            List<int[]> path = new ArrayList<>();

            while (0 <= nx && nx < n && 0 <= ny && ny < m && board[nx][ny] == '.') {
                board[nx][ny] = '*';        // 방문 처리
                path.add(new int[]{nx, ny});
                nx += dx[dir];
                ny += dy[dir];
            }

            if (!path.isEmpty()) {
                // 마지막으로 실제로 도달한 칸(막힌 곳의 직전)
                int[] last = path.get(path.size() - 1);
                backtracking(last[0], last[1], cnt + path.size(), move + 1);

                // 복원
                for (int i = path.size() - 1; i >= 0; i--) {
                    int[] p = path.get(i);
                    board[p[0]][p[1]] = '.';
                }
            }
            // path가 비어 있으면 이 방향으로는 한 칸도 못 감 -> 시도하지 않음
        }
    }

    // 공백 라인 스킵하며 다음 유효 라인 반환 (EOF면 null)
    static String nextNonEmptyLine(BufferedReader br) throws IOException {
        String s;
        while ((s = br.readLine()) != null) {
            if (!s.trim().isEmpty()) return s;
        }
        return null;
        }
}
