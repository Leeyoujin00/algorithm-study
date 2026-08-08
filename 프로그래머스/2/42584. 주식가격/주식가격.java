import java.util.Stack;

public class Solution {
    
    public int[] solution(int[] prices) {
        
        int n = prices.length;
        int[] answer = new int[n];

        Stack<Info> stack = new Stack<>();
        stack.push(new Info(0, prices[0]));
        
        int idx = 1;
        while(idx < prices.length) {
            
            int price = prices[idx];
            
            // 가격이 이전 시점보다 떨어지지 않으면 스택에 넣는다.
            if (!stack.isEmpty() && price < stack.peek().price) {
                while (!stack.isEmpty() && price < stack.peek().price) {
                    Info info = stack.pop();
                    answer[info.time] = idx - info.time;
                }
            }

            stack.push(new Info(idx, price));
            
            idx++;
        }
        
        while (!stack.isEmpty()) {
            Info info = stack.pop();
            answer[info.time] = n-1 - info.time;
        }
        
        return answer;
    }
    
    static class Info {
        int time; 
        int price;
        
        public Info(int time, int price) {
            this.time = time;
            this.price = price;
        }
    }
}
