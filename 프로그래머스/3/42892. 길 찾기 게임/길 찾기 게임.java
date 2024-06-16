import java.util.*;

public class Solution {
    static int MAX = 100001;
    static int MIN = -1;
    static int TREE_DEPTH;

    public int[][] solution(int[][] nodeinfo) {
        // 이진트리 정보 저장
        Map<Integer, List<int[]>> preTree = new HashMap<>();
        Map<Integer, List<int[]>> postTree = new HashMap<>();
        Set<Integer> levels = new HashSet<>();

        // 인자로 받은 nodeinfo 배열에서 트리 정보 추출
        for (int i = 0; i< nodeinfo.length; i++) {
            int x = nodeinfo[i][0];
            int y = nodeinfo[i][1];

            preTree.computeIfAbsent(y, k -> new ArrayList<>()).add(new int[] {x, i+1});
            postTree.computeIfAbsent(y, k -> new ArrayList<>()).add(new int[] {x, i+1});
            levels.add(y);
            //preTree.computeIfAbsent(y, k -> new ArrayList<>()).add(new int[]{x, i+1});
            //postTree.computeIfAbsent(y, k -> new ArrayList<>()).add(new int[]{x, i+1});
            //levels.add(y);
        }

        // 각 레벨의 x 좌표 내림차순 정렬
        for (int level : levels) {
            preTree.get(level).sort((a, b) -> Integer.compare(b[0], a[0]));
            postTree.get(level).sort((a, b) -> Integer.compare(b[0], a[0]));
        }

        // 레벨 역순으로 정렬
        List<Integer> levelList = new ArrayList<>(levels);
        Collections.sort(levelList, Collections.reverseOrder());
        TREE_DEPTH = levelList.size();

        List<Integer> preOrder = new ArrayList<>();
        preOrder(0, MIN, MAX, preTree, levelList, preOrder);
        List<Integer> postOrder = new ArrayList<>();
        postOrder(0, MIN, MAX, postTree, levelList, postOrder);

        return new int[][]{
                preOrder.stream().mapToInt(i -> i).toArray(),
                postOrder.stream().mapToInt(i -> i).toArray()
        };

//        return new int[][] {
//                preorder.stream().mapToInt(i -> i).toArray(),
//                postorder.stream().mapToInt(i -> i).toArray()
//        };
    }

    private void preOrder(int depth, int left, int right, Map<Integer, List<int[]>> tree, List<Integer> levels, List<Integer> result) {
        // depth 가 트리의 깊이와 같으면 종료
        if (depth == TREE_DEPTH) return;
        // 해당 level에 탐색할 x 좌표의 노드 안 남아있으면 종료
        if (tree.get(levels.get(depth)).isEmpty()) return;
        // 탐색 범위를 넘어섰으면 종료
        if (!(left < tree.get(levels.get(depth)).get(tree.get(levels.get(depth)).size() - 1)[0] && tree.get(levels.get(depth)).get(tree.get(levels.get(depth)).size() - 1)[0] < right)) return;

        int[] node = tree.get(levels.get(depth)).remove(tree.get(levels.get(depth)).size() - 1);
        // 현재 노드 방문
        result.add(node[1]);
        // 현재 노드의 왼쪽 서브트리 순회
        preOrder(depth+1, left, node[0], tree, levels, result);
        // 현재 노드의 오른쪽 서브트리 순회
        preOrder(depth+1, node[0], right, tree, levels, result);

    }

    private void postOrder(int depth, int left, int right, Map<Integer, List<int[]>> tree, List<Integer> levels, List<Integer> result) {
        if (depth == TREE_DEPTH) return;
        // 해당 level에 탐색할 x 좌표의 노드 안 남아있으면 종료
        if (tree.get(levels.get(depth)).isEmpty()) return;
        if (!(left < tree.get(levels.get(depth)).get(tree.get(levels.get(depth)).size()-1)[0] && tree.get(levels.get(depth)).get(tree.get(levels.get(depth)).size()-1)[0] < right)) return;

        int[] node = tree.get(levels.get(depth)).remove(tree.get(levels.get(depth)).size() - 1);
        postOrder(depth + 1, left, node[0], tree, levels, result);
        postOrder(depth + 1, node[0], right, tree, levels, result);
        result.add(node[1]);
    }

}
