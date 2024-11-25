import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,r,l;
    static int[][] map;
    static int[] dy = {0,0,1,-1};
    static int[] dx = {1,-1,0,0};
    static Queue<int[]> queue;
    static boolean[][] visited;
    static List<int[]> population;
    static boolean ck;
    static int result = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        ck = true;

        // 더 이상 인구이동 없을 때까지 진행
        while (ck) {
            ck = false;
            visited = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        queue = new LinkedList<>();
                        queue.add(new int[] {i,j});
                        population = new ArrayList<>();
                        population.add(new int[] {i,j});
                        setUnite();
                    }
                }
            }

            if (ck) result++;
            //System.out.println("이동: " + result);
            //x = false;
            //ckN++;
        }

        System.out.print(result);

    }

    // bfs 진행
    static void setUnite() {

        visited[queue.peek()[0]][queue.peek()[1]] = true;

        while (!queue.isEmpty()) {
            //System.out.println("큐 진행");

            int y = queue.peek()[0];
            int x = queue.poll()[1];
            //visited[y][x] = true;

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                // 탐색범위 벗어나는지 확인
                if (0 <= ny && ny < n && 0 <= nx && nx < n && !visited[ny][nx]) {

                    // 방문하지 않았고, 인구차 조건 만족
                    if (l <= Math.abs(map[y][x]-map[ny][nx]) && Math.abs(map[y][x]-map[ny][nx]) <= r) {

                        visited[ny][nx] = true;
                        queue.offer(new int[] {ny, nx});
                        population.add(new int[] {ny, nx});
                        //sum += map[ny][nx];
                        //System.out.println(sum);
                    }
                }
            }
        }

        // 인구 이동 진행
        move();

    }

    static void move() {

        int size = population.size();
        int sum = 0;

        if (size > 1) {
            ck = true;
        }
        else return;

        for (int i = 0; i < size; i++) {
            sum += map[population.get(i)[0]][population.get(i)[1]];
        }
        //System.out.println("값: " + sum/size);
        for (int i = 0; i < size; i++) {
            int y = population.get(i)[0];
            int x = population.get(i)[1];

            map[y][x] = sum/size;
        }
    }
}
