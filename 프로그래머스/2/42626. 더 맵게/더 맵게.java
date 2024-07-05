import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int s : scoville) {
            pq.offer(s);
        }
        
        int answer = 0;
        while(!pq.isEmpty() && pq.peek() < K) {
            int first = pq.poll();
            if (!pq.isEmpty()) {
                int second = pq.poll();
                int newS = first + 2*(second);
                
                pq.offer(newS);
                answer += 1;
            }
            else {
                return -1;
            }
        }
    
        
        return answer;
    }
}