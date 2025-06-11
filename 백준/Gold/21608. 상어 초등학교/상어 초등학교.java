import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static Queue<Integer> sequence = new LinkedList<>();
    static int[][] like;
    static int[][] map;
    // 인접한 방향 - 상하좌우
    static int[] dr = {0,0,-1,1};
    static int[] dc = {1,-1,0,0};

    public static void main(String[] args) throws IOException {

        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        like = new int[n*n+1][4];
        StringTokenizer st;
        for (int i = 0; i < n*n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            sequence.offer(s);
            for (int j = 0; j < 4; j++) {
                like[s][j] = Integer.parseInt(st.nextToken());
            }
        }

        map = new int[n][n];
        System.out.print(deploy());
    }


    // 자리 정보저장하는 클래스
    static class Node {
        int likeCnt;
        int emptyCnt;
        int row;
        int col;
        public Node(int likeCnt, int emptyCnt, int row, int col) {
            this.likeCnt = likeCnt;
            this.emptyCnt = emptyCnt;
            this.row = row;
            this.col = col;
        }
    }

    // 학생들의 자리 배치를 진행한다.
    private static int deploy() {

        // 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸이 가장 많은 칸으로 자리 정함

        // 2. 1 만족하는 칸 여러 개일 경우, 인접한 칸 중 빈 칸이 가장 많은 칸으로

        // 3. 2 를 만족하는 칸 여러 개일 경우, 행 번호, 열 번호 작은 칸으로 정함

        while (!sequence.isEmpty()) {
            int cur = sequence.poll();
            PriorityQueue<Node> nodeQ = new PriorityQueue<>((o1, o2) -> o1.likeCnt != o2.likeCnt ? Integer.compare(o2.likeCnt, o1.likeCnt) : (o1.emptyCnt != o2.emptyCnt ? Integer.compare(o2.emptyCnt, o1.emptyCnt) : (o1.row != o2.row ? Integer.compare(o1.row, o2.row) : Integer.compare(o1.col, o2.col))));

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // 이미 다른 학생이 차지한 칸이면 진행 X
                    if (map[i][j] != 0) {
                        continue;
                    }
                    int likeCnt = 0, emptyCnt = 0;
                    // 인접한 칸에 대해 좋아하는 학생수 있는 칸, 빈 칸 개수 구함
                    for (int k = 0; k < 4; k++) {
                        int nr = i + dr[k];
                        int nc = j + dc[k];
                        // 탐색범위 벗어나면 진행 X
                        if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
                            continue;
                        }
                        // 빈칸인지 확인
                        if (map[nr][nc] == 0) {
                            emptyCnt++;
                        }
                        // 좋아하는 학생 있는 칸인지 확인
                        for (int s : like[cur]) {
                            if (map[nr][nc] == s) {
                                likeCnt++;
                            }
                        }
                    }
                    nodeQ.offer(new Node(likeCnt, emptyCnt, i, j));
                }
            }

            // 우선순위 가장 높은 칸이 해당 학생이 앉는 칸이 된다.
            Node selectedNode = nodeQ.poll();
            map[selectedNode.row][selectedNode.col] = cur;
        }

        // 자리 배치 출력해봄
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("============");

        return getSum();
    }

    // 자리 배치가 끝나고, 학생들의 만족도 총합을 구한다.
    private static int getSum() {
        int sum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 빈칸이면 진행 X
                if (map[i][j] == 0) continue;
                int cur = map[i][j];
                int score = 0;
                for (int k = 0; k < 4; k++) {
                    int nr = i + dr[k];
                    int nc = j + dc[k];
                    // 탐색범위 벗어나면 진행 X
                    if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
                        continue;
                    }
                    for (int s : like[cur]) {
                        if (map[nr][nc] == s) score++;
                    }
                }
                switch (score) {
                    case 0:
                        break;
                    case 1:
                        sum += 1;
                        break;
                    case 2:
                        sum += 10;
                        break;
                    case 3:
                        sum += 100;
                        break;
                    case 4:
                        sum += 1000;
                        break;
                }
            }
        }

        return sum;
    }
}
