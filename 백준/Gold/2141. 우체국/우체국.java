import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class House implements Comparable<House>{
        long pos,val;

        public House(long pos, long val) {
            this.pos = pos;
            this.val = val;
        }

        // 집 위치 기준으로 오름차순 정렬
        @Override
        public int compareTo(House o) {
            return (int) (this.pos - o.pos);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        List<House> houseList = new ArrayList<>();
        // 총 마을 사람 수
        long sum = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            long pos = Long.parseLong(st.nextToken());
            long val = Long.parseLong(st.nextToken());
            houseList.add(new House(pos, val));
            sum += val;
        }

        // 집 위치 기준으로 오름차순 정렬
        Collections.sort(houseList);

        long mid = (sum+1) / 2;
        long ck = 0;
        for (int i = 0; i < n; i++) {
            ck += houseList.get(i).val;
            if (mid <= ck) {
                System.out.print(houseList.get(i).pos);
                break;
            }
        }
    }
}
