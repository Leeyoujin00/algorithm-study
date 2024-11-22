import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int width, height, M;
    static int arr[][];
    static int addLine; // 추가할 가로선 개수
    static boolean isDone = false;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        width = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());

        arr = new int[height+1][width+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a][b] = 1; // 우측으로 향하는 세로선
            arr[a][b+1] = 2; // 좌측으로 향하는 세로선
        }

        // 가로선 개수를 0-3개로 순차적으로 정하여 결과 계산
        for (int i = 0; i <= 3; i++) {
            addLine = i;
            dfs(0);
            if (isDone) {
                System.out.println(i);
                System.exit(0);
            }
        }

        System.out.println(-1);
    }

    static void dfs(int num) {

        if (num == addLine) {
            // 가로선 다 선택했으면, 사다리 조건 만족 여부 확인
            if (check()) {
                isDone = true;
            }
            return;
        }

        for (int i = 1; i <= height; i++) {
            for (int j = 1; j < width; j++) {
                // 해당 위치에 가로선 추가 가능한지 확인
                if (arr[i][j] == 0 && arr[i][j+1] == 0) {
                    arr[i][j] = 1;
                    arr[i][j+1] = 2;
                    dfs(num+1);
                    // 백트래킹
                    arr[i][j] = 0;
                    arr[i][j+1] = 0;
                }
            }
        }
    }

    static boolean check() {

        for (int i = 1; i <= width; i++) {
            int ni = i;
            int nj = 1;

            while (nj <= height) {
                if (arr[nj][ni] == 1) ni++; //우측으로 이동
                else if (arr[nj][ni] == 2) ni--; //좌측으로 이동
                nj++;
            }

            if (ni != i) return false;
        }
        return true;
    }
}
