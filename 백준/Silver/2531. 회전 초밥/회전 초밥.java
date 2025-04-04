import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n,d,k,c;
    static int[] arr;
    static int[] type;
    //static int[] info;
    static List<int[]> results = new ArrayList<>();
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 접시 수, 초밥 가짓수, 연속해서 먹는 접시 수, 쿠폰 번호
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        type = new int[d+1];
        arr = new int[n];
        // c를 제외하고 k가 되는 경우의 수를 구함
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int count = 0;
        // 처음 k개의 정보 저장
        for (int i = 0; i < k; i++) {
            if (type[arr[i]] == 0) {
                count++;
            }
            type[arr[i]]++;
        }
        if (type[c] == 0) max = Math.max(max, count+1);
        else max = Math.max(max, count);

        int start = arr[0];
        int end = (start+k-1) % n;
        // 투 포인터 활용,
        for (int i = 1; i < n; i++) {
            if(--type[start] == 0) {
                count--;
            }
            start = arr[i];
            end = arr[(i+k-1) % n];
            // 만약 end가 선택한 적 없는 요리라면 count 증가.
            if (type[end] == 0) {
                count++;
            }
            type[end]++;

            if (type[c] == 0) max = Math.max(max, count+1);
            else max = Math.max(max, count);

//            System.out.println(i + " " + count);
//            System.out.println(i + " " + max);
//            System.out.println("===");
        }

        System.out.print(max);
    }
}
