import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());

        int[] org = new int[n];
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.valueOf(st.nextToken());
            org[i] = arr[i];
        }

        // 원래 원소의 크기 순이 유지되어야 함
        // 원소 값이 같으면 같은 값으로, 다르면 다른 값으로 변환되어야 함
        // 크기 순을 유지하기 위해, 오름차순 정렬한다.
        Arrays.sort(arr);
        HashMap<Integer, Integer> hash = new HashMap<>();
        int num = 0;
        for (int i = 0; i < n; i++) {
            if (!hash.containsKey(arr[i])) {
                hash.put(arr[i], num++);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int o : org) {
            sb.append(hash.get(o) + " ");
        }
        System.out.print(sb);
    }
}
