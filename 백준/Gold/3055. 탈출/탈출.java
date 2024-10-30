import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R;
    static int C;
    static Character arr[][];

    static Queue<int[]> queue;
    static Queue<int[]> queueWater;

    static int[] nx = {0, 0, 1, -1};  // 동, 서, 남, 북
    static int[] ny = {1, -1, 0, 0};

    static boolean flag = false; // 도착 여부

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new Character[R][C];

        queue = new LinkedList<>();
        queueWater = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                arr[i][j] = line.charAt(j);
                if (arr[i][j] == '*') {
                    queueWater.offer(new int[] {i, j});
                } else if (arr[i][j] == 'S') {
                    queue.offer(new int[] {i, j});
                }
            }
        }

        int time = 0;

        while (true) {
            time++;
            bfsWater(); // 물 이동

//            for (int i = 0; i < R; i++) {
//                System.out.println();
//                for (int j = 0; j < C; j++) {
//                    System.out.print(arr[i][j]);
//                }
//            }
            // 물이 이동한 후 고슴도치 이동
            if (!bfsHedge()) {
//                for (int i = 0; i < R; i++) {
//                    System.out.println();
//                    for (int j = 0; j < C; j++) {
//                        System.out.print(arr[i][j]);
//                    }
//                }
                break; // 더 이상 이동할 수 없음
            }
        }

        // 결과 출력
        System.out.println(flag ? time : "KAKTUS");
    }

    public static boolean bfsHedge() {
        int size = queue.size();
        if (size == 0) return false; // 더 이상 고슴도치가 없으면 종료

        for (int s = 0; s < size; s++) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];

            for (int i = 0; i < 4; i++) {
                int dx = x + nx[i];
                int dy = y + ny[i];
                if (dx >= 0 && dx < R && dy >= 0 && dy < C) {
                    // 비버 소굴에 도착했을 경우
                    if (arr[dx][dy] == 'D') {
                        flag = true;
                        return false; // 고슴도치가 도착했으므로 종료
                    }
                    // 이동할 수 있는 위치
                    else if (arr[dx][dy] == '.') {
                        arr[dx][dy] = 'v'; // 방문 처리
                        queue.offer(new int[] {dx, dy});
                    }
                }
            }
        }

        return true; // 이동 가능
    }

    public static void bfsWater() {
        int size = queueWater.size();

        for (int s = 0; s < size; s++) {
            int[] now = queueWater.poll();
            int x = now[0];
            int y = now[1];

            // 물은 네 방향으로 확산
            for (int i = 0; i < 4; i++) {
                int dx = x + nx[i];
                int dy = y + ny[i];
                if (dx >= 0 && dx < R && dy >= 0 && dy < C) {
                    // 물이 빈 공간으로 가는 경우
                    if (arr[dx][dy] == '.') {
                        arr[dx][dy] = '*'; // 물로 방문 처리
                        queueWater.offer(new int[] {dx, dy}); // 큐에 물 추가
                    }
                }
            }
        }
    }
}

