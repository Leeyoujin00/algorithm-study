class Solution {
    public int solution(int[] money) {
        
        int n = money.length;
        // dpx[i] : i번째 집까지 왔을 때 얻을 수 있는 최댓값
        // dp1 = 첫번째 집 선택, dp2 = 두번째 집 선택
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        
        dp1[0] = money[0];
        dp1[1] = money[0];
        dp2[0] = 0;
        dp2[1] = money[1];
        
        for (int i = 2; i < n; i++) {
            dp1[i] = Math.max(dp1[i-2]+money[i], dp1[i-1]);
            dp2[i] = Math.max(dp2[i-2]+money[i], dp2[i-1]);
        }
        
        int max1 = Math.max(dp1[n-2], dp1[n-3]);
        int max2 = Math.max(dp2[n-2], dp2[n-1]);
        
        return Math.max(max1, max2);
    }
}