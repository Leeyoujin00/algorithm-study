import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int n,x;
    static int[] visitor;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        visitor = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            visitor[i] = Integer.parseInt(st.nextToken());
        }
        int sum = 0;
        for (int i = 0; i < x; i++) {
            sum += visitor[i];
        }

        int max = sum;
        int cnt = 1;
        for (int i = 0; i < n - x; i++) {
            sum -= visitor[i];
            sum += visitor[i+x];
            //System.out.println(sum);
            if (max < sum) {
                max = sum;
                cnt = 1;
            }
            else if (max == sum) {
                cnt++;
            }
        }

        if (max == 0) System.out.print("SAD");
        else {
            System.out.println(max);
            System.out.print(cnt);
        }
    }
}
