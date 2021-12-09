package com.ttahb.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to to find out the sum of all leaf nodes in a Rooted tree. A node can have multiple children. Direction of nodes is top-down.
 * @author bhattvijay69@hotmail.com
 */
public class LeafSum {

    /** A class that represents any node, its value, and child nodes it is pointing to. */
    public static class Node {

        private int value;
        private List<Node> childNodes;

        public Node(int value){
            this.value = value;
            this.childNodes = new ArrayList<>();
        }

        public int getValue(){
            return value;
        }

        public void addChild(Node ...nodes){
            for(Node node:nodes) {
                childNodes.add(node);
            }
        }

        public List<Node> getChildNodes(){
            return childNodes;
        }

    }

    /**
     * Calculates sum of the all the leaf nodes given the root of a rooted tree.
     * @param node - root node for the tree
     * @return - sum of leaf nodes.
     */
    public int leafSum(Node node){

        if(node == null){
            return 0;
        }

        if(node.getChildNodes().size() == 0){
            return node.getValue();
        }

        int sum = 0;
        for(Node childNode:node.getChildNodes()){
            sum += leafSum(childNode);
        }

        return sum;
    }

}