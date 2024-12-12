
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main{

    static int n,l,r,x;
    static int[] a;
    static boolean[] visited;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        // 2개 이상 n개 이하의 문제를 고르는 경우 탐색
        for (int i = 2; i <= n; i++) {
            visited = new boolean[n];
            int[] arr = new int[i];
            choose(i, 0, 0, arr);
        }

        System.out.println(result);
    }

    static void choose(int num, int start, int c, int[] arr) {

        if (c == num) {
            if (check(arr)) {
                result++;
            }
            return;
        }

        for (int i = start; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[c] = i;
                choose(num, i+1, c+1, arr);
                visited[i] = false;
            }
        }
    }

    static boolean check(int[] list) {

        //Collections.sort(list);
        //Arrays.sort(list);

        int[] cur = new int[list.length];
        for (int i = 0; i < list.length; i++) {
            cur[i] = a[list[i]];
        }

        int sum = 0;
        for (int i : cur) {
            sum += i;
        }

        if (sum < l || r < sum) return false;

        Arrays.sort(cur);

        if (cur[cur.length-1] - cur[0] < x) return false;


//        for (int i = 0; i < list.length; i++) {
//            System.out.print(cur[i] + " ");
//        }
//        for (int i = 0; i < list.length; i++) {
//            System.out.print(list[i] + " ");
//        }
//        System.out.println();
        return true;
    }
}
