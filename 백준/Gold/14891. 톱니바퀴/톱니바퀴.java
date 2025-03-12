import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int k;
    //static Queue<Wheel> que1, que2, que3, que4 = new LinkedList<>();
    static char[][] wheel;
    static int[] dir;
    static List<int[]> move = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        wheel = new char[4][8];
        for (int i = 0; i < 4; i++) {
            String str = br.readLine();
            for (int j = 0; j < 8; j++) {
                wheel[i][j] = str.charAt(j);
            }
        }

        // 개별 회전 출력해봄
//        rotation(3, 1);
//        for (int i = 0; i < 8; i++) {
//            System.out.print(wheel[3][i]);
//        }
//        rotation(3, -1);
//        for (int i = 0; i < 8; i++) {
//            System.out.print(wheel[3][i]);
//        }
//
//        System.out.println("=============");

        k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());
            simulation(num, dir);
        }

        int sum = 0;
        sum += (wheel[0][0]-'0') + 2 * (wheel[1][0]-'0') + 4 * (wheel[2][0]-'0') + 8 * (wheel[3][0]-'0');
//        for (int i = 0; i < 4; i++) {
//            System.out.println(wheel[i][0]);
//        }
        System.out.print(sum);
    }

    // 모든 톱니바퀴를 연쇄적으로 회전시킨다.
    private static void simulation(int w, int dir) {


        char left = wheel[w][6];
        char right = wheel[w][2];

        rotation(w,dir);

        //int idx = 2;
        int tmpDir = dir;
        // 해당 바퀴의 왼쪽에 있는 바퀴들 회전
        for (int i = w-1; i >= 0; i--) {
            // 서로 다른 극이면 회전한다.
            if (wheel[i][2] != left) {
                tmpDir *= -1;
                left = wheel[i][6];
                rotation(i, tmpDir);
            }
            else break;

        }

        tmpDir = dir;
        // 해당 바퀴의 오른쪽에 있는 바퀴들 회전
        for (int i = w+1; i < 4; i++) {
            // 서로 다른 극이면 회전한다.
            if (wheel[i][6] != right) {
                tmpDir *= -1;
                right = wheel[i][2];
                rotation(i, tmpDir);
            }
            else break;
        }
    }

    // 각 톱니바퀴 회전시키는 함수
    private static void rotation(int w, int dir) {

        // 1이면 시계방향, -1이면 반시계방향
        // 시계방향 회전
        if (dir == 1) {
            //  1 = 0 2 = 1 ... 0 = 7
            // 2 3 4 5
            char cur;
            char prev = wheel[w][7];
            for (int i = 0; i < 8; i++) {
                cur = wheel[w][i];
                wheel[w][i] = prev;
                prev = cur;
            }
        }

        // 반시계방향 회전
        if (dir == -1) {
            char cur;
            char prev = wheel[w][0];
            for (int i = 7; i >= 0; i--) {
                cur = wheel[w][i];
                wheel[w][i] = prev;
                prev = cur;
            }
        }

//        for (int i = 0; i < 8; i++) {
//            System.out.print(wheel[w][i] + " ");
//        }
//        System.out.println();
    }
}
