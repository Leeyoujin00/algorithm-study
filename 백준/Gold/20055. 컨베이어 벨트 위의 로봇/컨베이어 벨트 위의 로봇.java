import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n,k;
    static int[] a;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        a = new int[2*n+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= 2*n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            // 내구도가 0인 칸의 개수
            if (a[i] == 0) cnt++;
        }

        int ans = simulation();
        System.out.print(ans);
    }

    private static int simulation() {

        int step = 0, zeroNum = 0;

        Queue<Square> queue = new LinkedList<>();
        // 초기화. 1-N 번 칸이 각자 1-N번 위치에 있다.
        for (int i = n; i >= 1; i--) {
            queue.offer(new Square(i, i, false));
        }

        Queue<Square> nextQue = new LinkedList<>();
        int rmIdx = 0, nextIdx = 0;;
        while (zeroNum < k) {
            step++;
            // 한 칸 회전
            while (!queue.isEmpty()) {
                Square cur = queue.poll();

                // 마지막 칸이라면 벨트에서 제외
                if (cur.pos == n) {
                    rmIdx = cur.idx;
                    continue;
                }

                nextQue.offer(new Square(cur.pos+1, cur.idx, cur.isRobot));
            }

            // 가장 먼저 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동
            while (!nextQue.isEmpty()) {
                Square cur = nextQue.poll();

                if (cur.pos == 2) { // prev 없는 위치
                    queue.offer(cur);
                    continue;
                }
                Square prev = nextQue.peek();

                // 내리는 칸일 경우 로봇 내림
                if (cur.pos == n) {
                    cur.isRobot = false;
                }

                if (prev.isRobot) {
                    // 현재 위치에 로봇 없고 내구도 1이상이면 로봇 옮긴다.
                    if (!cur.isRobot && a[cur.idx] > 0) {
                        cur.isRobot = true;
                        a[cur.idx]--;
                        if (a[cur.idx] == 0) zeroNum++;
                        prev.isRobot = false;
                    }

                }

                queue.offer(cur);
            }

            // 올리는 위치에 칸 추가
            nextIdx = getNextIdx(rmIdx);
            if (a[nextIdx] == 0) {
                queue.offer(new Square(1, nextIdx, false));
            }
            else {
                queue.offer(new Square(1, nextIdx, true));
                if (--a[nextIdx] == 0) zeroNum++;
            }
        }

        return step;
    }

    private static class Square {
        int pos; // 칸 위치
        int idx; // 칸 번호
        boolean isRobot; // 해당 칸에 로봇 존재 여부

        public Square(int pos, int idx, boolean isRobot) {
            this.pos = pos;
            this.idx = idx;
            this.isRobot = isRobot;
        }
    }

    private static int getNextIdx(int idx) {
        if (idx <= n) return idx+n;
        else return idx-n;
    }
}
