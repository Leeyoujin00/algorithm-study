import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static String str;
    static long count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();

        backtracking(0,0,0,false,1);
        System.out.print(count);
    }

    static void backtracking(int idx, int vNum, int cNum, boolean l, long num) {

        if (vNum >= 3 || cNum >= 3) return;
        if (idx == str.length()) {
            if (l) count += num;
            return;
        }

        char currentChar = str.charAt(idx);
        // 빈칸일 경우
        if (currentChar == '_') {
            // 모음으로 채우기
            backtracking(idx+1, vNum+1, 0, l, num*5);
            // 자음으로 채우기
            backtracking(idx+1, 0, cNum+1, l, num*20);
            // L로 채우기
            backtracking(idx+1, 0, cNum+1, true, num);
        }

        // 알파벳일 경우
        else {
            backtracking(idx+1, isVowels(currentChar) ? vNum+1 : 0 , isCons(currentChar) ? cNum+1 : 0, currentChar == 'L' || l, num);
        }
    }

    static boolean isVowels(char c) {
        if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') return true;
        return false;
    }

    static boolean isCons(char c) {
        return !isVowels(c);
    }
}


