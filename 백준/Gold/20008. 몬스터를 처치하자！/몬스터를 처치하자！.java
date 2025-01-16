import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, hp;
    static int[][] skill;
    static int min = Integer.MAX_VALUE;
    static int[] t; // 각 스킬들의 사용가능한 최소 시간 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        hp = Integer.parseInt(st.nextToken());
        t = new int[n];

        skill = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            skill[i][0] = c;
            skill[i][1] = d;
            //available[i] = c;
        }

        //backtracking(0, hp);
        dfs(0, hp);
        System.out.print(min);

    }

    static void dfs(int time, int hp) {

        // 몬스터 죽었으면 최소시간 확인 및 갱신
        if (hp <= 0) {
            min = Math.min(min, time);
            return;
        }

        // 현재 사용가능한 스킬 있는지 확인
        boolean flag = false;

        for (int i = 0; i < n; i++) {
            if (t[i] <= time) {
                flag = true;
                int prev = t[i];
                t[i] = time+skill[i][0];
                hp -= skill[i][1];
                dfs(time+1, hp);
                hp += skill[i][1];
                t[i] = prev;
            }
        }

        if (!flag) {
            dfs(time+1, hp);
        }

    }
}
