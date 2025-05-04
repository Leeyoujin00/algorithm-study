import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        int n = people.length;
        
        boolean[] ck = new boolean[n];
        // 하나의 보트에 최대 2명이 탑승 가능
        // 남은 무게 이하 중, 가장 큰 무게 선택

        Arrays.sort(people);
        
        // 남은 사람들 중, 무게 가장 적은 사람(left)과 많은 사람(right) 짝 지은 후
        // 무게 합이 limit 넘을 경우 right만 탑승시킴
        int left = 0;
        int right = n-1;
        
        while (left <= right) {
            if (left == right) {
                answer++;
                break;
            }
            if (people[left] + people[right] <= limit) {
                answer++;
                left++;
                right--;
            }
            else {
                answer++;
                right--;
            }
        }
        
        return answer;
    }
}