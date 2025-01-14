import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int l,r,c;
    static char[][][] map;
    static int[] ds = {1,-1,0,0,0,0};
    static int[] dx = {0,0,1,-1,0,0};
    static int[] dy = {0,0,0,0,1,-1};
    static List<String> print = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if (l == 0 && r == 0 && c == 0) break;

            map = new char[l][r][c];
            int startS = 0; int startX= 0; int startY = 0;
            for (int stair = 0; stair < l; stair++) {
                for (int i = 0; i < r; i++) {
                    String str = br.readLine();
                    for (int j = 0; j < c; j++) {
                        map[stair][i][j] = str.charAt(j);
                        if (map[stair][i][j] == 'S') {
                            startS = stair;
                            startX = i;
                            startY = j;
                        }
                    }
                }
                br.readLine();
            }

            bfs(startS, startX, startY);
        }
        
        for (String s: print) {
            System.out.println(s);
        }

    }

    static void bfs(int startS, int startX, int startY) {

        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] {startS, startX, startY, 0});
        boolean[][][] visited = new boolean[l][r][c];
        visited[startS][startX][startY] = true;

        while (!que.isEmpty()) {
            int[] cur = que.poll();

            for (int i = 0; i < 6; i++) {
                int ns = cur[0] + ds[i];
                int nx = cur[1] + dx[i];
                int ny = cur[2] + dy[i];
                int step = cur[3];

                // 탐색범위 벗어나면 진행 x
                if (ns < 0 || ns >= l || nx < 0 || nx >= r || ny < 0 || ny >= c) {
                    continue;
                }

                // 방문한 적 있거나 벽이면 진행 x
                if (visited[ns][nx][ny] || map[ns][nx][ny] == '#') continue;

                if (map[ns][nx][ny] == 'E') {
                    int result = step+1;
                    String s = "Escaped in " + result + " minute(s).";
                    print.add(s);
                    return;
                }
                visited[ns][nx][ny] = true;
                //System.out.println(ns + " " + nx + " " + ny + " " + step+1);
                que.offer(new int[] {ns, nx, ny, step+1});
            }
        }

        String s = "Trapped!";
        print.add(s);

    }
}
