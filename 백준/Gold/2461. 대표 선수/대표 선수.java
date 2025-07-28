import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m;
    static int[][] score;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        score = new int[n][m];

        // 각각의 반에서 대표로 선발된 모든 학생들의 능력치 중 최댓값과 최솟값의 차이가 최소
        /** [16, 17, 15] -> 2로, 최소가 됨
         * 12 16 43 67
         * 7 17 48 68
         * 14 15 54 77
         *
         * n,m = 1000
         */

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                score[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 각 반의 점수를 정렬
        for (int i = 0; i < n; i++) {
            Arrays.sort(score[i]);
        }

        System.out.print(solution());

    }

    // 각 반에서 선택된 점수 정보
    private static class Node {
        int c; // 반번호
        int idx; // 점수 인덱스
        int score;
        public Node (int c, int idx, int score) {
            this.c = c;
            this.idx = idx;
            this.score = score;
        }
    }

    // 최소점수인 반의 포인터 증가시킴
    private static int solution() {

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.score, o2.score));
        PriorityQueue<Integer> maxScoreQ = new PriorityQueue<>(Collections.reverseOrder());
        int minDiff = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            pq.offer(new Node(i, 0, score[i][0]));
            maxScoreQ.offer(score[i][0]);
        }

        int minVal = 0, maxVal = 0;
        while (true) {
            Node minNode = pq.poll();

            minVal = minNode.score;
            maxVal = maxScoreQ.peek();
            minDiff = Math.min(minDiff, maxVal-minVal);

            if (minNode.idx == m-1) {
                break;
            }
            pq.offer(new Node(minNode.c, minNode.idx+1, score[minNode.c][minNode.idx+1]));
            maxScoreQ.offer(score[minNode.c][minNode.idx+1]);
        }

        return minDiff;
    }
}
