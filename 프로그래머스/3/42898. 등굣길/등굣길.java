class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        
        int[][] dp = new int[n][m];
        dp[0][0] = 1;
        
        int INF = -1;
        for (int[] pos : puddles) {
            dp[pos[1]-1][pos[0]-1] = INF;
        }
        
        // 현재 좌표의 왼쪽, 위쪽의 경로 수 = 현재 좌표의 경로 수
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dp[i][j] == INF) continue;
                if (i-1 >= 0 && dp[i-1][j] != INF) dp[i][j] += dp[i-1][j] % 1000000007;
                if (j-1 >= 0 && dp[i][j-1] != INF) dp[i][j] += dp[i][j-1] % 1000000007;
                dp[i][j] %= 1000000007;
            }
        }
        
        answer = dp[n-1][m-1];
        
        return answer % 1000000007;
    }
}