import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static List<int[]> arr;
    public static List<Integer> distinctArr;
    public static int N;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String read = br.readLine();
        N = Integer.parseInt(read);

        arr = new ArrayList<>();
        //arrCopy = new ArrayList<>();
        distinctArr = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String readLine = br.readLine();
            arr.add(new int[] {i+1, Integer.parseInt(readLine)});
            distinctArr.add(Integer.parseInt(readLine));
        }

        distinctArr = distinctArr.stream().distinct().collect(Collectors.toList());

       // 첫째 줄 정수가 둘째 줄의 정수에 없는 값이라면 해당 레코드는 삭제

        int delNum = 1;
        while (delNum != 0) {
            delNum = 0;
            for (int i = 0; i < arr.size(); i++) {
                if (!distinctArr.contains(arr.get(i)[0])) {
                    //System.out.println("삭제된 원소: " + arr.get(i)[1]);
                    arr.remove(i);
                    delNum += 1;
                }
            }
            distinctArr = new ArrayList<>();
            for (int i = 0; i < arr.size(); i++) {
                distinctArr.add(arr.get(i)[1]);
            }
        }

        distinctArr = distinctArr.stream().distinct().collect(Collectors.toList());
        Collections.sort(distinctArr);

        System.out.println(distinctArr.size());
        for (Integer i: distinctArr) {
            bw.write(Integer.toString(i)+"\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }


}
