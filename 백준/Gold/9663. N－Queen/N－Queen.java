import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int n;
    static int count = 0;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        nQueen(0);
        System.out.println(count);

    }

    /**
     *
     * 0 1 2 3
     *
     */

    public static void nQueen(int depth) {
        if (depth == n) {
            count++;
            return;
        }

        for (int i = 0; i < n; i++) {
            arr[depth] = i;
            if (Possibility(depth)) {
                nQueen(depth+1);
            }
        }
    }

    public static boolean Possibility(int col) {

        for (int i = 0; i < col; i++) {
            if (arr[i] == arr[col]) return false; // 같은 행에 있으면 안됨

            // 대각선에 있는지 확인
            if (Math.abs(i - col) == Math.abs(arr[i] - arr[col])) return false;
        }

        return true;
    }



}
