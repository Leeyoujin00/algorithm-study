import java.io.*;
import java.util.*;
public class Main {
    //오락실 위치 정보 관련 클래스
    static class Pos{
        int r, c;
        Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    static int N, M, C;
    //오락실 정보 배열
    static Pos[] arcades;
    //DP[i][j][c][k] 배열
    static int[][][][] DP;
    //2차원 구역 오락실 존재 유므 배열
    static boolean[][] isArcades;
    static final int MOD = 1000007;
    public static void main(String[] args) throws IOException {
        //입력값 처리하는 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //결과값 출력하는 BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arcades = new Pos[C+1];
        isArcades = new boolean[N+1][M+1];
        DP = new int[N+1][M+1][C+1][C+1];
        DP[1][0][0][0]= 1;
        //오락실 정보 저장
        for(int i=1;i<=C;i++){
            st = new StringTokenizer(br.readLine()," ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arcades[i] = new Pos(r, c);
            isArcades[r][c] = true;
        }
        //DP[i][j][0][0]일 때 구하기
        init();

        //DP[i][j][c][k] 구하기.
        for(int i=1;i<=C;i++){
            for(int j=i;j<=C;j++){
                setArcadesValue(i, j);
            }
        }

        // 0 ~ C번 오락실 방문할 때 경우의 수 BufferedWriter 저장
        for(int i=0;i<=C;i++){
            int sum = 0;
            for(int j=i;j<=C;j++){
                sum = (sum + DP[N][M][i][j]) % MOD;
            }
            bw.write(String.valueOf(sum));
            bw.write(" ");
        }
        bw.flush();		//결과 출력
        bw.close();
        br.close();
    }
    //DP[i][j][0][0], 오락실 0개일 때 경우의 수 구하는 함수
    static void init(){
        for(int i=1;i<=N;i++){
            for(int j=1;j<=M;j++){
                if(isArcades[i][j]){
                    continue;
                }
                DP[i][j][0][0] = (DP[i-1][j][0][0] + DP[i][j-1][0][0]) % MOD;
            }
        }
    }

    //DP[i][j][c][k]에 대해 점화식을 통해 경우의 수 구하기.
    static void setArcadesValue(int h, int c){
        Pos cur = arcades[c];

        //DP[i][j][c][k] = DP[i-1][j][c-1][1~(k-1)] + DP[i][j-1][c-1][1 ~ (k-1)]
        //h-1부터 시작하는 이유는 c-1번째는 최소 c-1번째 오락실을 이용해야 하기 때문입니다.
        for(int i=h-1;i<c;i++){
            DP[cur.r][cur.c][h][c] += (DP[cur.r-1][cur.c][h-1][i] + DP[cur.r][cur.c-1][h-1][i]) % MOD;
        }


        //오락실 정보 저장한 뒤, 학원 도착하는 경우의 수 구하기.
        for(int i=cur.r; i<=N;i++){
            for(int j=cur.c;j<=M;j++){
                if(isArcades[i][j]){
                    continue;
                }
                DP[i][j][h][c] = (DP[i-1][j][h][c] + DP[i][j-1][h][c]) % MOD;
            }
        }
    }
}