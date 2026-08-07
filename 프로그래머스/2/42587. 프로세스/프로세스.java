import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

    public int solution(int[] priorities, int location) {

        int answer = 1;

        /**
         * 우선순위가 가장 높은 프로세스가 먼저 실행된다.
         */

        Queue<Process> que = new LinkedList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int idx = 0; idx < priorities.length; idx++) {
            que.offer(new Process(idx, priorities[idx]));
            pq.offer(priorities[idx]);
        }

        while (!que.isEmpty()) {
            
            Process p = que.poll();
            if (p.priority != pq.peek()) {
                que.offer(p);
            } else {
                if (p.number == location) return answer;
                pq.poll();
                answer++;
            }
        }


        return answer;
    }

    static class Process {
        int number; // 프로세스 번호
        int priority;
        
        public Process(int number, int priority) {
            this.number = number;
            this.priority = priority;
        }
    }
}
