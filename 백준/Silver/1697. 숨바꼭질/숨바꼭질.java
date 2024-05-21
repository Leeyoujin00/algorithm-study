import java.io.*;
import java.util.*;

public class Main {
    public static Deque<int []> deq;
    public static List<Integer> stepList;
    public static int N;
    public static int K;
    public static int timeStep = 200000;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] readLine = br.readLine().split(" ");

        N = Integer.parseInt(readLine[0]);
        K = Integer.parseInt(readLine[1]);

        stepList = new ArrayList<>();
        for(int i = 0; i < 100001; i++) {
            stepList.add(100001);
        }

        bfs();

        System.out.println(timeStep);
    }

    // bfs 적용, 선택지는 현재 위치 +-1 또는 *2
    public static void bfs() {
        deq = new LinkedList<>();

        int step = 0;
        // 현재 점의 위치
        int now = -1;

        deq.add(new int[] {N, step});

        while (!deq.isEmpty()) {

            now = deq.peek()[0];
            step = deq.pop()[1];

            if (now == K) {
                if(step < timeStep) timeStep = step;
                continue;
            }

            // 현재 위치에서 세 가지 선택지에 의한 이동 진행
            if (now * 2 <= 100000 && (step + 1) < stepList.get(now * 2)) {
                deq.add(new int[] {now * 2, step+1});
                stepList.set(now * 2, step + 1);
            }
            if (now + 1 <= 100000 && (step + 1) < stepList.get(now + 1)) {
                deq.add(new int[] {now + 1, step+1});
                stepList.set(now + 1, step + 1);
            }
            if (now - 1 >= 0 && (step + 1) < stepList.get(now - 1)) {
                deq.add(new int[] {now - 1, step+1});
                stepList.set(now - 1, step + 1);
            }
        }
    }

}
