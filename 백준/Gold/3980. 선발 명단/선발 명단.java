import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int c, max;
    static int [][] arr;
    static int [] selected; // 각 포지션에 선택된 선수번호 저장
    static boolean[] visited; // 각 선수 배치여부 저장

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        c = Integer.parseInt(st.nextToken());
        arr = new int[11][11];
        selected = new int[11];

        for (int i = 0; i < c; i++) {
            max = Integer.MIN_VALUE;
            selected = new int[11];
            visited = new boolean[11];
            for (int j = 0; j < 11; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 11; k++) {
                    arr[j][k] = Integer.parseInt(st.nextToken());

                }
            }
            dispose(0, selected);
            System.out.println(max);
        }
    }

    static void dispose(int r, int[] selected) {

        if (r == 11) {
            calculate(selected);
            return;
        }

        for (int i = 0; i < 11; i++) {
            if (!visited[i] && arr[i][r] != 0) {
                visited[i] = true;
                selected[r] = i;
                dispose(r+1, selected);
                visited[i] = false;
            }
        }

    }

    static void calculate(int[] selected) {

        int sum = 0;
        for (int i = 0; i < 11; i++) {
            sum += arr[selected[i]][i];
            //System.out.print(arr[selected[i]][i] + " ");
        }
        //System.out.println();

        max = Math.max(max, sum);
    }
}
