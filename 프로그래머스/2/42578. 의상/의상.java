import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> hash = new HashMap<>();
        for(int i = 0; i < clothes.length; i++) {
            if (hash.containsKey(clothes[i][1])) {
                hash.put(clothes[i][1], hash.get(clothes[i][1]) + 1);
            }
            else {
                hash.put(clothes[i][1], 1);
            }
        }
        
        int answer = -1;
        //answer += hash.size();
        int n = 1;
        for (Map.Entry<String, Integer> entry: hash.entrySet()) {
            n *= (entry.getValue() + 1);
        }
        
        answer += n;
        
        return answer;
        
    }
}