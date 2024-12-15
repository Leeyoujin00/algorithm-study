import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static int n;
    static int[] prime = {2,3,5,7};
    static List<Integer> result;
    static int[] number;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        number = new int[n];
        result = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            number[0] = prime[i];
            backtracking(1);
        }

        Collections.sort(result);
        for (int r : result) {
            System.out.println(r);
        }


    }

    public static void backtracking(int depth) {

        // n자리수 다 선택됐을 경우, 저장
        if (depth == n) {
            //result.add(number);
            depth -= 1;
            int a = number[depth];

            for (int i = depth-1; i >= 0; i--) {
                a += Math.pow(10, depth - i) * number[i];
            }
            result.add(a);
            return;
        }

        // 0도 가능?
        for (int i = 1; i <= 9; i++) {
            number[depth] = i;
            if (isPrime(number, depth)) {
                backtracking(depth+1);
            }
            //else return;
        }
    }

    // 소수인지 확인하는 함수 2222 3 0 -> 3, 1 -> 2
    public static boolean isPrime(int[] num, int depth) {

        int a = num[depth];

        for (int i = depth-1; i >= 0; i--) {
            a += Math.pow(10, depth - i) * num[i];
        }

        for (int i = 2; i <= Math.sqrt(a); i++) {
            if (a % i == 0) return false;
        }
        //System.out.println(a);

        return true;
    }


}
