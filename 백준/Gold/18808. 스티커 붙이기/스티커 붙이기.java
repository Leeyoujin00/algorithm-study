import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n,m,k;
    static int[][] map;
    static List<Steaker> steakers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        // 스티커 정보 입력
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            Steaker s = new Steaker(r,c);
            for (int row = 0; row < r; row++) {
                st = new StringTokenizer(br.readLine());
                for (int col = 0; col < c; col++) {
                    s.matrix[row][col] = Integer.parseInt(st.nextToken());
                }
            }
            steakers.add(s);
        }

        // 스티커 붙이기
        for (Steaker s : steakers) {
            attach(s);
        }

        // 차례대로 다 붙이고 난 후 노트북에서 몇 개의 칸이 채워졌는지 센다.
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) count++;
            }
        }

        System.out.println(count);
    }

    private static class Steaker {
        int[][] matrix;
        int r, c;

        public Steaker(int r, int c) {
            this.r = r;
            this.c = c;
            this.matrix = new int[r][c];
        }
    }

    // 스티커를 붙인다.
    private static void attach(Steaker s) {

        int[] degree = {0,90,180,270};
        // 각 회전방향에 대해
        // 검증 후, 가능하다면
        // 붙인다.
        for (int i = 0; i < 4; i++) {
            int[][] matrix = rotation(s.matrix, degree[i]);
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (isValid(j, k, matrix)) {
                        return;
                    }
                }
            }
        }
    }

    // x,y 좌표에 스티커 붙일 수 있는지 검증
    private static boolean isValid(int x, int y, int[][] matrix) {

        int r = matrix.length;
        int c = matrix[0].length;

        if (x+r > n || y+c > m) return false;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i+x][j+y] == 1 && matrix[i][j] == 1) return false;
            }
        }

        // 실제 붙일 수 있는 위치이므로 스티커를 붙인다.
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (matrix[i][j] == 1) map[i+x][j+y] = matrix[i][j];
            }
        }

        return true;
    }

    // 하나의 스티커를 회전시킨다.
    private static int[][] rotation(int[][] arr, int degree) {

        if (degree == 0) return arr;

        int n = arr.length;
        int m = arr[0].length;
        int[][] matrix = new int[m][n];

        switch (degree) {
            case 90: 
            case 270:
                matrix = new int[m][n];
                break;
            case 180:
                matrix = new int[n][m];
                break;
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                switch (degree) {
                    case 90:
                        matrix[i][j] = arr[n-1-j][i];
                        break;
                    case 180:
                        matrix[i][j] = arr[n-1-i][m-1-j];
                        break;
                    case 270:
                        matrix[i][j] = arr[j][m-1-i];
                        break;
                }
            }
        }

        return matrix;
    }
}
