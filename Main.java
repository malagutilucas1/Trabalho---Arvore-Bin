import java.util.Scanner;

class Node {
    int val;
    Node left, right;

    public Node(int item) {
        val = item;
        left = right = null;
    }
}

class BST {
    Node root;

    BST() {
        root = null;
    }

    // Método para inserir um novo nó
    void insert(int key) {
        root = insertRec(root, key);
    }

    // Método recursivo para inserir um novo nó na árvore
    Node insertRec(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (key < root.val)
            root.left = insertRec(root.left, key);
        else if (key > root.val)
            root.right = insertRec(root.right, key);

        return root;
    }

    // Método para deletar um nó da árvore
    void delete(int key) {
        root = deleteRec(root, key);
    }

    // Método recursivo para deletar um nó da árvore
    Node deleteRec(Node root, int key) {
        if (root == null)
            return root;

        if (key < root.val)
            root.left = deleteRec(root.left, key);
        else if (key > root.val)
            root.right = deleteRec(root.right, key);
        else {
            // Caso 1: Nó folha (sem filhos)
            if (root.left == null && root.right == null)
                return null;

                // Caso 2: Nó com um filho
            else if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // Caso 3: Nó com dois filhos
            root.val = minValue(root.right);
            root.right = deleteRec(root.right, root.val);
        }

        return root;
    }

    // Método para encontrar o menor valor em uma subárvore
    int minValue(Node root) {
        int minv = root.val;
        while (root.left != null) {
            minv = root.left.val;
            root = root.left;
        }
        return minv;
    }

    // Método para travessia em ordem
    void inorder() {
        inorderRec(root);
        System.out.println();
    }

    // Método recursivo para travessia em ordem
    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.val + " ");
            inorderRec(root.right);
        }
    }

    // Exemplo de uso
    public static void main(String[] args) {
        BST tree = new BST();
        Scanner scanner = new Scanner(System.in);

        // Inserir alguns valores na árvore
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        System.out.println("Árvore em ordem:");
        tree.inorder();

        while (true) {
            System.out.print("Digite um número para remover (ou -1 para sair): ");
            int num = scanner.nextInt();

            if (num == -1) {
                break;  // Sai do loop se o usuário digitar -1
            }

            tree.delete(num);

            System.out.println("Árvore após remoção:");
            tree.inorder();
        }

        scanner.close();
    }
}
