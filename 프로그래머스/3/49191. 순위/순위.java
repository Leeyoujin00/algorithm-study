class Solution {
    static boolean[][] ck;
    static int[][] map;
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        // A가 B를 이김 , B가 C를 이김 -> A가 C를 이김
        // A가 B에게 짐, B가 C에게 짐 -> A가 C에게 짐
        
        map = new int[n+1][n+1];
        for (int[] r : results) {
            int a = r[0];
            int b = r[1];
            map[a][b] = 1;
            map[b][a] = -1;
        }
        
        // 플로이드 와샬 알고리즘 수행
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (map[i][j] != 0) continue;
                    if (map[i][k] == 1 && map[k][j] == 1) {
                        map[i][j] = 1;
                    }
                    else if (map[i][k] == -1 && map[k][j] == -1) {
                        map[i][j] = -1;
                    }
                }
            }
        }
        
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            cnt = 1;
            for (int j = 1; j <= n; j++) {
                if (map[i][j] != 0) {
                    cnt++;
                }
            }
            if (cnt == n) answer++;
        }
        
        return answer;
    }
}