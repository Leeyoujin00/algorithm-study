import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int k;
    static boolean[] visited;
    static String[] arr; // 부등호 저장하는 배열
    static List<String> list = new ArrayList<>();

    static void dfs(String num, int idx) {

        // 주어진 부등호의 모든 조건을 완성하면 리턴
        if (idx == k+1) {
            // 부등호가 성립되는 모든 경우의 수가 저장됨
            //System.out.println(num);
            list.add(num);
            return;
        }

        // 0~9까지의 수
        for (int j = 0; j < 10; j++) {
            // 사용한 숫자인지 체크
            if (!visited[j]) {
                // 부등호 조건에 맞는 숫자이면 사용
                if (idx == 0 || check(Character.getNumericValue(num.charAt(idx-1)), j, arr[idx-1])) {
                    //System.out.println(j);
                    visited[j] = true;
                    dfs(num+j, idx+1);
                    visited[j] = false;
                }
            }
        }
    }

    static boolean check(int a, int b, String c) {
        // 현재 사용하는 숫자와 j번째 숫자와 비교하여, 부등호가 성립되면 true
        if (c.equals(">")) {
            if (a < b) return false;
        }
        if (c.equals("<")) {
            if (a > b) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());

        k = Integer.parseInt(br.readLine());
        arr = br.readLine().split(" ");

        visited = new boolean[10];

        dfs("", 0);

        System.out.println(list.get(list.size()-1));
        System.out.println(list.get(0));


    }
}
