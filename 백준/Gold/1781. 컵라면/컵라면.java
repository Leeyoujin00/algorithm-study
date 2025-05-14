import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.valueOf(br.readLine());
        Question[] questionArr = new Question[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int deadline = Integer.valueOf(st.nextToken());
            int cup = Integer.valueOf(st.nextToken());
            questionArr[i] = new Question(deadline, cup);
        }

        // 문제 정렬 - 1. 데드라인 가까운 순, 2. 컵라면 개수 많은 순
        Arrays.parallelSort(questionArr);

        // pq 원소수 == 단위 시간
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (Question q : questionArr) {
            int time = pq.size();
            // 만약 단위시간이 문제의 데드라인보다 작다면, 문제 풀 수 있음
            if (time < q.deadline) {
                pq.offer(q.cup);
            }
            else {
                if (pq.peek() < q.cup) {
                    pq.poll();
                    pq.offer(q.cup);
                }
            }
        }

        long totalCup = 0;
        for (int cup: pq) {
            totalCup += cup;
        }

        System.out.print(totalCup);
    }

    static class Question implements Comparable<Question> {

        int deadline;
        int cup;
        public Question(int deadline, int cup) {
            this.deadline = deadline;
            this.cup = cup;
        }

        @Override
        public int compareTo(Question o) {
            // 데드라인 값이 작은 것이 우선
            // 컵라면 개수가 많은 것이 우선
            if (this.deadline < o.deadline) {
                return -1;
            }
            else if (this.deadline == o.deadline) {
                if (this.cup > o.cup) return 1;
                else if (this.cup == o.cup) return 0;
                else return -1;
            }
            else {
                return 1;
            }
        }
    }
}
