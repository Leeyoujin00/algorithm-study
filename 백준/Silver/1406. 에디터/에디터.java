import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Stack<Character> stack = new Stack<>(); // 커서 왼쪽 문자 저장
        Stack<Character> tmpStack = new Stack<>(); // 커서 오른쪽 문자 저장
        String line = br.readLine();

        for (int i = 0; i < line.length(); i++) {
            stack.push(line.charAt(i));
        }

        // 명령어를 입력받는다.
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);

            // 커서를 왼쪽으로 한칸 옮김
            if (c == 'L') {
                // 커서가 문장의 맨 앞이면 무시됨
                if (!stack.isEmpty()) {
                    tmpStack.push(stack.pop());
                }
            }
            // 커서를 오른쪽으로 한칸 옮김
            if (c == 'D') {
                // 커서가 문장의 맨 뒤이면 무시됨
                if (!tmpStack.isEmpty()) {
                    stack.push(tmpStack.pop());
                }
            }
            // 커서 왼쪽에 있는 문자를 삭제함
            if (c == 'B') {
                // 커서가 문장의 맨 앞이면 무시됨
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }
            // 문자를 커서 왼쪽에 추가
            if (c == 'P') {
                char en = st.nextToken().charAt(0);
                stack.push(en);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();
        while(!tmpStack.isEmpty()) {
            sb.append(tmpStack.pop());
        }

        System.out.print(sb);
    }
}
