import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        // 가능한 모든 총 대기시간에 대해, 이분 탐색을 진행한다.
        // 최소 시간 <- times[0]
        // 최대 시간 <- times[times.length-1] * n (모든 인원이 제일 오래걸리는 심사대에서 심사 받음)
        
        Arrays.sort(times);
        long left = (long)times[0];
        long right = (long)times[times.length-1] * n;
        long mid;
        
        long min = right;
        while (left <= right) {
            
            mid = (left+right)/2; // 총 대기시간을 mid라고 상정
            
            // 대기 시간 mid 동안 심사받을 수 있는 인원 계산
            long cnt = 0;
            for (int t : times) {
                cnt += mid/t;
            }
            
            if (cnt >= n) { // 대기시간 더 줄임
                right = mid-1;
                min = Math.min(min, mid);
            }
            else { // 대기시간 늘림
                left = mid+1;
            }
        }
        
        return min;
    }
}