import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int h,w;
    static int[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        list = new int[w];


        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < w; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }

        System.out.print(solution());

    }

    private static int solution() {

        int sum = 0;
        // 현재 위치 기준으로 좌우의 max 값 비교, 더 작은 값 높이만큼 빗물 채워짐
        for (int cur = 1; cur < w-1; cur++) {
            int d = list[cur];
            int lm = d;
            int rm = d;
            for (int l = cur-1; l >= 0; l--) {
                lm = Math.max(lm, list[l]);
            }
            for (int r = cur+1; r < w; r++) {
                rm = Math.max(rm, list[r]);
            }
            sum += Math.min(lm,rm) - d;
        }

        return sum;
    }
}
