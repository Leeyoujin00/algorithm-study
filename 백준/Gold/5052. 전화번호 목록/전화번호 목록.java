import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    static StringBuilder sb = new StringBuilder();

    static class Node {
        HashMap<Character, Node> child;
        boolean endOfWord;

        public Node() {
            this.child = new HashMap<>();
            this.endOfWord = false;
        }
    }

    static class Trie {
        Node root;

        public Trie() {
            this.root = new Node();
        }

        public void insert(String str) {
            // 시작노드는 루트
            Node node = this.root;

            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                node.child.putIfAbsent(c, new Node());
                node = node.child.get(c);
            }

            node.endOfWord = true;
        }

        // 해당 번호로 시작하는 번호 있으면 false, 없으면 true 반환
        public boolean isValid(String str) {
            Node node = this.root;

            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                if (!node.child.containsKey(c)) {
                    return false;
                }
                if (node.endOfWord) {
                    return false;
                }
                node = node.child.get(c);
            }

            return node.endOfWord;


        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        List<String> strSet = new ArrayList<>();
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            Trie trie = new Trie();
            strSet.clear();
            for (int i = 0; i < n; i++) {
                String str = br.readLine();
                trie.insert(str);
                strSet.add(str);
            }

            boolean flag = true;
            for (String s : strSet) {
                if (!trie.isValid(s)) {
                    flag = false;
                    break;
                }
            }

            if (flag)  sb.append("YES\n");
            else sb.append("NO\n");
        }

        System.out.print(sb);
    }
}
