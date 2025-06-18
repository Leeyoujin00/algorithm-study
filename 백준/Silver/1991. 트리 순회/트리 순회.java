import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static Node[] tree;

    static class Node {
        char nodeChar;
        Node left; // 왼쪽 자식
        Node right; // 오른쪽 자식

        public Node(char nodeChar) {
            this.nodeChar = nodeChar;
            left = null;
            right = null;
        }
    }

    static void preorder(Node node) {
        if (node == null) return;
        System.out.print(node.nodeChar);
        preorder(node.left);
        preorder(node.right);
    }

    static void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.nodeChar);
        inorder(node.right);
    }

    static void postorder(Node node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.nodeChar);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        tree = new Node[n];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            char nodeChar = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            // 현재 노드가 아직 생성되지 않은 경우, 생성
            if (tree[nodeChar - 'A'] == null) {
                tree[nodeChar - 'A'] = new Node(nodeChar);
            }
            if (left != '.') {
                tree[left - 'A'] = new Node(left);
                tree[nodeChar - 'A'].left = tree[left - 'A'];
            }
            if (right != '.') {
                tree[right - 'A'] = new Node(right);
                tree[nodeChar - 'A'].right = tree[right - 'A'];
            }
        }

        preorder(tree[0]);
        System.out.println();
        inorder(tree[0]);
        System.out.println();
        postorder(tree[0]);
    }
}
