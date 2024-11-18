import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static int arr[][];
    static int emt = 0;
    static List<int[]> cctv;
    static boolean[][] visited;
    static boolean[] vCC;
    static boolean[][] v;

    static int min = 64;
    // 상하좌우
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    //
    static int[] dy2 = {1, 0,-1, 0};
    static int[] dx2 = {0, 1, 0, -1};

    static int[] dx3 = {};
    static int[] dy3 = {};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        cctv = new ArrayList<>();
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 0) {
                    emt++;
                }
                else if (arr[i][j] < 6) { // cctv 위치 및 번호 저장
                    cctv.add(new int[] {arr[i][j],i,j});
                    //cctvN++;
                }
            }
        }

        vCC = new boolean[cctv.size()];
        visited = new boolean[cctv.size()][];
        for (int i = 0; i < visited.length; i++) {
            if (cctv.get(i)[0] == 2) { // 2번 cctv
                visited[i] = new boolean[2];
            }
            else if (cctv.get(i)[0] == 5) { // 5번 cctv
                visited[i] = new boolean[1];
            }
            else { // 1,3,4번 cctv
                visited[i] = new boolean[4];
            }
        }

        int[] selected = new int[cctv.size()];
        rotation(0,0,selected);
        // 4, 2, 4, 4, 1
        System.out.print(min);
    }

    static void rotation(int start, int r, int[] selected) { //각 cctv 회전

        if (r == cctv.size()) { // 모든 cctv의 방향 설정 완료
            // 사각지대 크기 계산
//            for (int i = 0; i < r; i++) {
//                System.out.print(selected[i] + " ");
//            }
            //System.out.println();
            calculation(selected);

            return;
        }

        for (int c = start; c < cctv.size(); c++) {
            if (!vCC[c]) {
                vCC[c] = true;
                if (cctv.get(r)[0] == 2) { // 2번 cctv
                    for (int i = 0; i < 2; i++) {
                        if (!visited[r][i]) {
                            visited[r][i] = true;
                            selected[r] = i;
                            rotation(c+1, r+1, selected);
                            visited[r][i] = false;
                        }
                    }
                }
                else if (cctv.get(r)[0] == 5) { // 5번 cctv
                    for (int i = 0; i < 1; i++) {
                        if (!visited[r][i]) {
                            visited[r][i] = true;
                            selected[r] = i;
                            rotation(c+1, r+1, selected);
                            visited[r][i] = false;
                        }
                    }
                }
                else { // 1,3,4번 cctv
                    for (int i = 0; i < 4; i++) {
                        if (!visited[r][i]) {
                            visited[r][i] = true;
                            selected[r] = i;
                            rotation(c+1, r+1, selected);
                            visited[r][i] = false;
                        }
                    }
                }
                vCC[c] = false;
            }
        }
    }

    static void calculation(int[] selected) {

        //System.out.println("계산");
        v = new boolean[n][m];
        int count = 0;

//        Queue<int[]> que = new LinkedList<>();
//        for (int i = 0; i < cctv.size(); i++) {
//            int[] l = new int[4];
//            for (int j = 0; j < 3; j++) {
//                l[j] = cctv.get(i)[j];
//            }
//            l[3] = selected[i];
//            que.add(l);
//        }

//        while(!que.isEmpty()) {
//            int[] cctv = que.poll();
//            int cctvType = cctv[0];
//            int y = cctv[1];
//            int x = cctv[2];
//            int s = cctv[3];
//
//
//            if (cctvType == 1) {
//                int ny = y + dy[s];
//                int nx = x + dx[s];
//            }
//            else if (cctvType == 2) {
//                for (int i = 0; i < 2; i++) {
//                    int ny = y +
//                }
//                int
//            }
//
//        }
        for (int i = 0; i < cctv.size(); i++) {
            int cctvType = cctv.get(i)[0];
            int y = cctv.get(i)[1];
            int x = cctv.get(i)[2];
            int s = selected[i];

            if (cctvType == 1) {
                dfs1(y,x,s);
            }
            else if (cctvType == 2) { // 0, 1 -> 01 23
                dfs1(y,x,s*2);
                dfs1(y,x,s*2+1);
            }
            else if (cctvType == 3) {
                dfs2(y,x,s%4);
                dfs2(y,x,(s+1)%4);
            }
            else if (cctvType == 4) { // 0,1,2,3
                dfs2(y,x,s);
                dfs2(y,x,(s+1)%4);
                dfs2(y,x,(s+2)%4);
                //dfs2(y,x,(s+3)%4);
            }
            else if (cctvType == 5) {
                dfs1(y,x,s);
                dfs1(y,x,s+1);
                dfs1(y,x,s+2);
                dfs1(y,x,s+3);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(!v[i][j] && arr[i][j] == 0) {
                    count++;
                }
            }
        }

        min = Math.min(min, count);

    }

    static void dfs1(int y, int x, int a) {
        //System.out.println("dfs1 호출 " + y + " " + x);
        //if (!visited)
        v[y][x] = true;
        int ny = y+dy[a];
        int nx = x+dx[a];

//        if (arr[y][x] == 0) {
//            System.out.println("k");
//        }

        if (0 <= ny && ny < n && 0 <= nx && nx < m) {
            //System.out.println("조건1");
            // if ((!v[ny][nx] && arr[ny][nx] != 6) || (1 <= arr[ny][nx] && arr[ny][nx] < 6))
            if (arr[ny][nx] != 6) {
                //System.out.println("조건2");
                dfs1(ny, nx, a);
            }
        }
    }

    static void dfs2(int y, int x, int a) {
        v[y][x] = true;

        for (int i = 0; i < 2; i++) {
            int ny = y+dy2[a];
            int nx = x+dx2[a];

            if (0 <= ny && ny < n && 0 <= nx && nx < m) {
                //System.out.println("조건1");
                if (arr[ny][nx] != 6) {
                    //System.out.println("조건2");
                    dfs2(ny, nx, a);
                }
            }
        }
    }

}
