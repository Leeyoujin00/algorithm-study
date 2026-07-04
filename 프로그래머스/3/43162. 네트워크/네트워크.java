class Solution {

    int[] parent;

    public int find(int x) {

        if (x == parent[x]) return x;

        return parent[x] = find(parent[x]);
    }

    public boolean union(int x, int y) {

        x = find(x);
        y = find(y);

        if (x == y) return false;
        else if (x < y) parent[y] = x;
        else parent[x] = y;

        return true;
    }

    public int solution(int n, int[][] computers) {

        int answer = 0;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && computers[i][j] == 1) {
                    union(i,j);
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (i == parent[i]) answer++;
        }

        return answer;
    }
}
