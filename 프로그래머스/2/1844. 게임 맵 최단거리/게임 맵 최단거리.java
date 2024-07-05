import java.util.LinkedList;
import java.util.Queue;

class Solution {
    static int n;
    static int m;
    static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;
        
        return bfs(maps);
    }
    
    private static int bfs(int[][] maps) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        
        queue.offer(new int[] {0, 0, 1}); // {x, y, distance}
        visited[0][0] = true;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int distance = current[2];
            
            if (x == n - 1 && y == m - 1) {
                return distance;
            }
            
            for (int[] direction : directions) {
                int newX = x + direction[0];
                int newY = y + direction[1];
                
                if (newX >= 0 && newY >= 0 && newX < n && newY < m && maps[newX][newY] == 1 && !visited[newX][newY]) {
                    queue.offer(new int[] {newX, newY, distance + 1});
                    visited[newX][newY] = true;
                }
            }
        }
        
        return -1; // No path found
    }
}
