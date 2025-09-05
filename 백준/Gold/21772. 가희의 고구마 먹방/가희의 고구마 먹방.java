import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int r,c,t;
    static char[][] map;
    static int[] dx = {-1,1,0,0,0};
    static int[] dy = {0,0,-1,1,0};
    static int ans = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        map = new char[r][c];

        int x = 0, y = 0;
        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'G') {
                    x = i;
                    y = j;
                }
            }
        }

        dfs(x,y,0,0);
        System.out.print(ans);

    }

    private static void dfs(int x, int y, int time, int cnt) {

        if (time == t) {
            ans = Math.max(ans, cnt);
            return;
        }

        for (int i = 0; i < 5; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= r || ny < 0 || ny >= c || map[nx][ny] == '#') {
                continue;
            }
            if (map[nx][ny] == 'S') {
                map[nx][ny] = '.';
                dfs(nx,ny,time+1, cnt+1);
                map[nx][ny] = 'S';
            }
            else {
                dfs(nx,ny,time+1,cnt);
            }
        }
    }
}
