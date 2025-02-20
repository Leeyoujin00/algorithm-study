import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n,w,l;
    static int[] a;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        System.out.print(simulation());

    }

    private static int simulation() {

        int time = 0, cnt = 0, idx = 0;
        Queue<truck> queue = new LinkedList<>();
        //queue.offer(new truck(0,1));

        int space = 0; // 다리에서 트럭이 차지하는 공간
        int weight = 0; // 현재 다리 위 트럭 무게 합
        // 모든 트럭이 다리를 건널 때까지 진행
        while (cnt < n) {
//            int space = 0; // 다리에서 트럭이 차지하는 공간
//            int weight = 0; // 현재 다리 위 트럭 무게 합
            Queue<truck> tmpQue = new LinkedList<>();
            time++;
            // 현재 다리에 있는 트럭들을 하나씩 이동시킨다.
            while (!queue.isEmpty()) {
                truck cur = queue.poll();
                // 마지막 칸에 있는 트럭은 제거
                if (cur.pos == w) {
                    space--;
                    weight -= a[cur.idx];
                    cnt++;
                }
                // 트럭 위치 한 칸씩 이동
                else {
                    tmpQue.offer(new truck(cur.idx, cur.pos+1));
                }
            }

            // 다리공간이 남아있고, 하중을 넘지 않는다면 다리에 트럭을 하나 추가한다.
            if (space < w && idx < n && weight+a[idx] <= l) {
                tmpQue.offer(new truck(idx, 1));
                weight += a[idx];
                idx++;
                space++;
            }

//            System.out.println();
//            System.out.print(time + ":  ");
            for (truck t : tmpQue) {
                queue.offer(t);
                //System.out.print(t.idx + "번 트럭" + t.pos + "위치 ");
            }
            tmpQue.clear();
        }

        return time;
    }

    private static class truck {
        int idx; // 트럭 번호
        int pos; // 트럭의 현재 다리 위치 (처음 진입시 1, 마지막 위치는 w)

        public truck(int idx, int pos) {
            this.idx = idx;
            this.pos = pos;
        }
    }
}
