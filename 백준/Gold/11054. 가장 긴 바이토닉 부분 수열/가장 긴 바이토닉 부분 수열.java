import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static Integer[] LisDp;
    static Integer[] LdsDp;
    static int[] seq;
    static int[] result;
    static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        seq = new int[N];
        LisDp = new Integer[N];
        LdsDp = new Integer[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            LIS(i);
        }

        for (int i = N-1; i >= 0; i--) {
            LDS(i);
        }

        result = new int[N];

        // 디버깅
//        for (int i = 0; i < N; i++) {
//            System.out.println(LisDp[i] + " " + LdsDp[i]);
//        }
//
//        System.out.println("--------");

        for (int i = 0; i < N; i++) {
            result[i] = LisDp[i] + LdsDp[i] - 1;
            //System.out.println(result[i]);
        }

        int len = Arrays.stream(result).max().orElse(0);
//        bw.write(len);
//        bw.flush();
//        bw.close();

        System.out.print(len);

    }

    // 인덱스 k까지의 최대로 증가하는 부분 수열 길이 반환
    public static int LIS(int k) {
        // 아직 처리한 적 없을 때만 진행
        if (LisDp[k] == null) {

            LisDp[k] = 1;

            for (int i = k-1; i >= 0; i--) {
                if (seq[i] < seq[k]) {
                    LisDp[k] = Math.max(LisDp[k], LIS(i)+1);
                }
            }
        }

        return LisDp[k];
    }

    // 인덱스 k까지의 최대로 감소하는 부분 수열 길이 반환
    public static int LDS(int k) {
        // 아직 처리한 적 없을 때만 진행
        if (LdsDp[k] == null) {

            LdsDp[k] = 1;

            for (int i = k+1; i < N; i++) {
                if (seq[i] < seq[k]) {
                    LdsDp[k] = Math.max(LdsDp[k], LDS(i)+1);
                }
            }
        }

        return LdsDp[k];
    }
}
