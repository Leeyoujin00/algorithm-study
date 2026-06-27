import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    int[] dx = {0,0,-1,1};
    int[] dy = {-1,1,0,0};

    public int solution(int[][] maps) {
        return bfs(maps);
    }

    class Node {
        int x, y, step;

        public Node(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }

    private int bfs(int[][] maps) {

        int n = maps.length;
        int m = maps[0].length;

        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;

        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(0,0, 1));

        while (!que.isEmpty()) {

            Node cur = que.poll();
            // 상대방 진영에 도착하면 종료
            if (cur.x == n-1 && cur.y == m-1) return cur.step;

            // 동서남북 네 방향으로 이동
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                // 범위 안이고, 벽이 아니라면 이동
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || maps[nx][ny] == 0 || visited[nx][ny]) continue;

                // 방문
                visited[nx][ny] = true;
                que.offer(new Node(nx, ny, cur.step+1));
            }
        }

        return -1;
    }
}
