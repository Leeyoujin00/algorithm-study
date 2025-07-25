import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        // 자신보다 낮은 높이 나오면, 건물 개수 증가
        StringTokenizer st;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int cur = Integer.parseInt(st.nextToken());

            if (stack.isEmpty()) {
                stack.push(cur);
            }
            else { // 스택이 비어있지 않다면
                if (stack.peek() < cur) { // 현재가 더 높음
                    stack.push(cur);
                }
                else if (stack.peek() > cur) { // 현재가 더 낮음
                    boolean flag = false;
                    while (!stack.isEmpty() && stack.peek() >= cur) {
                        if (stack.peek() == cur) {
                            flag = true;
                        }
                        cnt++;
                        stack.pop();
                        //System.out.println(x + "  증가");
                    }
                    if (cur != 0 && !flag) {
                        stack.push(cur);
                    }
                    else if (flag) {
                        cnt--;
                        stack.push(cur);
                    }
                }
            }
        }

        if (!stack.isEmpty()) {
            if (stack.peek() != 0) {
                cnt += stack.size();
            }
        }

        System.out.print(cnt);


    }
}
