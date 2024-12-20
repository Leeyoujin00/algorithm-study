import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int n;
    static int min;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int move = 0;
    static int[][] result;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        result = new int[n][2];

        for (int tc = 0; tc < n; tc++) {
            int pins = 0;
            move = 0;
            char[][] board = new char[5][9];
            for (int i = 0; i < 5; i++) {
                String str = br.readLine();
                for (int j = 0; j < 9; j++) {
                    board[i][j] = str.charAt(j);
                    if (board[i][j] == 'o') {
                        pins++;
                    }
                }
            }
            min = pins;
            dfs(board, min);
            result[tc][0] = min;
            result[tc][1] = pins - min;
            br.readLine();
        }

        for (int tc = 0; tc < n; tc++) {
            System.out.println(result[tc][0] + " " + result[tc][1]);
        }
    }

    public static void dfs(char[][] board, int pins) {
        if (pins < min) {
            min = pins;
        }

        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 9; y++) {
                if (board[x][y] == 'o') {
                    // 인접 위치 탐색
                    for (int i = 0; i < 4; i++) {
                        int nextX = x + dx[i];
                        int nextY = y + dy[i];
                        int newX = nextX + dx[i];
                        int newY = nextY + dy[i];

                        // 인접 위치에 핀이 있고, 그 다음 칸이 빈칸일 때
                        if (checkTarget(board, nextX, nextY, 'o') && checkTarget(board, newX, newY, '.')) {
                            // 백트래킹 진행
                            board[x][y] = '.';
                            board[nextX][nextY] = '.';
                            board[newX][newY] = 'o';
                            dfs(board, pins-1);
                            board[x][y] = 'o';
                            board[nextX][nextY] = 'o';
                            board[newX][newY] = '.';
                        }
                    }
                }
            }
        }
    }

    static boolean checkTarget(char[][] board, int x, int y, char target) {
        if (x < 0 || x >= 5 || y < 0 || y >= 9) return false;
        return board[x][y] == target;
    }
}

