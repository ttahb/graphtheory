package com.ttahb.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author - bhattvijay69@hotmail.com
 * Why we need to root a tree ?
 * - 1) Adds structure or simplifies a problem
 * - 2) It enables to perform recursive algorithm
 * - 3) Transforms a tree to have directed edges which generally are easier to work with.
 * - Conceptually rooting a tree means, "picking up" the tree from a specific node and having all
 * the edges point downwards. You can root a tree using any node. But not every node results in
 * a well-balanced tree.
 * - To get a resultant balanced tree, we must be selective about our designated root node.
 */
public class RootingATree {

    private final List<ArrayList<Integer>> adjList;

    /**
     * @param n - Number of nodes of the input graph which is to be rooted.
     */
    public RootingATree(int n) {
        adjList = Stream.generate(ArrayList<Integer>::new).limit(n).toList();
    }

    /**
     * Helper method to build the starting graph which has to be rooted.
     * Add an undirected edge only once, it adds it both ways to the adjacency list.
     *
     * @param u - any node "u" forming an edge (u,v)
     * @param v - any node "v" forming an edge (u,v)
     */
    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    public List<ArrayList<Integer>> getAdjList() {
        return adjList;
    }

    /**
     * A class to represent newly formed tree
     */
    public static class TreeNode {

        private int id;
        private TreeNode parent;
        private List<TreeNode> children;

        public TreeNode(int id, TreeNode parent, TreeNode... children) {
            this.id = id;
            this.parent = parent;

            if (children != null) {
                if (this.children == null) {
                    this.children = new ArrayList<>();
                }

                for (TreeNode child : children) {
                    this.children.add(child);
                }
            }
        }

        public int getId() {
            return id;
        }

        public TreeNode getParent() {
            return parent;
        }

        public void setParent(TreeNode parent) {
            this.parent = parent;
        }

        public List<TreeNode> getChildren() {
            return children;
        }

        public void addChild(TreeNode child) {
            if (null == this.children) {
                this.children = new ArrayList<>();
            }
            this.children.add(child);
        }
    }

    /**
     * A function to transform an undirected acyclic graph into a rooted directed tree.
     *
     * @param adjList - represents the graph with undirected edges which has to be transformed into a rooted tree.
     * @param rootId  - designated root node passed by the user - it will be the id of the root of the rooted tree.
     * @return - returns the root of the rooted tree.
     */
    public TreeNode rootTree(List<ArrayList<Integer>> adjList, int rootId) {
        TreeNode root = new TreeNode(rootId, null, null);
        return buildTree(adjList, root, null);
    }

    /**
     * A recursive DFS function to build rooted tree.
     *
     * @param adjList - same as above
     * @param node    - any treeNode
     * @param parent  - parent of the treeNode
     * @return - a TreeNode.
     */
    private TreeNode buildTree(List<ArrayList<Integer>> adjList, TreeNode node, TreeNode parent) {

        ArrayList<Integer> children = adjList.get(node.getId());

        for (Integer child : children) {
            // avoid adding an edge pointing back to the parent
            if (parent != null && child == parent.getId()) {
                continue;
            }

            TreeNode childTreeNode = new TreeNode(child, node, null);
            node.addChild(childTreeNode);
            buildTree(adjList, childTreeNode, node);
        }

        return node;
    }

}
