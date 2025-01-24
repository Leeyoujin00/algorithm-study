import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static int[][] arr;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        arr = new int[n + 1][n + 1];
        for(int i = 1; i< n+1; i++) {
            arr[i][i] = 1;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // a > b
            arr[a][b] = 1;
            arr[b][a] = -1;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (arr[i][k] == 1 && arr[k][j] == 1) {
                        arr[i][j] = 1;
                    }
                    if (arr[i][k] == -1 && arr[k][j] == -1) {
                        arr[i][j] = -1;
                    }
                }
            }
        }

        result = new int[n];
        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            for (int j = 1; j <= n; j++) {
                if (arr[i][j] != 0) {
                    cnt++;
                }
            }
            result[i-1] = n - cnt;
        }

        for (int r : result) {
            System.out.println(r);
        }
    }
}
