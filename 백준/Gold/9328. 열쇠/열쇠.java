import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int h,w;
    static char[][] map;
    static Set<Character> keySet = new HashSet<>();
    static int maxDoc = 0;
    // 상하좌우
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        StringBuilder ans = new StringBuilder();
        for (int tc = 0; tc < t; tc++) {
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken()) + 2;
            w = Integer.parseInt(st.nextToken()) + 2;

            map = new char[h][w];
            // 건물의 가장자리 공간을 표시한다.
            // (빌딩 가장자리의 벽이 아닌 곳을 통해 빌딩 안팎을 드나들 수 있다.)
            for (int i = 0; i < h; i++) {
                map[i][0] = '-';
                map[i][w-1] = '-';
            }
            for (int i = 0; i < w; i++) {
                map[0][i] = '-';
                map[h-1][i] = '-';
            }

            for (int i = 1; i < h-1; i++) {
                String line = br.readLine();
                char[] lineArr = line.toCharArray();
                for (int j = 1; j < w-1; j++) {
                    map[i][j] = lineArr[j-1];
                }
            }

            // 가지고있는 열쇠 저장
            keySet.clear();
            String keys = br.readLine();
            char[] keyList = keys.toCharArray();

            for (Character k : keyList) {
                if (k == '0') continue;
                keySet.add(k);
            }

            // 확인
//            for (int i = 0; i < h; i++) {
//                for (int j = 0; j < w; j++) {
//                    System.out.print(map[i][j]);
//                }
//                System.out.println();
//            }

            maxDoc = 0;
            solution();
            ans.append(maxDoc + "\n");
        }

        System.out.print(ans);
    }

    static class Node {
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    // 각각의 tc에 대해, 훔칠 수 있는 문서의 개수를 구한다.
    private static void solution() {
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(0,0));
        boolean[][] visited = new boolean[h][w];
        visited[0][0] = true;

        int cnt = 0;

        while (!que.isEmpty()) {
            Node cur = que.poll();
            int r = cur.r;
            int c = cur.c;

            // 상하좌우에 대해, 이동할 수 있는 칸을 탐색
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                // 탐색범위 벗어나면 진행 X
                if (nr < 0 || nr >= h || nc < 0 || nc >= w || visited[nr][nc] || map[nr][nc] == '*') {
                    continue;
                }

                // 문일 경우
                // ASCII 코드로 알파벳 대문자는 65 ~ 90, 알파벳 소문자는 97~122
                if (65 <= map[nr][nc] && map[nr][nc] <= 90) {
                    // 해당 문의 열쇠를 가지고 있을 경우
                    if (keySet.contains(Character.toLowerCase(map[nr][nc]))) {
                        // 이동할 수 있다.
                        map[nr][nc] = '.'; // 이동할 수 있는 칸이므로 빈칸 처리
                        que.offer(new Node(nr,nc));
                        visited[nr][nc] = true;

                    }
                }

                // 열쇠일 경우
                if (97 <= map[nr][nc] && map[nr][nc] <= 122) {
                    // 가지고있지 않은 열쇠일 경우
                    if (!keySet.contains(map[nr][nc])) {
                        visited = new boolean[h][w]; // 방문체크 배열 초기화

                        keySet.add(map[nr][nc]); // 열쇠 추가

                        map[nr][nc] = '.';
                        que.offer(new Node(nr,nc));
                        visited[nr][nc] = true;
                    }
                    else { // 가지고있는 열쇠일 경우, 의미가 없으므로 빈칸 처리
                        map[nr][nc] = '.';
                        que.offer(new Node(nr,nc));
                        visited[nr][nc] = true;
                    }
                }

                // 문서일 경우
                if (map[nr][nc] == '$') {
                    cnt++;
                    map[nr][nc] = '.';
                    que.offer(new Node(nr,nc));
                    visited[nr][nc] = true;
                }

                // 건물 밖일 경우, 빈 칸일 경우
                if (map[nr][nc] == '-' || map[nr][nc] == '.') {
                    que.offer(new Node(nr,nc));
                    visited[nr][nc] = true;
                }
            }
        }

        maxDoc = Math.max(maxDoc, cnt);
    }
}
