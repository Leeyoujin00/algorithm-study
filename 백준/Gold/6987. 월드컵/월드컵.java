import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] result;
    static int[] s = {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4};
    static int[] v = {1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5};
    static boolean flag;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        result = new int[6][3];

        for (int tc = 0; tc < 4; tc++) {
            st = new StringTokenizer(br.readLine());
            int cnt = 0;
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    result[i][j] = Integer.parseInt(st.nextToken());
                    cnt += result[i][j];
                }
            }
            if (cnt != 30) {
                System.out.println(0);
                continue;
            }
            flag = false;
            backtracking(0, 0, 0);
            int answer = 0;
            if (flag) answer = 1;

            System.out.println(answer);
        }
    }

    static void backtracking(int n, int sIdx, int vIdx) {

        // 경기가 15번 진행되었다면 종료
        if (n == 15) {
            flag = true;
            return;
        }

        //s국이 승리
        if (result[s[sIdx]][0] > 0 && result[v[vIdx]][2] > 0) {
            result[s[sIdx]][0]--;
            result[v[vIdx]][2]--;
            backtracking(n + 1, sIdx + 1, vIdx + 1);
            result[s[sIdx]][0]++;
            result[v[vIdx]][2]++;
        }
        //무승부
        if (result[s[sIdx]][1] > 0 && result[v[vIdx]][1] > 0) {
            result[s[sIdx]][1]--;
            result[v[vIdx]][1]--;
            backtracking(n + 1, sIdx + 1, vIdx + 1);
            result[s[sIdx]][1]++;
            result[v[vIdx]][1]++;
        }
        //s국이 패
        if (result[s[sIdx]][2] > 0 && result[v[vIdx]][0] > 0) {
            result[s[sIdx]][2]--;
            result[v[vIdx]][0]--;
            backtracking(n + 1, sIdx + 1, vIdx + 1);
            result[s[sIdx]][2]++;
            result[v[vIdx]][0]++;
        }

    }
}