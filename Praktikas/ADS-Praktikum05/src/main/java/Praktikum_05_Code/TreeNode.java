package Praktikum_05_Code;

class TreeNode<T extends Comparable<T>> {
    T element;
    TreeNode<T> left, right;
    int height;
    int count;

    TreeNode(T element){
        this.element = element;
        this.count = 1;
        this.height = 1;
    }
    TreeNode(T element, TreeNode<T> left, TreeNode<T> right){
        this(element); this.left = left; this.right = right;
    }

    T getValue(){return element;}
}