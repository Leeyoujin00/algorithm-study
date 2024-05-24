import java.util.*;

public class Solution {
    public static List<List<Integer>> fromNodes;
    public static List<List<Integer>> toNodes;
    public static int nodeNum = 0;

    public int[] solution(int[][] edges) {


        // 주어진 엣지 정보로부처 노드 개수(max 정점 번호)를 구함
        for (int i = 0; i < edges.length; i++) {
            if(nodeNum < edges[i][0]) nodeNum = edges[i][0];
            if(nodeNum < edges[i][1]) nodeNum = edges[i][1];
        }

        fromNodes = new ArrayList<>(nodeNum+1);
        toNodes = new ArrayList<>(nodeNum+1);


        for (int i = 0; i <= nodeNum; i++) {
            fromNodes.add(new ArrayList<>());
            toNodes.add(new ArrayList<>());
        }
        // 각 노
        for (int i = 0; i < edges.length; i++) {
            fromNodes.get(edges[i][1]).add(edges[i][0]);
            toNodes.get(edges[i][0]).add(edges[i][1]);
        }

        int createdNodeNum = 0;
        // 그래프 연결하기 위해 생성한 노드 구하기
        for (int i = 1; i <= nodeNum; i++) {
            if ((fromNodes.get(i).size() == 0) && (toNodes.get(i).size() >= 2)) {
                createdNodeNum = i;
                break;
            }
        }

        // 총 그래프 수
        int graphNum = toNodes.get(createdNodeNum).size();

        int donutGraphNum = 0;
        int stickGraphNum = 0;
        int eightGraphNum = 0;

//        for (int i = 0; i <= nodeNum; i++) {
//            System.out.println(fromNodes.get(i).size());
//        }
        int minus = 0;
        for (int i = 1; i <= nodeNum; i++) {
            if (i == createdNodeNum) continue;
            if (toNodes.get(i).size() == 0 && fromNodes.get(i).size() == 0) {
                    continue;
            }

            if (fromNodes.get(i).contains(createdNodeNum)) minus = 1;
            else minus = 0;

            if (fromNodes.get(i).size()-minus == 2 && toNodes.get(i).size() == 2) {
                eightGraphNum += 1;
            }

             else if (fromNodes.get(i).size()-minus == 0) {
                stickGraphNum += 1;
            }
        }

        donutGraphNum = graphNum - (stickGraphNum + eightGraphNum);

        int[] answer = {createdNodeNum, donutGraphNum, stickGraphNum, eightGraphNum};

        return answer;
    }
}
