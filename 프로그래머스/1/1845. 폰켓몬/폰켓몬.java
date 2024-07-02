import java.util.*;
class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        int n = nums.length;
        
        List<int[]> list = new ArrayList();
        
        Arrays.sort(nums);
        list.add(new int[] {nums[0], 1});
        int now = nums[0];
        
        for(int i = 1; i < n; i++) {
            if (nums[i] != now) {
                list.add(new int[] {nums[i], 1});
                now = nums[i];
            }
            else list.get(list.size()-1)[1] = list.get(list.size()-1)[1]+1;
        }
        
        if (list.size() >= n/2) return n/2;
        return list.size();
    }

        
}