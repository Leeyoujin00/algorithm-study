import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static int[] sup;
    static int[] complement;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        sup = new int[n+1];
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            // 부하직원 정보 저장
            sup[i+1] = num;
        }

        complement = new int[n+1];
        for (int c = 0; c < m; c++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            complement[i] += w;
        }

        // 부하직원의 점수는 직속 상사의 점수를 더하 것과 같음
        System.out.print(complement[1] + " ");
        for (int i = 1; i < n; i++) {
            complement[i+1] += complement[sup[i+1]];
            System.out.print(complement[i+1] + " ");
        }
    }
}
