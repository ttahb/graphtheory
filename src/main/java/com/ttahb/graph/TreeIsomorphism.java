package com.ttahb.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.ttahb.graph.RootingATree.*;

/**
 * Tree Isomorphism - whether two trees are structurally the same. We use the CenterOfATree and RootingATree class methods
 * for finding the center(s) of the tree and rooting. It is essential to find the center of the tree and then do the rooting.
 * For checking isomorphism, it is also important to select the same root node in both trees before encoding using AHU algorithm.
 * @author - bhattvijay69@hotmail.com
 */
public class TreeIsomorphism {

    /**
     * Method to find out about if given two trees are isomorphic.
     * @param tree1 - tree1 as an undirected graph tree represented in adjacency list
     * @param tree2 - tree2 as an undirected graph tree represented in adjacency list
     * @return  - true if trees are isomorphic, otherwise returns false.
     */
    public boolean findIfTreeAreIsomorphic(List<ArrayList<Integer>> tree1, List<ArrayList<Integer>> tree2){
        List<Integer> tree1Centers = new CenterOfATree(tree1.size()).findTreeCenters(tree1);
        List<Integer> tree2Centers = new CenterOfATree(tree2.size()).findTreeCenters(tree2);

        TreeNode rootedTree1 = new RootingATree(tree1.size()).rootTree(tree1, tree1Centers.get(0));
        String encodedTree1 = encode(rootedTree1);

        for(Integer center:tree2Centers){
            TreeNode rootedTree2 = new RootingATree(tree2.size()).rootTree(tree2, center);
            String encodedTree2 = encode(rootedTree2);
            if(encodedTree1.equals(encodedTree2)){
                //System.out.println(encodedTree1);
                //System.out.println(encodedTree2);
                return true;
            }
        }

        return false;
    }

    /**
     * AHU (Aho, Hopcoft, Ullman) algorithm is a clever serialization technique for representing a tree as a unique string.
     * @param rootedTree - accepts a rooted tree to recursively use it to traverse and build up the encoded tree string
     * @return - an encoded form the tree in the form of a string. e.g ((()())(())) of the rooted tree.
     */
    private String encode(TreeNode rootedTree) {
        if(rootedTree == null){
            return "";
        }
        List<String> labels = new ArrayList<>();
        if(rootedTree.getChildren() != null){
            for(TreeNode childNode: rootedTree.getChildren()){
                labels.add(encode(childNode));
            }
        }

        Collections.sort(labels);

        StringBuilder result = new StringBuilder();
        for(String label: labels){
            result.append(label);
        }
        return "("+result.toString()+")";
    }

}
