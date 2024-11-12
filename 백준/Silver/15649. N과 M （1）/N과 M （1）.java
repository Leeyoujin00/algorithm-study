import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[N+1];
        int[] arr = new int[M];

        dfs(arr, visited, M);
    }

    public static void dfs(int[] arr, boolean[] visited, int r) {

        if (r == 0) {
            printResult(arr);
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[M - r] = i;
                dfs(arr, visited, r-1);
                visited[i] = false;
            }
        }
    }

    public static void printResult(int[] arr) {

        for (int i = 0; i < M; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
