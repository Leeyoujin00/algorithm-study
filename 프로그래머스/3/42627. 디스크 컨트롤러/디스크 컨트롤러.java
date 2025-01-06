import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        
        // 소요시간,요청시각,작업번호 순으로 우선순위 지정
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> o1[0] != o2[0] ? Integer.compare(o1[0], o2[0]) : (o1[1] != o2[1] ? Integer.compare(o1[1], o2[1]) : Integer.compare(o1[2], o2[2])));
        
        // 작업 번호, 요청시각, 소요시간 
        // 우선순위 : 소요시간, 요청시각, 작업번호
        //PriorityQueue<int[]> pq1 = 
        
        // jobs 배열 요청시각 순으로 정렬
        // 작업번호까지 저장
        int[][] jobList = new int[jobs.length][3];
        for (int i = 0; i < jobs.length; i++) {
            jobList[i][0] = jobs[i][1];
            jobList[i][1] = jobs[i][0];
            jobList[i][2] = i;
        }
        
        Arrays.sort(jobList, (o1,o2) -> {return o1[1]-o2[1];});
        
        // int first = jobList[0][1];
        // int idx1 = 0;
        // while (idx1 < jobList.length && first == jobList[idx1][1]) {
        //     pq.offer(jobList[idx1++]);
        // }
        
        int t = 0;
        int idx = 0;
        int sum = 0;
        boolean flag = false;
        System.out.println(jobList.length);
        while (idx < jobList.length) {
            flag = false;
            // 현재시간이 도착시각보다 이후라면
            while (idx < jobList.length && t >= jobList[idx][1]) {
                flag = true;
                System.out.println(t + " " + idx);
                // 현재시간보다 빨리 도착한 작업 큐에 넣음
                pq.offer(jobList[idx++]);
            }
            
            // 현재시간에 처리할 수 있는 작업이 아직 도착x
            if (!flag) {
                t++;
            }
            
            // 큐에 있는 작업 진행
            while (!pq.isEmpty()) {
                int[] j = pq.poll();
                
                // 해당 작업의 반환 시간(작업종료시각 - 요청시각) 더함
                sum += (t + j[0]) - j[1];
                t += j[0];
                 while (idx < jobList.length && t >= jobList[idx][1]) {
                    //flag = true;
                    System.out.println(t + " " + idx);
                    // 현재시간보다 빨리 도착한 작업 큐에 넣음
                    pq.offer(jobList[idx++]);
                }
                //flag = true;
            }
            
            //if (!flag) 
        }
        
        answer = sum/jobs.length;
        
        
        return answer;
    }
}