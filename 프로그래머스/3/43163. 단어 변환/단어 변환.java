public class Solution {

    int n;
    boolean[] visited;
    int minDepth = Integer.MAX_VALUE;

    public int solution(String begin, String target, String[] words) {
        int answer = 0;

        n = words.length;
        visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if (isValid(begin, words[i])) {
                dfs(i, target, words, 1);
            }
        }

        return minDepth == Integer.MAX_VALUE ? 0 : minDepth;
    }

    // 이미 방문한 단어는 탐색하지 않는다.
    private void dfs(int idx, String target, String[] words, int depth) {

        if (words[idx].equals(target)) {
            minDepth = Math.min(minDepth, depth);
        }

        for (int i = 0; i < n; i++) {
            // 아직 탐색하지 않은 단어이고, 서로 변환가능한 단어면 dfs 진행
            if (!visited[i] && isValid(words[idx], words[i])) {
                visited[i] = true;
                dfs(i, target, words, depth+1);
                visited[i] = false;
            }
        }
        
    }

    // 두 단어가 알파벳 한개만 서로 다를 때 true 반환
    private boolean isValid(String a, String b) {

        boolean flag = false;

        int[] alpha = new int[26];

        for (int i = 0; i < a.length(); i++) {
            alpha[a.charAt(i)-97]++;
        }

        for (int i = 0; i < b.length(); i++) {
            if (alpha[b.charAt(i)-97]-1 < 0) {
                if (flag) return false;
                flag = true;
                continue;
            }
            alpha[b.charAt(i)-97]--;
        }

        return true;
    }


}
