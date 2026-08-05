import java.util.*;

class Solution {
    public int solution(String arr[]) {
        int[] num = new int[arr.length / 2 + 1];
        boolean[] plusOp = new boolean[arr.length / 2];

        int opIndex = 0;
        int numCount = 0;

        // 입력
        for (String s : arr) {
            if (s.equals("+")) {
                plusOp[opIndex++] = true;
            } else if (s.equals("-")) {
                opIndex++;
            } else {
                num[numCount++] = Integer.parseInt(s);
            }
        }

        // dp 테이블 초기화
        int[][] maxDp = new int[num.length][num.length];
        int[][] minDp = new int[num.length][num.length];
        for (int i = 0; i < numCount; i++) {
            Arrays.fill(maxDp[i], Integer.MIN_VALUE);
            maxDp[i][i] = num[i];
            Arrays.fill(minDp[i], Integer.MAX_VALUE);
            minDp[i][i] = num[i];
        }

        for (int length = 1; length < numCount; length++) {
            for (int start = 0; start < numCount - length; start++) {
                int end = start + length;
                for (int split = start; split < end; split++) {
                    if (plusOp[split]) {   // + 연산자일 경우
                        // max = max + max
                        maxDp[start][end] = Math.max(maxDp[start][end], maxDp[start][split] + maxDp[split + 1][end]);
                        // min = min + min
                        minDp[start][end] = Math.min(minDp[start][end], minDp[start][split] + minDp[split + 1][end]);
                    } else { // -
                        // max = max + min
                        maxDp[start][end] = Math.max(maxDp[start][end], maxDp[start][split] - minDp[split + 1][end]);
                        // min = min + max
                        minDp[start][end] = Math.min(minDp[start][end], minDp[start][split] - maxDp[split + 1][end]);
                    }
                }
            }
        }
        return maxDp[0][numCount - 1];
    }
}