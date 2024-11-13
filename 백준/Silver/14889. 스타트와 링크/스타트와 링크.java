import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {

    static int[][] s;
    static int n;
    static boolean[] visited;
    static int min = 2000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = new int[n][n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                s[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] output = new int[n/2];

        combination(0,0);
        System.out.println(min);

    }

    static void combination(int start, int r) {

        if (r == n/2) {
            calculate(visited);
            return;
        }

        for (int i = start; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                //output[r] = i;
                combination(i+1, r+1);
                visited[i] = false;
            }
        }
    }

    static void calculate(boolean[] check) {

        int start = 0; // 스타트팀 점수
        int link = 0; // 링크팀 점수

        // 각 팀 점수 계산
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (check[i] && check[j]) start+= s[i][j];
                else if (!check[i] && !check[j]) link += s[i][j];
            }
        }

//        //링크팀원 계산
//        int[] linkIdx = new int[n/2];
//        List<Integer> startMember = Arrays.stream(startIdx)
//                .boxed()
//                .collect(Collectors.toList());
//
//        int idx = 0;
//        for (int i = 0; i < n; i++) {
//            if (!startMember.contains(i)) {
//                linkIdx[idx++] = i;
//            }
//        }
//
//        // 링크팀 점수 계산
//        for (int i = 0; i < n/2; i++) {
//            for (int j = 0; j < n/2; j++) {
//                link += s[linkIdx[i]][linkIdx[j]];
//            }
//        }

        // 최소점수차 갱신
        if (Math.abs(start-link) < min) {
            min = Math.abs(start-link);
        }

    }
}
