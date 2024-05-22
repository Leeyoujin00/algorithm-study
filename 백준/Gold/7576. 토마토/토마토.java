import java.io.*;
import java.util.*;

public class Main {
    public static Deque<int[]> deq1;
    public static Deque<int[]> deq2;
    public static int[][] tomatos;
    public static int M;
    public static int N;
    public static int maxDay;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] readLine = br.readLine().split(" ");
        M = Integer.parseInt(readLine[0]);
        N = Integer.parseInt(readLine[1]);

        deq1 = new LinkedList<>();
        deq2 = new LinkedList<>();
        tomatos = new int[N][M];
        // 토마토 배열 인자로 받음
        for (int i = 0; i < N; i++) {
            readLine = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                tomatos[i][j] = Integer.parseInt(readLine[j]);
                if (tomatos[i][j] == 1) {
                    deq1.add(new int[] {i, j});
                }
            }
        }

        int result = bfs() - 1;
        if (getAnswer() == -1) {
            System.out.println(-1);
        }
        else {
            System.out.println(result);
        }


    }

    public static int bfs() {
        int x = 0;
        int y = 0;

        int day = 0;

        // 큐 2개 모두 비어있을 때까지 반복 진행
        while (!deq1.isEmpty() || !deq2.isEmpty()) {
            day += 1;
            if (day % 2 == 1) {
                // 반복 횟수가 홀수이면 deq1에 원소가 있음
                while (!deq1.isEmpty()) {
                    x = deq1.peek()[0];
                    y = deq1.pop()[1];

                    // 꺼낸 위치의 상하좌우 위치의 토마토에 1 전파
                    if (x + 1 < N) {
                        propagate(x + 1, y, 2);
                    }
                    if (y + 1 < M) {
                        propagate(x, y + 1, 2);
                    }
                    if (x - 1 >= 0) {
                        propagate(x - 1, y, 2);
                    }
                    if (y - 1 >= 0) {
                        propagate(x, y - 1, 2);
                    }
                }
            }
            // 반복 횟수가 짝수이면 deq2에 원소가 있음
            else if (day % 2 == 0) {
                while (!deq2.isEmpty()) {
                    x = deq2.peek()[0];
                    y = deq2.pop()[1];

                    // 꺼낸 위치의 상하좌우 위치의 토마토에 1 전파
                    if (x + 1 < N) {
                        propagate(x + 1, y, 1);
                    }
                    if (y + 1 < M) {
                        propagate(x, y + 1, 1);
                    }
                    if (x - 1 >= 0) {
                        propagate(x - 1, y, 1);
                    }
                    if (y - 1 >= 0) {
                        propagate(x, y - 1, 1);
                    }
                }
            }
        }

        return day;
    }

    public static void propagate(int x, int y, int deqNum) {
        // 토마토 배열의 [x][y] 위치에 익지 않은 토마토(0)가 있을 때
        if(tomatos[x][y] == 0) {
            // 해당 위치의 토마토에 1을 전파
            if (deqNum == 1) {
                tomatos[x][y] = 1;
                deq1.add(new int[] {x, y});
            }
            else if (deqNum == 2) {
                tomatos[x][y] = 1;
                deq2.add(new int[] {x, y});
            }
        }
    }

    public static int getAnswer() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tomatos[i][j] == 0) {
                    return -1;
                }
            }
        }
        return 1;
    }


}
