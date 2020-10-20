package Praktikum_06_Code;

import java.util.LinkedList;
import java.util.Queue;

public class TreeTraversal<T extends Comparable<T>> implements Traversal<T> {

    private TreeNode<T> root;

    public TreeTraversal(TreeNode<T> root) {
        this.root = root;
    }

	@Override
    public void inorder(Visitor<T> vis) {
        // to be done
        inorder(root, vis);
    }

    private void inorder(TreeNode<T> node, Visitor<T> visitor) {
        if (node != null) {
            inorder(node.left, visitor);
            visitor.visit(node.element);
            inorder(node.right, visitor);
        }
    }

	@Override
    public void preorder(Visitor<T> vis) {
        // to be done
        preorder(root, vis);
    }

    private void preorder(TreeNode<T> node, Visitor<T> visitor) {
        if (node != null) {
            visitor.visit(node.element);
            preorder(node.left, visitor);
            preorder(node.right, visitor);
        }
    }

	@Override
    public void postorder(Visitor<T> vis) {
        // to be done
        postorder(root, vis);
    }

    private void postorder(TreeNode<T> node, Visitor<T> visitor) {
        if (node != null) {
            postorder(node.left, visitor);
            postorder(node.right, visitor);
            visitor.visit(node.element);
        }
    }
	
	@Override
    public void interval(T min, T max, Visitor<T> v) {
        // to be done
        interval(root, min ,max, v);
    }

    private void interval(TreeNode<T> node, T min, T max, Visitor<T> visitor) {
        if (node != null) {
            if (node.element.compareTo(max) <= 0 && node.element.compareTo(min) >= 0) {
                visitor.visit(node.element);
            }
            interval(node.left, min, max, visitor);
            interval(node.right, min, max, visitor);
        }
    }

    @Override
    public void levelorder(Visitor<T> vistor) {
        // TODO Auto-generated method stub
        levelOrder(root, vistor);
    }

    private void levelOrder(TreeNode<T> node, Visitor<T> visitor) {
        Queue<TreeNode<T>> q = new LinkedList<>();
        if (node != null) q.add(node);
        while (!q.isEmpty()){
            node = q.remove();
            visitor.visit(node.element);
            if (node.left !=null) q.add(node.left);
            if (node.right != null) q.add(node.right);
        }
    }
}
