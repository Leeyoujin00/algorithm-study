import java.util.*;

class Solution {
    static int[] distances;
    static int max = 0; // 각 지점 사이의 거리의 최솟값 중 가장 큰 값
    public int solution(int distance, int[] rocks, int n) {

        // rocks는 각 바위의 위치, n은 제거할 바위의 개수
        // 구하려는 것은, 바위 제거한 뒤, 남은 바위들 지점 사이의 거리 최솟값
        
        Arrays.sort(rocks);
        // 각 바위 사이의 거리 배열
        distances = new int[rocks.length+1];
        distances[0] = rocks[0];
        for (int i = 1; i < rocks.length; i++) {
            distances[i] = rocks[i] - rocks[i-1];
        }
        distances[rocks.length] = distance - rocks[rocks.length-1];
        
        int left = 0, right = distance; // 지점 간 거리의 최솟값, 최댓값
        
        int mid = 0;

        while (left <= right) {
            
            mid = (left + right) / 2;
            
            if (getRemovedRocks(mid, n) > n) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        return max;
    }
    
    // 제거한 바위의 개수를 반환한다.
    private static int getRemovedRocks(int k, int n) { 
        
        // 바위 사이의 거리는 k 이상이어야 한다.
        int cnt = 0; // 없앤 바위 개수
        int min_interval = 1000000000; // 거리의 최솟값
        
        int d = 0;
        for (int i = 0; i < distances.length; i++) {
            if (d + distances[i] < k) {
                // i 바위 없앰으로써, 간격을 늘려야 함.
                cnt++;
                d += distances[i];
            }
            else {
                min_interval = Math.min(min_interval, d + distances[i]);
                d = 0;
            }
        }
        
//         if (d + distances[distances.length-1] < k) {
//             return cnt;
//         }
        
        if (cnt <= n) {
            max = Math.max(max, k);
        }
        return cnt;
    }
}