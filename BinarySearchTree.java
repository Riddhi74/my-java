public class BinarySearchTree {
    // Node class representing each element in the BST
    static class Node {
        int data;
        Node left, right;

        // Constructor to create a new node
        public Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    // Root of the BST
    private Node root;

    // Constructor to initialize an empty tree
    public BinarySearchTree() {
        root = null;
    }

    // Method to insert a node in the tree
    public void insert(int data) {
        root = insertRec(root, data);
    }

    // Recursive function to insert a new node
    private Node insertRec(Node root, int data) {
        // If the tree is empty, return a new node
        if (root == null) {
            root = new Node(data);
            return root;
        }

        // Otherwise, recur down the tree
        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }

        // Return the unchanged node pointer
        return root;
    }

    // Method to delete a node from the tree
    public void delete(int data) {
        root = deleteRec(root, data);
    }

    // Recursive function to delete a node
    private Node deleteRec(Node root, int data) {
        // Base case: if the tree is empty
        if (root == null) {
            return root;
        }

        // Otherwise, recur down the tree
        if (data < root.data) {
            root.left = deleteRec(root.left, data);
        } else if (data > root.data) {
            root.right = deleteRec(root.right, data);
        } else {
            // Node to be deleted found

            // Case 1: Node has no child (leaf node)
            if (root.left == null && root.right == null) {
                return null;
            }

            // Case 2: Node has only one child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Case 3: Node has two children
            // Get the inorder successor (smallest in the right subtree)
            root.data = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.data);
        }

        return root;
    }

    // Helper function to find the minimum value node in the tree
    private int minValue(Node root) {
        int minValue = root.data;
        while (root.left != null) {
            root = root.left;
            minValue = root.data;
        }
        return minValue;
    }

    // Method for inorder traversal (left, root, right)
    public void inorder() {
        inorderRec(root);
    }

    // Recursive function for inorder traversal
    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);  // Visit left subtree
            System.out.print(root.data + " ");  // Visit root
            inorderRec(root.right);  // Visit right subtree
        }
    }

    // Main function to test the BST implementation
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        // Insert values
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        // Print inorder traversal (should be sorted)
        System.out.println("Inorder traversal of the BST:");
        tree.inorder();

        // Deleting nodes
        System.out.println("\n\nDeleting node 20 (leaf node):");
        tree.delete(20);
        tree.inorder();

        System.out.println("\n\nDeleting node 30 (node with one child):");
        tree.delete(30);
        tree.inorder();

        System.out.println("\n\nDeleting node 50 (node with two children):");
        tree.delete(50);
        tree.inorder();
    }
}
