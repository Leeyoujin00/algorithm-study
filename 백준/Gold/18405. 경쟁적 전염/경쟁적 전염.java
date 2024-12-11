import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n,k;
    static int[][] arr;
    static int s,x,y;
    static PriorityQueue<int[]> pq;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n][n];
        pq = new PriorityQueue<>((o1,o2) -> Integer.compare(o1[0], o2[0]));

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] != 0) {
                    pq.offer(new int[] {arr[i][j], i, j});
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        bfs();
        System.out.print(arr[x-1][y-1]);

    }

    public static void bfs() {

        List<int[]> nextList = new ArrayList<>();
        int cnt = 0;
        while (cnt < s) {

            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                int num = cur[0];
                int x = cur[1];
                int y = cur[2];

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= n || arr[nx][ny] != 0) {
                        continue;
                    }
                    //pq.offer(new int[] {num, nx, ny});
                    nextList.add(new int[] {num, nx, ny});
                    arr[nx][ny] = num;
                }
            }

            for (int i = 0; i < nextList.size(); i++) {
                pq.offer(nextList.get(i));
            }
            nextList.clear();
            cnt++;
        }

    }

}
