import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n,m,p;
    static int[] s;
    static char[][] map;
    static Queue<Node>[] qList;
    static int[] answer;
    static boolean[][] visited;
    // 상하좌우
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    static class Node {
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        s = new int[p+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= p; i++) {
            s[i] = Integer.parseInt(st.nextToken());
        }

        answer = new int[p+1];
        qList = new Queue[p+1];
        visited = new boolean[n][m];

        for (int i = 0; i <= p; i++) {
            qList[i] = new LinkedList<>();
        }
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
                if ('1' <= map[i][j] && map[i][j] <= '9') {
                    int player = Character.getNumericValue(map[i][j]);
                    answer[player]++;
                    qList[player].offer(new Node(i,j));
                    visited[i][j] = true;
                }
            }
        }

        boolean flag = true;
        while (flag) {
            flag = false;
            // 성을 확장한 플레이어가 하나라도 없으면 게임을 종료한다.
            for (int i = 1; i <= p; i++) {
                if (bfs(i)) flag = true;
            }
        }

        for (int i = 1; i <= p; i++) {
            System.out.print(answer[i] + " ");
        }
    }

    private static boolean bfs(int player) {

        // 현재 턴에서 해당 플레이어의 추가된 성 개수
        int cnt = 0;

        // 해당 플레이어의 확장범위만큼 진행한다.
        for (int sNum = 0; sNum < s[player]; sNum++) {
            // 큐의 크기만큼 진행한다.
            int qSize = qList[player].size();
            for (int size = 0; size < qSize; size++) {
                Node cur = qList[player].poll();
                int r = cur.r;
                int c = cur.c;

                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    // 탐색 범위 벗어났거나, 이미 방문한 위치(성 있는 위치)라면 진행 x
                    if (nr < 0 || nr >= n || nc < 0 || nc >= m || visited[nr][nc]) continue;
                    // 벽 마주치면 진행 x
                    if (map[nr][nc] == '#') continue;

                    visited[nr][nc] = true;
                    qList[player].offer(new Node(nr,nc));
                    cnt++;
                    map[nr][nc] = Character.forDigit(player, 10);
                }
            }
            if (qList[player].isEmpty()) break;
        }
        if (cnt == 0) return false;
        answer[player] += cnt;
        return true;
    }
}
