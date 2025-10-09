import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] scores;
    static int[][] match; // 각 경기를 진행하는 두 팀 번호
    static int GAME_COUNT = 0;// 총 경기 횟수
    static int TEAM_NUM = 6;
    static boolean isPossible = false;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 총 경기 수를 계산한다.
        for (int i = TEAM_NUM-1; i >= 1; i--) {
            GAME_COUNT += i;
        }

        int tc = 4;
        // 각 경기 진행하는 두 팀 번호 세팅
        match = new int[GAME_COUNT][2];
        int idx = 0;
        for (int i = 0; i < TEAM_NUM - 1; i++) {
            for (int j = i+1; j < TEAM_NUM; j++) {
                match[idx][0] = i;
                match[idx][1] = j;
                idx++;
            }
        }

        StringBuilder sb = new StringBuilder();
        while (tc-- > 0) {
            // 각 경기 결과 저장
            scores = new int[TEAM_NUM][3];

            st = new StringTokenizer(br.readLine());
            
            int sum = 0;
            for (int i = 0; i < TEAM_NUM; i++) {
                for (int j = 0; j < 3; j++) {
                    scores[i][j] = Integer.parseInt(st.nextToken());
                    sum += scores[i][j];
                }
            }

            // 주어진 점수가 가능한 매치 결과인지 확인
            if (sum != 30) {
                sb.append(0 + " ");
                continue;
            }

            isPossible = false; // 초기화
            backtracking(0);

            if (isPossible) sb.append(1 + " ");
            else sb.append(0 + " ");
        }

        System.out.print(sb);
    }

    private static void backtracking(int match_num) {

        if (isPossible) {
            return;
        }

        if (match_num == GAME_COUNT) {
            isPossible = true;
            return;
        }

        int t = match[match_num][0];
        int s = match[match_num][1];

        // 내가 승, 상대가 패
        if (scores[t][0] > 0 && scores[s][2] > 0) {
            scores[t][0]--;
            scores[s][2]--;
            backtracking(match_num+1);
            scores[t][0]++;
            scores[s][2]++;
        }

        // 무승부
        if (scores[t][1] > 0 && scores[s][1] > 0) {
            scores[t][1]--;
            scores[s][1]--;
            backtracking(match_num+1);
            scores[t][1]++;
            scores[s][1]++;
        }

        // 내가 패, 상대가 승
        if (scores[t][2] > 0 && scores[s][0] > 0) {
            scores[t][2]--;
            scores[s][0]--;
            backtracking(match_num+1);
            scores[t][2]++;
            scores[s][0]++;
        }

    }
}
