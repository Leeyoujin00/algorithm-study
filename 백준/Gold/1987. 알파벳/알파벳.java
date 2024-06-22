import java.util.*;
import java.io.*;

// 골드 4. 알파벳
public class Main {
    static int N = 0;
    static int R;
    static int C;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] readLine = br.readLine().split(" ");
        R = Integer.parseInt(readLine[0]);
        C = Integer.parseInt(readLine[1]);

        char[][] arr = new char[R][C];
        boolean[] visited = new boolean[26]; // 각 알파벳 방문 여부 저장

        for (int i = 0; i < R; i++) {
            String read = br.readLine();
            for (int j = 0; j < C; j++) {
                arr[i][j] = read.charAt(j);
            }
        }

//        System.out.println(arr[0][0]);
//        System.out.println(Character.getNumericValue(arr[0][1]));

        if(R == 1 && C == 1) {
            System.out.println(1);
        }
        else {
            dfs(arr, 0, 0, 0, visited);
            System.out.println(N);
        }


    }

    private static void dfs(char[][] arr, int x, int y, int n, boolean[] visited) {
        if (!(visited[Character.getNumericValue(arr[x][y]) - 10])) {
            if (x + 1 < R) {
                visited[Character.getNumericValue(arr[x][y]) - 10] = true;
                dfs(arr, x + 1, y, n+1, visited);
                visited[Character.getNumericValue(arr[x][y]) - 10] = false;
            }
            if (y + 1 < C) {
                visited[Character.getNumericValue(arr[x][y]) - 10] = true;
                dfs(arr, x, y+1, n+1, visited);
                visited[Character.getNumericValue(arr[x][y]) - 10] = false;
            }
            if (0 <= x - 1) {
                visited[Character.getNumericValue(arr[x][y]) - 10] = true;
                dfs(arr, x - 1, y, n+1, visited);
                visited[Character.getNumericValue(arr[x][y]) - 10] = false;
            }
            if (0 <= y - 1) {
                visited[Character.getNumericValue(arr[x][y]) - 10] = true;
                dfs(arr, x, y - 1, n+1, visited);
                visited[Character.getNumericValue(arr[x][y]) - 10] = false;
            }

        }

        else {
            if (N < n) {
                N = n;
            }
        }
    }
}

