import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static class Top {
        int num; // 탑의 번호
        int height; // 탑의 높이

        public Top(int num, int height) {
            this.num = num;
            this.height = height;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        Stack<Top> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            int height = Integer.parseInt(st.nextToken());
            // 초기 과정
            if (stack.isEmpty()) {
                stack.push(new Top(i, height));
                sb.append("0 ");
                continue;

            }
            while (true) {
                if (stack.isEmpty()) {
                    stack.push(new Top(i, height));
                    sb.append("0 ");
                    break;
                }
                else {
                    Top top = stack.peek();
                    // 만약 이전 탑의 높이가 현재 탑보다 낮을 경우
                    if (top.height < height) {
                        stack.pop();
                        //sb.append("0 ");
                        //stack.push(new Top(i, height));
                    }

                    // 이전 탑이 현재 탑보다 높은 탑일 경우
                    else {
                        sb.append(top.num + " ");
                        stack.push(new Top(i, height));
                        break;
                    }
                }
            }
        }

        System.out.print(sb.toString());
    }
}
