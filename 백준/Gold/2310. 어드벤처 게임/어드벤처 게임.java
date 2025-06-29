import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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

            dfs(rooms[1], 0);
            boolean flag = visited[n];

            if (flag) {
                sb.append("Yes" + "\n");
            }
            else {
                sb.append("No" + "\n");
            }
        }

        System.out.print(sb);
    }

    private static void dfs(Room curRoom, int money) {

        int roomNum = curRoom.num, cost = curRoom.cost;
        char roomType = curRoom.type;

        if (visited[roomNum]) return;

        if (roomType == 'L') {
            if (money < cost) {
                money = cost;
            }
        }
        else if (roomType == 'T') {
            if (money < cost) { // 통행료 지불할 수 없으므로 방문 불가
                return;
            }
            else {
                money -= cost;
            }
        }
        visited[roomNum] = true;
        for (int nextRoom : curRoom.connectedRooms) {
            dfs(rooms[nextRoom], money);
        }

    }
}
