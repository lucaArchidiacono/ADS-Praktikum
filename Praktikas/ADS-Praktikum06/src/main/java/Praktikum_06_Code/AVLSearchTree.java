package Praktikum_06_Code;

public class AVLSearchTree<T extends Comparable<T>> extends SortedBinaryTree<T> {

    /**
     * Return the height of node t, or 0, if null.
     */
    private int height(TreeNode t) {
        return t == null ? 0 : t.height;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param element the item to insert.
     */
    public void add(T element) {
        root = insertAt(root, element);
    }

    private TreeNode<T> balance(TreeNode<T> p) {
        if (p == null) return null;
        if (height(p.left) - height(p.right) == 2) {
            if (height(p.left.left) > height(p.left.right)) {
                // to be done
            } else {
                // to be done
            }
        } else if (height(p.right) - height(p.left) == 2) {
            if (height(p.right.right) > height(p.right.left)) {
                // to be done
            } else {
                // to be done
            }
        }
        p.height = Math.max(height(p.left), height(p.right)) + 1;
        return p;
    }

    /**
     * Internal method to insert into a subtree.
     * @param p the item to insert.
     * @param element the node that roots the tree.
     * @return the new root.
     */
    private TreeNode insertAt(TreeNode p, T element) {
        if (p == null) {
            p = new TreeNode<T>(element);
            return p;
        } else {
            int c = element.compareTo((T) p.element);
            if (c == 0) {
                p.count++;
            } else if (c < 0) {
                p.left = insertAt(p.left, element);
            } else if (c > 0) {
                p.right = insertAt(p.right, element);
            }
        }
        return balance(p);
    }

    // find node to replace
    // find node to replace
    // private TreeNode<T> rep;
    private TreeNode<T> findRepAt(TreeNode<T> node, TreeNode<T> rep) {
        if (node.right != null) {
            node.right = findRepAt(node.right,rep);
        } else {
            rep.element = node.element;
            rep.count = node.count;
            rep.height = node.height;
            node = node.left;
        }
        // to be done
        return node;
    }

    // remove node
    private TreeNode<T> removeAt(TreeNode<T> node, T x, TreeNode<T> removed) {
        if (node == null) {
            return null;
        } else {
            if (x.compareTo(node.element) == 0) {
                // found
                removed.element = node.element;
                if (node.count > 1) {
                    node.count--;
                    return node;
                } else if (node.left == null) {
                    node = node.right;
                } else if (node.right == null) {
                    node = node.left;
                } else {
                    node.left = findRepAt(node.left,node);
                }
            } else if (x.compareTo(node.element) < 0) {
                // search left
                node.left = removeAt(node.left, x, removed);
            } else {
                // search right
                node.right = removeAt(node.right, x, removed);
            }
            // to be done
            return node;
        }
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public T remove(T x) {
        TreeNode<T> removed = new TreeNode<T>(null);
        root = removeAt(root, x, removed);
        return removed.element;
    }

    public Traversal<T> traversal() {
        return new AVLTreeTraversal<T>(root);
    }

    /**
     * Rotate binary tree node with left child.
     * For AVL trees, this is a single rotation for case 1.
     * Update heights, then return new root.
     */
    private TreeNode rotateR(TreeNode k2) {
        TreeNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    /**
     * Rotate binary tree node with right child.
     * For AVL trees, this is a single rotation for case 4.
     * Update heights, then return new root.
     */
    private TreeNode rotateL(TreeNode k1) {
        TreeNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(height(k2.right), k1.height) + 1;
        return k2;
    }

    /**
     * Double rotate binary tree node: first left child
     * with its right child; then node k3 with new left child.
     * For AVL trees, this is a double rotation for case 2.
     * Update heights, then return new root.
     */
    private TreeNode rotateLR(TreeNode k3) {
        k3.left = rotateL(k3.left);
        return rotateR(k3);
    }

    /**
     * Double rotate binary tree node: first right child
     * with its left child; then node k1 with new right child.
     * For AVL trees, this is a double rotation for case 3.
     * Update heights, then return new root.
     */
    private TreeNode rotateRL(TreeNode k1) {
        k1.right = rotateR(k1.right);
        return rotateL(k1);
    }



}
