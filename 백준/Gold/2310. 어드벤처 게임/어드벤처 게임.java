import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static Room[] rooms;
    static boolean[] visited;

    static class Room {
        int num; // 방 번호
        char type;
        int cost; // 레프리콘이나 트롤이 정해놓은 금액
        List<Integer> connectedRooms;

        public Room(int num, char type, int cost) {
            this.num = num;
            this.type = type;
            this.cost = cost;
            connectedRooms = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        while (true) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) break;
            rooms = new Room[n+1];
            visited = new boolean[n+1];

            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());
                char type = st.nextToken().charAt(0);
                int cost = Integer.parseInt(st.nextToken());
                Room room = new Room(i, type, cost);
                while (true) {
                    int roomNum = Integer.parseInt(st.nextToken());
                    if (roomNum == 0) break;
                    room.connectedRooms.add(roomNum);
                }
                rooms[i] = room;
            }

            //dfs(rooms[1], 0);
            boolean flag = bfs();

            if (flag) {
                sb.append("Yes" + "\n");
            }
            else {
                sb.append("No" + "\n");
            }
        }

        System.out.print(sb);
    }

    static class Node {
        int roomNum;
        int money;
        public Node (int roomNum, int money) {
            this.roomNum = roomNum;
            this.money = money;
        }
    }
    // bfs 알고리즘 활용
    private static boolean bfs() {

        int[] moneyCk = new int[n+1]; // 각 방에 도달했을 때의 최대 소지금
        Arrays.fill(moneyCk, -1);
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(1,0));
        moneyCk[1] = 0;

        while (!que.isEmpty()) {
            Node cur = que.poll();
            int roomNum = cur.roomNum, money = cur.money;

            if (roomNum == n) return true;

            for (int nextRoom : rooms[roomNum].connectedRooms) {
                int nextMoney = money;
                if (rooms[nextRoom].type == 'L') {
                    if (nextMoney < rooms[nextRoom].cost) {
                        nextMoney = rooms[nextRoom].cost;
                    }
                }
                else if (rooms[nextRoom].type == 'T') {
                    nextMoney -= rooms[nextRoom].cost;
                }

                if (nextMoney < 0 || moneyCk[nextRoom] >= nextMoney) continue;
                que.offer(new Node(nextRoom, nextMoney));
                moneyCk[nextRoom] = nextMoney;
            }
        }

        return false;
    }
    
}
