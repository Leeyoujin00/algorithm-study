import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m;
    static int[][] arr;
    static Queue<int[]> que = new LinkedList<>();
    static boolean[][] v;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int rest = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        v = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) {
                    que.offer(new int[] {i,j});
                    v[i][j] = true;
                }
                if (arr[i][j] == 0) rest++;
            }
        }

        int result = bfs();
        // 토마토가 다 익지는 못할 경우
        if (rest > 0) System.out.print(-1);
        else System.out.print(result);

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                System.out.print(arr[i][j] + " ");
//            }
//            System.out.println();
//        }


    }

    static int bfs() {

        int day = 0;
        boolean flag = true;

        List<int[]> newTomato = new ArrayList<>();
        while (true) {
            flag = false;
            // 하루마다 바로 이전에 익은 토마토들만 큐에 있음
            while (!que.isEmpty()) {
                int[] cur = que.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = cur[0] + dx[i];
                    int ny = cur[1] + dy[i];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                        continue;
                    }

                    // 주변에 익지 않은 토마토가 있을 경우, 익은 상태 전파
                    if (arr[nx][ny] == 0 && !v[nx][ny]) {
                        v[nx][ny] = true;
                        newTomato.add(new int[] {nx, ny});
                        rest--;
                        flag = true;
                    }

                }
            }
            if (!flag) break;

            for (int[] t : newTomato) {
                que.offer(t);
                arr[t[0]][t[1]] = 1;
            }
            newTomato.clear();
            day++;
        }

        return day;
    }
}
