package com.ttahb.graph;

/**
 * This class helps in finding the height of a binary tree. The height of a tree is the number of edges from the root to the
 * leaf node. Recurrence Relationship ~ Assuming the root x is not a leaf node its height would be
 * h(x) = max(h(x.left),h(x.right)) + 1,  i.e. Maximum out of height of left child and right child  + 1.
 * we consider height of a leaf node to be 0.
 *
 * @author - bhattvijay69@hotmail.com
 */
public class HeightOfTree {

    /**
     * Generally calculate height of a tree while inserting nodes in a tree to reduce the cost.
     * This method is for cases where it will not possible.
     *
     * @param node - expecting root node.
     * @return - height of the tree.
     */
    public int findHeight(Node node) {

        if (node == null) {
            return -1;
        }

        return Math.max(findHeight(node.getLeft()), findHeight(node.getRight())) + 1;
    }

    public static class Node {

        private int data;
        private Node left, right;

        public Node(int data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
