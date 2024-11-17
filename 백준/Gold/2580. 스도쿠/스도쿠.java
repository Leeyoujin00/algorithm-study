import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[9][9];

        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sudoku(0,0);

    }

    static void sudoku(int row, int col) {

        // 마지막 열까지 왔으면, 다음 행으로 진행
        if (col == 9) {
            sudoku(row+1, 0);
            return;
        }

        // 마지막 행까지 왔으면, 결과 출력
        if (row == 9) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(map[i][j]).append(' ');
                }
                sb.append('\n');
            }
            System.out.print(sb);
            System.exit(0);
        }

        if (map[row][col] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (possibility(row, col, i)) {
                    map[row][col] = i;
                    sudoku(row, col+1);
                }
            }
            map[row][col] = 0;
            return;
        }

        sudoku(row, col+1);
    }

    static boolean possibility(int row, int col, int n) {

        // 같은 행에 n이 없는지 확인
        for (int i = 0; i < 9; i++) {
            if (map[row][i] == n) {
                return false;
            }
        }

        // 같은 열에 n이 없는지 확인
        for (int i = 0; i < 9; i++) {
            if (map[i][col] == n) {
                return false;
            }
        }

        // 같은 정사각형에 n이 없는지 확인
        int setRow = (row/3) * 3;  // (0/ 3/ 6)
        int setCol = (col/3) * 3;

        for (int i = setRow; i < setRow+3; i++) {
            for (int j = setCol; j < setCol+3; j++) {
                if (map[i][j] == n) {
                    return false;
                }
            }
        }

        return true;
    }
}
