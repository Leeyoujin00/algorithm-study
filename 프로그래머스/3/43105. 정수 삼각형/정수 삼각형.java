class Solution {

    public int solution(int[][] triangle) {
        
        int n = triangle.length;
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                if (j == 0) triangle[i][j] += triangle[i-1][0]; // 맨 왼쪽
                else if (j == triangle[i].length - 1) triangle[i][j] += triangle[i-1][triangle[i].length - 2]; // 맨 오른쪽
                else triangle[i][j] += Math.max(triangle[i-1][j-1], triangle[i-1][j]);
            }
        }
        
        // 피라미드의 맨 밑 행에서 최댓값 == 정답
        int max = 0;
        for (int i = 0; i < triangle[n-1].length; i++) {
            max = Math.max(max, triangle[n-1][i]);
        }

        return max;
    }
}
