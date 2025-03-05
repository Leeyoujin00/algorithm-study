import java.io.*;
import java.util.*;

public class Main {
    static class TrieNode {
        Map<Character, TrieNode> children = new TreeMap<>(); // 사전순 정렬을 위해 TreeMap 사용
        int count = 0; // 해당 단어의 등장 횟수
    }

    static class Trie {
        TrieNode root = new TrieNode();
        int totalWords = 0; // 전체 단어 개수

        // Trie에 단어 삽입
        void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                node.children.putIfAbsent(c, new TrieNode());
                node = node.children.get(c);
            }
            node.count++; // 단어 등장 횟수 증가
            totalWords++; // 전체 단어 개수 증가
        }

        // DFS를 이용하여 사전순 출력 (재귀 방식)
        void dfs(TrieNode node, StringBuilder prefix, BufferedWriter bw) throws IOException {
            if (node.count > 0) { // 단어 끝에 도달했을 때
                double percentage = (double) node.count / totalWords * 100;
                bw.write(prefix.toString() + " " + String.format("%.4f", percentage) + "\n");
            }
            for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
                prefix.append(entry.getKey());
                dfs(entry.getValue(), prefix, bw);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }

        // Trie를 DFS 탐색하여 사전순 출력
        void printWords(BufferedWriter bw) throws IOException {
            dfs(root, new StringBuilder(), bw);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Trie trie = new Trie();
        String line;

        // 입력받아 Trie에 삽입
        while ((line = br.readLine()) != null) {
            trie.insert(line);
        }

        // Trie 내 단어를 사전순 출력
        trie.printWords(bw);

        br.close();
        bw.flush();
        bw.close();
    }
}
