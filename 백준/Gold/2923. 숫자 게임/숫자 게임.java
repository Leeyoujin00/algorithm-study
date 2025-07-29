import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] arr1 = new int[101]; // 현재 라운드까지 idx를 말한 횟수
    static int[] arr2 = new int[101];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr1[a]++;
            arr2[b]++;
            sb.append(solve() + "\n");
        }

        System.out.print(sb);
    }

    private static int solve() {

        int[] tmp1 = arr1.clone();
        int[] tmp2 = arr2.clone();
        int idx1 = 0;
        int idx2 = 100;
        int max = 0;

        while (idx1 <= 100 && idx2 >= 0) {

            if (tmp1[idx1] == 0) {
                idx1++;
                continue;
            }
            if (tmp2[idx2] == 0) {
                idx2--;
                continue;
            }

            max = Math.max(max, idx1+idx2);

            if (tmp1[idx1] > tmp2[idx2]) {
                tmp1[idx1] -= tmp2[idx2];
                tmp2[idx2] = 0;
                idx2--;
            }
            else if (tmp1[idx1] < tmp2[idx2]) {
                tmp2[idx2] -= tmp1[idx1];
                tmp1[idx1] = 0;
                idx1++;
            }
            else {
                tmp1[idx1] = 0;
                tmp2[idx2] = 0;
                idx1++;
                idx2--;
            }
        }

        return max;

    }
}
