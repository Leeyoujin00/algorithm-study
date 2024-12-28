import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        boolean[] visited = new boolean[n+1];
        int[] selected = new int[n];
        backtracking(0, selected, visited);
    }

    static void backtracking(int r, int[] selected, boolean[] visited) {
        if (r == n) {
            for (int s : selected) {
                System.out.print(s + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                selected[r] = i;
                visited[i] = true;
                backtracking(r+1, selected, visited);
                visited[i] = false;
            }
        }
    }
}
