import java.util.*;
import java.io.*;
 
class Main {
    static int stoi(String s) { return Integer.parseInt(s);}
 
    static int[][] arr;
    static boolean[] visited;
    static int k;
    static int n;
    static int f;
    static boolean done = false;
    static StringBuilder sb;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
 
        st = new StringTokenizer(br.readLine());
        k = stoi(st.nextToken());
        n = stoi(st.nextToken());
        f = stoi(st.nextToken());
        arr = new int[n+1][n+1];
        visited = new boolean[n+1];
        sb = new StringBuilder();
        int[] indegree = new int[n+1];
 
        // 인접행렬 형성
        for(int i=0; i<f; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = stoi(st.nextToken());
            int v2 = stoi(st.nextToken());
 
            arr[v1][v2] = arr[v2][v1] = 1;
            indegree[v1]++;
            indegree[v2]++;
        }
 
        // 백트래킹으로 확인
        for(int i=1; i<=n; i++) {
            // 본인 포함 k명이 되지 않으면 애초에 볼 필요가 없음
            if(indegree[i] < k-1) continue;
            if(done) break;
 
            visited[i] = true;
            dfs(i, 1);
            visited[i] = false;
        }
 
        // 아무도 소풍에 가지 못할 경우 -1
        if(!done)
            System.out.println(-1);
        else
            System.out.println(sb);
    }
 
 
    static void dfs(int now, int depth) {
        if(done) return;
 
        if(depth == k) {
            print();
            done = true;
            return;
        }
 
        for(int i=now+1; i<=n; i++) {
            if(arr[now][i] == 1 && isFriend(i)) {
                visited[i] = true;
                dfs(i, depth + 1);
                visited[i] = false;
            }
        }
    }
 
    // 지금까지 방문했던 노드와 현재 target 노드가 서로 친구인지 확인
    // DFS가 방문하는 점들은 항상 모두 친구임을 보장
    static boolean isFriend(int target) {
        for(int i=1; i<=n; i++) {
            if(visited[i] && arr[target][i] != 1) 
                return false;
        }
        return true;
    }
 
    // 답 출력
    static void print() {
        for(int i=1; i<=n; i++) {
            if(visited[i])
                sb.append(i + "\n");
        }
    }
}