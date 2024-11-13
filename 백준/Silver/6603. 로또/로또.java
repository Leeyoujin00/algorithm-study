import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] arr;
    static int[] output;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            if (n == 0) {
                break;
            }

            arr = new int[n];
            visited = new boolean[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            output = new int[6];

            combination(output, 0, 0);
            System.out.println();
        }
    }

    public static void combination(int[] output, int start, int r) {

        if (r == 6) {
            printResult(output);
            return;
        }

        for (int i = start; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                output[r] = arr[i];
                combination(output, i+1, r+1);
                visited[i] = false;
            }
        }
    }

    public static void printResult(int[] output) {
        for (int i = 0; i < 6; i++) {
            System.out.print(output[i] + " ");
        }
        System.out.println();
    }
}
