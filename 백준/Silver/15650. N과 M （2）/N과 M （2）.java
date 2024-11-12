import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int m;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] output = new int[m];
        boolean[] visited = new boolean[n+1];

        combination(output, visited, 1, 0);
    }

    public static void combination(int[] output, boolean[] visited, int start, int r) {

        if (r == m) {
            printResult(output);
            return;
        }

        for (int i = start; i <= n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                output[r] = i;
                combination(output, visited, i+1, r+1);
                visited[i] = false;
            }
        }
    }

    public static void printResult(int[] output) {
        for (int i = 0; i < m; i++) {
            System.out.print(output[i] + " ");
        }
        System.out.println();
    }
}
