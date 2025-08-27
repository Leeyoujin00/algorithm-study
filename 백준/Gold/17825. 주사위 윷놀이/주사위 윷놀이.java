import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[] dices;
    static List<Node>[] map; // 인접리스트
    static final int INF = 41;
    static int[][] ck;
    static int max = 0;
    static int[][] pieces; // 각 말의 칸, 경로 저장

    static class Node {
        int num; // 번호
        int routeNum; // 경로 번호 - 반시계방향의 원 경로는 0

        public Node(int num, int routeNum) {
            this.num = num;
            this.routeNum = routeNum;
        }
    }

    /**
     * 맵 형성 - 거점은 시작점, 도착점 제외 10, 20, 30 (파란색, 즉 13으로만 갈 수 있음)/
     */

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dices = new int[10];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 10; i++) {
            dices[i] = Integer.parseInt(st.nextToken());
        }

        // ========== 맵 형성 시작 ===========
        map = new List[INF + 1]; // 도착지점 idx == 41
        for (int i = 0; i <= INF; i++) {
            map[i] = new ArrayList<>();
        }

        // 경로 1. 2씩 증가
        for (int i = 0; i < 38; i+=2) {
            map[i].add(new Node(i+2, 0)); // 다음 도착 지점은 현재 수 + 2
        }
        map[38].add(new Node(40, 2));
        // 10 경로 추가
        map[10].add(new Node(13, 1));
        map[13].add(new Node(16, 1));
        map[16].add(new Node(19, 1));
        map[19].add(new Node(25, 2));
        // 20 경로 추가
        map[20].add(new Node(22, 1));
        map[22].add(new Node(24, 1));
        map[24].add(new Node(25, 2));
        // 30 경로 추가
        map[30].add(new Node(28, 1));
        map[28].add(new Node(27, 1));
        map[27].add(new Node(26, 1));
        map[26].add(new Node(25, 2));

        // 25 경로 추가 -> 25에서 한번 경로 식별 바꿔줘야 함(경로 2에 30있으므로)
        map[25].add(new Node(30, 2));
        map[30].add(new Node(35, 2));
        map[35].add(new Node(40, 2));

        // 도착지 설정
        map[40].add(new Node(41, 2));
        // ========== 맵 형성 종료 ===========

        ck = new int[INF+1][3]; // 각 칸에 있는 말 번호 나타냄, 0이면 아무 말도 없음 의미
        pieces = new int[5][2];

        backtracking(0, 0);
        System.out.print(max);
    }

    private static void backtracking(int round, int sum) {
        //System.out.println("round = " + round + "sum = " + sum);
        // 모든 회차 이동 끝났으면, 종료
        if (round == 10) {
            max = Math.max(max, sum);
            return;
        }

        // 다음 회차 이동 진행
        for (int i = 1; i <= 4; i++) { // 모든 말에 대해 진행
            int prevS = pieces[i][0];
            int prevR = pieces[i][1];
            if (prevS == INF) continue; // 도착칸에 있는 말은 선택 불가
            Move m = dfs(dices[round], 0, prevR, prevS);
            // 해당 말이, 해당 칸만큼 이동한 곳에 다른 말이 없는지 확인
            if (m.canGo) {
                //System.out.println("말 " + i + "이동 : " + m.s + " 점수 획득");
                pieces[i][0] = m.s;
                pieces[i][1] = m.r;
                ck[prevS][prevR] = 0;
                ck[m.s][m.r] = i;
                if (m.s == INF) {
                    backtracking(round+1, sum);
                }
                else backtracking(round+1, sum+m.s);
                pieces[i][0] = prevS;
                pieces[i][1] = prevR;
                ck[m.s][m.r] = 0;
                ck[prevS][prevR] = i;
            }
        }
    }

    static class Move {
        boolean canGo;
        int r;
        int s;
        public Move(boolean canGo, int r, int s) {
            this.canGo = canGo;
            this.r = r;
            this.s = s;
        }
    }

    // 주사위 칸만큼 이동 (라운드 한번의 이동에 해당)
    private static Move dfs(int d, int depth, int r, int s) {

        // 이동 칸만큼 이동하기 전에, 도착지 도착하면 이동 가능
        if (s == INF) {
            return new Move(true, 0, INF);
        }

        // 주사위 칸만큼 이동 끝냈으면 재귀 종료
        if (depth == d) {
            if (ck[s][r] == 0) {
                // 목적지에 다른 말 없으므로, 이동 가능
                return new Move(true, r, s);
            }
            else return new Move(false, 0, 0);
        }

        int nxtRouteNum = r;
        // 그냥 지나가는 곳이 파란 노드일 경우, 경로 안바꿔도 됨
        // 현재 노드가 파란 노드임 -> 경로 1로 바꿔야 함
        if (depth == 0) {
            if (s == 10 || s == 20 || (s == 30 && r == 0)) {
                nxtRouteNum = 1;
            }
        }
        if (s == 19 || (s == 24 && r == 1) || (s == 26 && r == 1) || s == 38) {
            nxtRouteNum = 2;
        }

        for (Node next : map[s]) {
            if (next.routeNum == nxtRouteNum) {
                return dfs(d, depth+1, nxtRouteNum, next.num);
            }
        }

        // 도달 X
        return new Move(false, -1, -1);
    }
}
