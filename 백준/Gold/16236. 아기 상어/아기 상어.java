import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int[][] arr;
    static int[] dy = {-1, 0, 0, 1};
    static int[] dx = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        arr = new int[N][N];

        //PriorityQueue<int[]> queue = new PriorityQueue<>();
        int[] cur = null;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 9) {
                    cur = new int[] {i, j, 0};
                    arr[i][j] = 0;
                }
            }
        }

        int eat = 0; // 먹은 물고기 수
        int size = 2; // 아기상어 크기
        int move = 0; // 이동한 총 거리

        while (true) {
//            PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) ->
//                    o1[2] != o1[2] ? Integer.compare(o1[2], o2[2]) : (o1[0] != o2[0] ? Integer.compare(o1[0], o2[0]) : Integer.compare(o1[1], o2[1]))
//                    );

            PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) ->
                    o1[2] != o2[2] ? Integer.compare(o1[2], o2[2]) : (o1[0] != o2[0] ? Integer.compare(o1[0], o2[0]) : Integer.compare(o1[1], o2[1]))
            );
            queue.offer(new int[]{cur[0], cur[1], 0});
            boolean[][] visited = new boolean[N][N];
            visited[cur[0]][cur[1]] = true;
            boolean ck = false; // 물고기를 먹었는지 확인하는 변수

            while (!queue.isEmpty()) {
                cur = queue.poll();
                int y = cur[0];
                int x = cur[1];
                int d = cur[2];
                //visited[y][x] = true;

                if (0 != arr[y][x] && arr[y][x] < size) { // 물고기가 있고, 먹을 수 있는 크기일 경우
                    // 물고기를 먹는다.
                    eat++;
                    move += d;
                    arr[y][x] = 0;
                    //cur = new int[] {y, x, 0};
                    ck = true;

                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];
                    if (ny >= 0 && ny < N && nx >= 0 && nx < N) { // 탐색 범위 내이고
                        if (!visited[ny][nx] && arr[ny][nx] <= size) { // 물고기 크기가 상어 이하이고, 방문한 적이 없을 경우
                            visited[ny][nx] = true;
                            queue.offer(new int[] {ny, nx, d+1});
                        }
                    }
                }
            }

            if (!ck) { // 탐색을 다 했는데, 물고기를 잡아먹지 않았을 경우 종료
                break;
            }
            if (eat == size) {
                size++;
                eat = 0;
            }
        }

        System.out.print(move);
    }
}
