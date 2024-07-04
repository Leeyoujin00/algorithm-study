import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int[] answer;
        
        List<Integer> list;
        Stack<Integer> stack = new Stack<>();
        for(int i : arr) {
            if(!stack.empty()) {
                if (stack.peek() == i) {
                    //stack.
                    continue;
                }
            }
            stack.push(i);
        }
        
        list = new ArrayList<>(stack);
        answer = list.stream().mapToInt(i->i).toArray();
        
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("Hello Java");

        return answer;
    }
}