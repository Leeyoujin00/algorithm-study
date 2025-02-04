import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int r,c;
    static char[][] map;
    static int[] dr = {0,0,1,-1};
    static int[] dc = {1,-1,0,0};
    static Queue<int[]> fireQue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];

        int sr = 0, sc = 0, fr = 0, fc = 0;
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'J') {
                    sr = i;
                    sc = j;
                    map[i][j] = '.';
                }
                else if (map[i][j] == 'F') {
                    fireQue.offer(new int[] {i,j});
                }
            }
        }

        int ans = bfs(sr,sc);

        if (ans == -1) {
            System.out.print("IMPOSSIBLE");
        }
        else System.out.print(ans);
    }

    // 매초마다 지훈이 이동 -> 불 이동 진행
    static int bfs(int sr, int sc) {
        // 지훈 이동 큐
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(sr,sc,0));
        boolean[][] visited = new boolean[r][c];
        visited[sr][sc] = true;

        //boolean flag = true;
        // 매 초마다 진행
        while (true) {
            List<Node> list = new ArrayList<>(); // 지훈이 이동할 칸 저장
            List<int[]> fireList = new ArrayList<>(); // 불이 이동할 칸 저장

            // 불이동
            while (!fireQue.isEmpty()) {
                int[] fire = fireQue.poll();

                for (int i = 0; i < 4; i++) {
                    int nr = fire[0] + dr[i];
                    int nc = fire[1] + dc[i];

                    if (nr < 0 || nr >= r || nc < 0 || nc >= c || map[nr][nc] != '.' || visited[nr][nc])  {
                        continue;
                    }

                    map[nr][nc] = 'F';
                    fireList.add(new int[] {nr,nc});
                }
            }
            for (int[] next : fireList) {
                fireQue.offer(next);
            }

            // 지훈 이동
            while (!queue.isEmpty()) {
                Node cur = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dr[i];
                    int nc = cur.c + dc[i];

                    // 가장자리 위치에 도달한 경우 탈출 성공
                    if (nr < 0 || nr >= r || nc < 0 || nc >= c) {
                        return cur.step+1;
                    }

                    if (map[nr][nc] != '.' || visited[nr][nc])  {
                        continue;
                    }

                    list.add(new Node(nr,nc,cur.step+1));
                    visited[nr][nc] = true;
                }
            }

            // 지훈이가 더이상 이동할 수 없음
            if (list.size() == 0) {
                return -1;
            }
            for (Node node : list) {
                queue.offer(node);
            }
        }

    }

    static class Node {
        int r;
        int c;
        int step;

        public Node(int r, int c, int step) {
            this.r = r;
            this.c = c;
            this.step = step;
        }
    }
}
