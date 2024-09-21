import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        int[][] map = new int[n][m];
        int cheeseNum = 0;

        for (int i = 0; i < n; i++) {
            String[] inputs = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(inputs[j]);
                if (map[i][j] == 1) cheeseNum += 1;
            }
        }

        int[] nx = {0, 0, 1, -1};
        int[] ny = {1, -1, 0, 0};

        int time = 0;
        int result = 0;

        while (cheeseNum > 0) {
            result = cheeseNum; // while 루프 내에서 cheeseNum이 감소하기 직전 값을 저장
            Queue<int[]> pos = new LinkedList<>();
            boolean[][] visited = new boolean[n][m];
            pos.offer(new int[] {0, 0});
            visited[0][0] = true;

            time += 1;

            while (!pos.isEmpty()) {

                int[] posNow = pos.poll();

                for (int i = 0; i < 4; i++) {
                    int dx = posNow[0] + nx[i];
                    int dy = posNow[1] + ny[i];

                    if (dx < 0 || dx >= n || dy < 0 || dy >= m || visited[dx][dy]) continue;

                    if (map[dx][dy] == 0) {
                        pos.offer(new int[] {dx, dy});
                    }
                    else if (map[dx][dy] == 1) {
                        cheeseNum -= 1;
                        map[dx][dy] = 0;
                    }
                    visited[dx][dy] = true;
                }
            }
        }

        System.out.println(time);
        System.out.println(result);
    }
}
