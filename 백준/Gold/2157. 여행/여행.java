import java.io.*;
import java.util.*;

public class Main {
    static int[][] dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        dp = new int[M+1][N+1];
        List<City>[] airplanes = new List[N+1];
        for(int i=1; i<N+1; i++) {
            airplanes[i] = new ArrayList<>();
        }
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a>b) continue; // 동쪽으로 이동하는 경로는 필요없음
            airplanes[a].add(new City(b, c));
        }

        int answer = 0;
        int count = 1;
        
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        while(!q.isEmpty() && count < M) {
            int size = q.size();
            while(size-->0) { // count마다 갈 수 있는 경로 dp 계산
                int now = q.poll();
                for(City c: airplanes[now]) {
                    int next = c.number;
                    int nextCount = dp[count][now]+c.value;
                    
                    if(dp[count+1][next]>=nextCount) continue;
                    
                    dp[count+1][next] = nextCount;
                    q.add(next);
                }
            }
            count++;
        }
        for(int i=2; i<=M; i++) {
            answer = Math.max(answer, dp[i][N]);
        }

        System.out.println(answer);
        br.close();
    }
    
    static class City {
        int number;
        int value;
        
        public City(int number, int value) {
            this.number = number;
            this.value = value;
        }
    }
}