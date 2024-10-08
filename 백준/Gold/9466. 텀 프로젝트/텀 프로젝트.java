import java.util.*;
import java.io.*;

// 백준 G3 - 텀 프로젝트
public class Main {
    public static List<List<Integer>> graph;
    public static int[] selectInfo;
    public static int T;
    public static int n;
    public static int result = 0;
    public static List<Integer> resultList;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String testNum = br.readLine();
        T = Integer.parseInt(testNum);

        String line;

        resultList = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            line = br.readLine();
            n = Integer.parseInt(line);
            String[] readLine = br.readLine().split(" ");
            graph = new ArrayList<>();

            for (int j = 0; j < n+1; j++) {
                graph.add(new ArrayList<>());
            }
            selectInfo = new int[n+1];
            //selected = new int[n+1];

            for (int j = 1; j < n+1; j++) {
                graph.get(Integer.parseInt(readLine[j-1])).add(j);
                selectInfo[j] = Integer.parseInt(readLine[j-1]);
                //selected[j] = 0;
            }
            result = 0;
            dfs();
            resultList.add(result);
        }

        for (int i = 0; i < T-1; i++) {
            bw.write(Integer.toString(resultList.get(i))+"\n");
        }
        bw.write(Integer.toString(resultList.get(T-1)));

        bw.flush();
        br.close();
        bw.close();

    }

    public static void dfs() {
        List<Integer> zeroNum = new ArrayList<>();

        // 아무에게도 선택되지 않은 학생들의 index 저장
        for(int i = 1; i < n+1; i++) {
            if (graph.get(i).size() == 0) {
                zeroNum.add(i);
                //System.out.println(" 선택 안됨: "+ i);
            }
        }

        for(int i = 0; i < zeroNum.size(); i++) {
            int idx = zeroNum.get(i);
            //System.out.println("선택안됨: " + idx);
            result += 1;
            graph.get(selectInfo[idx]).remove((Integer)idx);
            idx = selectInfo[idx];
            if(graph.get(idx).size() == 0) {
                zeroNum.add(idx);
            }
            //else break;
//            while(true) {
//                graph.get(selectInfo[idx]).remove((Integer)idx);
//                idx = selectInfo[idx];
//                if(graph.get(idx).size() == 0) {
//                    zeroNum.add(idx);
//                }
//                else break;
//            }
        }
    }

}
