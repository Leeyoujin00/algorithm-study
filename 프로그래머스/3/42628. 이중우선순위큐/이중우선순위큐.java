import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        
        PriorityQueue<Integer> pq1 = new PriorityQueue<>(); // 오름차순
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(Collections.reverseOrder()); // 내림차순
        
        for (String s : operations) {
            String c = s.split(" ")[0];
            int n = Integer.parseInt(s.split(" ")[1]);
            
            if (c.equals("I")) {
                pq1.offer(n);
            } else if (c.equals("D")) {
                if (n == 1) {
                    deleteMax(pq1, pq2);
                } else if (n == -1) {
                    deleteMin(pq1, pq2);
                }
            }
        }
        
        int min = 0, max = 0;
        
        while (!pq2.isEmpty()) {
            pq1.offer(pq2.poll());
        }
        
        if (!pq1.isEmpty()) {
            min = pq1.peek();
            while (!pq1.isEmpty()) {
                pq2.offer(pq1.poll());
            }
            max = pq2.poll();
        }
        else if (!pq2.isEmpty()) {
            max = pq2.peek();
            while (!pq2.isEmpty()) {
                pq1.offer(pq2.poll());
            }
            min = pq1.poll();
        }
        
        answer[0] = max;
        answer[1] = min;
        
        return answer;
    }
    
    public void deleteMin(PriorityQueue<Integer> pq1, PriorityQueue<Integer> pq2) {
        
        while(!pq2.isEmpty()) {
            pq1.offer(pq2.poll());
        }
        
        // 최솟값 삭제
        if (!pq1.isEmpty()) pq1.poll();
    }
    
    public void deleteMax(PriorityQueue<Integer> pq1, PriorityQueue<Integer> pq2) {
        while(!pq1.isEmpty()) {
            pq2.offer(pq1.poll());
        }
        
        // 최댓값 삭제
        if (!pq2.isEmpty()) pq2.poll();
    }
}