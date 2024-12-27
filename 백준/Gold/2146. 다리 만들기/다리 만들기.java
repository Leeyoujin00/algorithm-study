import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[][] map;
    static List<List<Pos>> islands;
    static List<List<Pos>> sidePos;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int count = 0;
    static int min = 10000;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        islands = new ArrayList<>();
        sidePos = new ArrayList<>();
        count();

//        for (int i = 0; i < count; i++) {
//            System.out.println();
//            for (Pos p : sidePos.get(i)) {
//                System.out.print(p.x + " " + p.y + " " + p.id +"   ");
//            }
//        }
        //System.out.println("개수: " + count);
        // 서로 다른 섬들끼리의 최소길이 다리 구하기
        for (int i = 0; i < count-1; i++) {
            for (int j = i+1; j < count; j++) {
                int tmp = calMinBridge(i,j);
                min = Math.min(min, tmp);
                //System.out.println(i + " " + j + " " + tmp);
            }
        }

        System.out.print(min);
    }

    static class Node {
        int x;
        int y;
        int move;

        public Node(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }

    // 각 섬의 가장자리 좌표들만 비교하면 됨
    static int calMinBridge(int id1, int id2) {
        int min = 10000;
        for (int i = 0; i < sidePos.get(id1).size(); i++) {
            for (int j = 0; j < sidePos.get(id2).size(); j++) {
                int distance = Math.abs(sidePos.get(id1).get(i).x - sidePos.get(id2).get(j).x) + Math.abs(sidePos.get(id1).get(i).y - sidePos.get(id2).get(j).y);
                min = Math.min(min, distance-1);
            }
        }
        return min;
    }

    // 섬 정보들 저장
    static void count() {

        boolean[][] visited = new boolean[n][n];

        int id = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    id++;
                    islands.add(new ArrayList<>());
                    sidePos.add(new ArrayList<>());
                    islands.get(id-1).add(new Pos(i,j,id));
                    bfs(i,j,id, visited);
                    //id++;
                }
            }
        }
        count = id;
    }

    static void bfs(int x, int y, int id, boolean[][] visited) {

        Queue<Pos> queue = new LinkedList<>();
        queue.offer(new Pos(x,y,id));
        visited[x][y] = true;
        map[x][y] = id;

        while (!queue.isEmpty()) {
            Pos cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x+dx[i];
                int ny = cur.y+dy[i];
                if (nx < 0 || nx >= n || ny < 0 || n <= ny || visited[nx][ny]) {
                    continue;
                }
                Pos next = new Pos(nx, ny, id);
                if (map[nx][ny] == 0) {
                    sidePos.get(id-1).add(cur);
                    continue;
                }
                visited[nx][ny] = true;
                map[nx][ny] = id;
                queue.offer(next);
                islands.get(id-1).add(next);
            }
        }
    }

    static class Pos {
        int x;
        int y;
        int id; // 섬 번호

        public Pos(int x, int y, int id) {
            this.x = x;
            this.y = y;
            this.id = id;
        }
    }
}
