import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m;
    static int[][] map;
    static int emptyPos = 0;
    // 바이러스의 위치 저장
    static List<Pos> posList = new ArrayList<>();
    // 상하좌우
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int minTime = Integer.MAX_VALUE;

    static class Pos {
        int r;
        int c;
        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    posList.add(new Pos(i,j));
                }
                else if (map[i][j] == 0) {
                    emptyPos++;
                }
            }
        }

        if (emptyPos == 0) {
            System.out.print(0);
            System.exit(0);
        }

        Pos[] selected = new Pos[m];
        select(0,0,selected);

        if (minTime == Integer.MAX_VALUE) System.out.print(-1);
        else System.out.print(minTime);

    }

    // 활성 바이러스를 놓는 위치를 선택한다.
    private static void select(int start, int r, Pos[] selected) {
        if (r == m) {
            int time = bfs(selected);
            if (time != -1) {
                minTime = Math.min(minTime, time);
            }
            return;
        }

        for (int i = start; i < posList.size(); i++) {
            selected[r] = posList.get(i);
            select(i+1, r+1, selected);
        }
    }

    static class Node {
        int r;
        int c;
        int time;
        public Node(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }

    // 활성 바이러스의 전파를 진행 -> BFS
    private static int bfs(Pos[] selected) {

        Queue<Node> que = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        for (Pos pos : selected) {
          que.offer(new Node(pos.r, pos.c, 0));
          visited[pos.r][pos.c] = true;
        }

        int cnt = 0, result = 0;
        while (true) {
            result++;
            List<Node> nodeList = new ArrayList<>();
            while (!que.isEmpty()) {
                Node node = que.poll();
                int r = node.r, c = node.c, time = node.time;

                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if (nr < 0 || nr >= n || nc < 0 || nc >= n || visited[nr][nc] || map[nr][nc] == 1) {
                        continue;
                    }
                    visited[nr][nc] = true;
                    nodeList.add(new Node(nr,nc,time+1));
                    if (map[nr][nc] == 0) cnt++;
                }
            }

            if (cnt == emptyPos) return result;

            if (nodeList.isEmpty()) return -1;
            for (Node node: nodeList) {
                que.offer(node);
            }
        }

        //return result;
    }
}
