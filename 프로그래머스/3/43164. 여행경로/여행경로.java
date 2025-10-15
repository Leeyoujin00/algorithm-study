import java.util.*;

class Solution {

    static boolean[] visited;
    static List<String> result = new ArrayList<>();
    
    public String[] solution(String[][] tickets) {
        
        visited = new boolean[tickets.length];
        
        dfs(0, "ICN", "ICN", tickets);
        
        Collections.sort(result);
        String[] answer = result.get(0).split(" ");
        
        return answer;
    }
    
    private static void dfs(int cnt, String start, String route, String[][] tickets) {
        
        // 모든 티켓 사용함
        if (cnt == tickets.length) {
            result.add(route.toString());
            return;
        }
        
        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && start.equals(tickets[i][0])) {
                visited[i] = true;
                dfs(cnt+1, tickets[i][1], route + " " + tickets[i][1], tickets);
                visited[i] = false;
            }
        }
        return;
    }
}