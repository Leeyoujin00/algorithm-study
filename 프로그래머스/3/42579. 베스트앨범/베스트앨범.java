
import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {

        Map<String, List<int[]>> playInfo = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            playInfo.computeIfAbsent(genres[i], k -> (new ArrayList<>())).add(new int[] {i, plays[i]});
        }

        // 각 장르의 재생횟수 저장할 해시맵
        Map<String, Integer> genreNums = new HashMap<>();
        for(Map.Entry<String, List<int[]>> entry: playInfo.entrySet()) {
            // 각 장르의 곡 재생횟수 기준 내림차순 정렬
            entry.getValue().sort((a,b) -> Integer.compare(b[1], a[1]));
            // 해당 장르의 재생횟수 계산
            int num = 0;
            for(int[] play : entry.getValue()) {
                num += play[1];
                System.out.println("순서: " + play[1]);
            }
            System.out.println(num);
            genreNums.put(entry.getKey(), num);
        }

        // 장르 재생횟수 기준 내림차순 정렬
        List<String> keySet = new ArrayList<>(genreNums.keySet());
        keySet.sort((o1, o2) -> genreNums.get(o2).compareTo(genreNums.get(o1)));

        List<Integer> result = new ArrayList<>();
        int p1, p2 = 0;
        int idx1, idx2 = 0;
        for (String key: keySet) {
            System.out.println(genreNums.get(key));
            idx1 = playInfo.get(key).get(0)[0];
            p1 = playInfo.get(key).get(0)[1];
        

            if(!(playInfo.get(key).size() == 1)) {
                p2 = playInfo.get(key).get(1)[1];
                idx2 = playInfo.get(key).get(1)[0];
                for(int i = 1; i < playInfo.get(key).size(); i++) {
                    if (p1 != playInfo.get(key).get(i)[1] && i > 1) break;
                    p2 = playInfo.get(key).get(i)[1];
                    idx2 = playInfo.get(key).get(i)[0];
                    p1 = playInfo.get(key).get(i-1)[1];
                    idx1 = playInfo.get(key).get(i-1)[0];
                }


                if(p1 == p2) {
                    //idx1 = idx2 - 1;
                    if (idx1 > idx2) {
                        result.add(idx2);
                        result.add(idx1);
                    }
                    else {
                        result.add(idx1);
                        result.add(idx2);
                    }
                }
                else {
                    result.add(idx1);
                    result.add(idx2);
                }
            }
            else {
                result.add(idx1);
            }

        }
        int[] answer = result.stream().mapToInt(i->i).toArray();

        return answer;
    }
    // public static void main(String[] args) {
    //     Solution_bestAlbum m = new Solution_bestAlbum();
    //     String[] genres = new String[] {"a", "a", "a", "c", "c", "c", "c"};
    //     int[] plays = new int[] {1, 1, 1, 1, 1, 1, 1};
    //     int[] answer = m.solution(genres, plays);
    //     for(int i : answer) {
    //         System.out.println("결과: " + i);
    //     }
    // }
}
