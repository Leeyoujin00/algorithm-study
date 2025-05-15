import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,c,s;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.valueOf(st.nextToken());
        c = Integer.valueOf(st.nextToken());

        arr = new int[n+1][n+1];
        // 앞에 있는 번호의 사건이 먼저 일어남
        for (int i = 0; i < c; i++) {
            st = new StringTokenizer(br.readLine());
            int prev = Integer.valueOf(st.nextToken());
            int next = Integer.valueOf(st.nextToken());
            arr[prev][next] = 1;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i != j && arr[i][k] == 1 && arr[k][j] == 1) {
                        arr[i][j] = 1;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        s = Integer.valueOf(br.readLine());
        for (int i = 0; i < s; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.valueOf(st.nextToken());
            int b = Integer.valueOf(st.nextToken());
            if (arr[a][b] == 1) {
                sb.append(-1 + "\n");
            }
            else if (arr[b][a] == 1) {
                sb.append(1 + "\n");
            }
            else {
                sb.append(0 + "\n");
            }
        }

        System.out.print(sb);
    }
}
