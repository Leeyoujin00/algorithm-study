import java.util.*;
import java.io.*;

public class Main {
    public static Deque<int[]> deq;
    public static int N;
    public static int K;
    public static List<Integer> steps;
    public static int minStep = 100001;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] readLine = br.readLine().split(" ");

        N = Integer.parseInt(readLine[0]);
        K = Integer.parseInt(readLine[1]);

        steps = new ArrayList<>();
        for (int i = 0; i < 100001; i++) {
            steps.add(100001);
        }

        bfs();

        System.out.println(minStep);

    }

    public static void bfs() {
        deq = new LinkedList<>();

        deq.add(new int[] {N, 0});

        int now = -1;
        int step = -1;

        while (!deq.isEmpty()) {
            now = deq.peek()[0];
            step = deq.pop()[1];

            if (now == K) {
                if (step < minStep) {
                    minStep = step;
                }
                continue;
            }

            if (now + 1 <= 100000 && (step + 1) < steps.get(now + 1)) {
                deq.add(new int[] {now + 1, step + 1});
                steps.set(now + 1, step + 1);
            }
            if (now - 1 >= 0 && (step + 1) < steps.get(now - 1)) {
                deq.add(new int[] {now - 1, step + 1});
                steps.set(now - 1, step + 1);
            }
            if (now * 2 <= 100000 && step < steps.get(now * 2)) {
                deq.add(new int[] {now * 2, step});
                steps.set(now * 2, step);
            }
        }
    }

}
