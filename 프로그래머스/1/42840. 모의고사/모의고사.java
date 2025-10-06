import java.util.*;

class Solution {
    static int[] s1 = {1,2,3,4,5};
    static int[] s2 = {2,1,2,3,2,4,2,5};
    static int[] s3 = {3,3,1,1,2,2,4,4,5,5};
    static int max = 0;

    public int[] solution(int[] answers) {
        int[] answer = {};
        
        // 12345 ...
        // 21232425
        // 3311224455
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            list.add(solve(answers, i));
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (max == list.get(i)) result.add(i+1);
        }
        
        answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
    
    private static int solve(int[] answers, int s) {
        
        int score = 0;
        int n = 0;
        int idx = 0;
        
        if (s == 1) {
            n = s1.length;
            for (int i = 0; i < answers.length; i++) {
                if (answers[i] == s1[idx++]) {
                    score++;
                }
                if (idx == n) idx = 0;
            }
        }
        else if (s == 2) {
            n = s2.length;
            for (int i = 0; i < answers.length; i++) {
                if (answers[i] == s2[idx++]) {
                    score++;
                }
                if (idx == n) idx = 0;
            }
        }
        else if (s == 3) {
            n = s3.length;
            for (int i = 0; i < answers.length; i++) {
                if (answers[i] == s3[idx++]) {
                    score++;
                }
                if (idx == n) idx = 0;
            }
        }
        
        max = Math.max(max, score);
        return score;
    }
}