class Solution {
    int count = 0;

    public int solution(int[] numbers, int target) {
        
        dfs(target, 0, 0, numbers);
        
        return count;
    }
    
    private void dfs(int target, int sum, int depth, int[] numbers) {
        
        if (depth == numbers.length) {
            if (target == sum) count++;
            return;
        }
        
        int minus = sum - numbers[depth];
        int plus = sum + numbers[depth];
        
        dfs(target, minus, depth+1, numbers);
        dfs(target, plus, depth+1, numbers);
    }
}