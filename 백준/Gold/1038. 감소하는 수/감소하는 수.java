import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static int n;
    static List<Long> result;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        result = new ArrayList<>();
        backtracking(0, 0, 0, -1);

        Collections.sort(result);

//        

        if (result.size() < n+1) {
            System.out.print(-1);
        }
        else System.out.println(result.get(n));

    }

    // 바로 앞의 수보다만 감소하면 됨, 작은 수부터 세야 함
    // 일의 자리수부터 수 정함,
    // 1 2 3 4 5 6 7 8 9
    // 다 구하고 정렬
    static void backtracking(int r, long num, int cnt, int max) {

        if (cnt == n+1) {
            return;
        }

        //int max =
        for (int i = max+1; i <= 9; i++) {
//            if (r == 0 && i == 0) {
//                continue;
//            }
            num += i * Math.pow(10, r);
            result.add(num);
            backtracking(r+1, num, cnt+1, i);
            num -= i * Math.pow(10, r);
        }
    }
}
