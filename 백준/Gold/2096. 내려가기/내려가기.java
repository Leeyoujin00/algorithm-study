import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int n;
    static int[] minDp;
    static int[] maxDp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());


        minDp = new int[3];
        maxDp = new int[3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int i1 = Integer.parseInt(st.nextToken());
            int i2 = Integer.parseInt(st.nextToken());
            int i3 = Integer.parseInt(st.nextToken());

            if (i == 0) {
                minDp[0] = i1;
                minDp[1] = i2;
                minDp[2] = i3;

                maxDp[0] = i1;
                maxDp[1] = i2;
                maxDp[2] = i3;
            }
            else {
                int d1 = minDp[0];
                int d2 = minDp[1];
                int d3 = minDp[2];
                minDp[0] = i1 + Math.min(d1,d2);
                minDp[2] = i3 + Math.min(d2,d3);
                minDp[1] = i2 + Math.min(Math.min(d1,d2),d3);

                d1 = maxDp[0];
                d2 = maxDp[1];
                d3 = maxDp[2];
                maxDp[0] = i1 + Math.max(d1,d2);
                maxDp[2] = i3 + Math.max(d2,d3);
                maxDp[1] = i2 + Math.max(Math.max(d1,d2),d3);
            }
        }

        int min = Math.min(Math.min(minDp[0],minDp[1]),minDp[2]);
        int max = Math.max(Math.max(maxDp[0],maxDp[1]),maxDp[2]);
        System.out.print(max +" " +min);

    }
}
