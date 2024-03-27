import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static int N;
    public static int result;
    static ArrayList<int[]> arr;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new ArrayList<>();
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            arr.add(new int[]{Integer.parseInt(line[0]), Integer.parseInt(line[1])});
        }
        // 강의 시작 시간 기준으로 수업 정렬
        Collections.sort(arr, (l1, l2) -> l1[0] - l2[0]);

        //끝나는 시간(T)를 키로 하는 최소힙 생성
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        result = 1;

        for(int[] lesson : arr) {
            if(!heap.isEmpty() && heap.peek() <= lesson[0]) {
                heap.poll();
            }
            heap.add(lesson[1]);
        }

        System.out.println(heap.size());

    }
}
