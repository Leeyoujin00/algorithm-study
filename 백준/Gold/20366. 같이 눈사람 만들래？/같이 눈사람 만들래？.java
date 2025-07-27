import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int min = Integer.MAX_VALUE;
    static int[] h;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        h = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            h[i] = Integer.parseInt(st.nextToken());
        }

        // n개의 눈덩이를 이용해 만들 수 있는 눈사람의 키 차이 중 최솟값을 구한다.
        /**
         * 2 3 5 5 9
         * 0 1 1 1 1
         */
        Arrays.sort(h);

        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                twoPointer(i,j,h[i]+h[j]);
            }
        }

        System.out.print(min);
    }


    // 눈사람 하나의 조합을 정하고, 다른 눈사람의 크기를 조정하기 위해 투포인터 사용
    private static void twoPointer(int sl, int sr, int snow1) {

        int l = 0, r = n-1;

        while (l < r) {
            if (l == sl || l == sr) {
                l++;
            }
            if (r == sr || r == sl) {
                r--;
            }
            if (l < 0 || l >= n || r < 0 || r >= n || l >= r) break;

            int snow2 = h[r]+h[l];
            min = Math.min(min,Math.abs(snow1-snow2));

            if (snow1 > snow2) {
                // snow1이 더 크다면,snow2 를 키운다.
                l++;
            } else if (snow1 < snow2) {
                // snow1이 더 작다면,snow2 를 줄인다.
                r--;
            } else break;

            if (l == sl || l == sr) {
                l++;
            }
            if (r == sr || r == sl) {
                r--;
            }

        }
    }
}
