import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int s;
    static boolean[][] visited; // [clipboard][emoticon]
    static int result;

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        s= scanner.nextInt();

        visited = new boolean[1001][1001];

        bfs();
        System.out.print(result);

    }

    // 최솟값을 구해야하므로, bfs 실행
    public static void bfs() {

        Queue<state> queue = new LinkedList<>();
        queue.offer(new state(0,1,0));
        visited[0][1] = true;

        while (!queue.isEmpty()) {
            state current = queue.poll();

            if (current.emoticon == s) {
                result = current.step;
                return;
            }

            // 연산 1. 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장
            if (!visited[current.emoticon][current.emoticon]) {
                visited[current.emoticon][current.emoticon] = true;
                queue.offer(new state(current.emoticon, current.emoticon, current.step+1));
            }

            // 연산 2. 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기
            // 클립보드가 비어있으면 붙여쓰기 할 수 없음
            if (current.clipboard+current.emoticon <= s && !visited[current.clipboard][current.clipboard+current.emoticon] && current.clipboard != 0) {
                visited[current.clipboard][current.clipboard+current.emoticon] = true;
                queue.offer(new state(current.clipboard, current.clipboard+current.emoticon, current.step+1));
            }

            // 연산 3. 화면에 있는 이모티콘 중 하나를 삭제
            if (current.emoticon - 1 >= 1 && !visited[current.clipboard][current.emoticon-1]) {
                visited[current.clipboard][current.emoticon-1] = true;
                queue.offer(new state(current.clipboard, current.emoticon-1, current.step+1));
            }

        }
    }

    static class state {
        int clipboard;
        int emoticon;
        int step;

        public state(int clipboard, int emoticon, int step) {
            this.clipboard = clipboard;
            this.emoticon = emoticon;
            this.step = step;
        }
    }
}
