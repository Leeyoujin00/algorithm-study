import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, int[]> hash = new HashMap<>();
        //Map<String, Integer> completionHash = new HashMap<>();
        
        for (int i = 0; i < participant.length; i++) {
            if (hash.containsKey(participant[i])) {
                hash.put(participant[i], new int[] {hash.get(participant[i])[0]+1 , 0});
            }
            else hash.put(participant[i], new int[] {1, 0});
        }
        
        for (int i = 0; i < completion.length; i++) {
            hash.put(completion[i], new int[] {hash.get(completion[i])[0] , hash.get(completion[i])[1]+1});
        }
        
        String answer = "answer";
        for (Map.Entry<String, int[]> entry: hash.entrySet()) {
            if (entry.getValue()[0] != entry.getValue()[1]) {
                answer = entry.getKey();
                break;
            }
        }
        
        return answer;
    }
}