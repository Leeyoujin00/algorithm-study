import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Solution {
    public int solution(int N, int number) {

        // N 을 1-8번 사용해서 만들 수 있는 결과 수들을 각각 저장
        List<HashSet<Integer>> list = new ArrayList<HashSet<Integer>>();
        
        for (int i = 0; i <= 8; i++) {
            list.add(new HashSet<>());
        }

        // N을 한 개 사용해 만들 수 있는 값은 N 하나.
        list.get(1).add(N);
        
        if (N == number) return 1;

        for (int i = 2; i <= 8; i++) {
            // i번 통의 연산 수행
            HashSet<Integer> set = list.get(i);
            for (int j = 1; j < i; j++) {

                HashSet<Integer> a = list.get(j);
                HashSet<Integer> b = list.get(i-j);

                // a와 b의 사칙연산한 결과 set에 추가. (+,-,*,/)
                for (int n1 : a) {
                    for (int n2: b) {
                        set.add(n1+n2);
                        set.add(n1-n2);
                        set.add(n1*n2);
                        if (n1 != 0 && n2 != 0) set.add(n1/n2);
                    }
                }

                // N을 i번 이어붙인 값도 추가
                set.add(Integer.parseInt(String.valueOf(N).repeat(i)));

                if (set.contains(number)) return i;
            }
        }

        return -1;
    }
}
