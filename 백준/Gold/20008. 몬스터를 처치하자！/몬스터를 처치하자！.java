import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int n,hp;
    static int[] c; // 각 스킬의 대기시간 저장
    static int[] t;
    static int[] d; // 각 스킬의 대미지 저장
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        hp = Integer.parseInt(st.nextToken());

        c = new int[n];
        t = new int[n];
        d = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            c[i] = Integer.parseInt(st.nextToken());
            //t[i] = c[i];
            d[i] = Integer.parseInt(st.nextToken());
        }

        backtracking(hp, 0);
        System.out.println(min);

    }

    // 현재 사용 가능한 스킬들에 대해 매 초 백트래킹 수행
    private static void backtracking(int hp, int time) {
        // 몬스터 처치 완료
        if (hp <= 0) {
            min = Math.min(min, time);
            return;
        }

        // 현재 사용 가능한 스킬 존재여부 저장
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            if (t[i] <= time) { // 해당 스킬 사용
                flag = true;
                int prev = t[i];
                t[i] = time + c[i];
                hp -= d[i];
                backtracking(hp, time+1);
                hp += d[i];
                t[i] = prev;
            }
        }

        if (!flag) {
            backtracking(hp, time+1);
        }

    }
}
