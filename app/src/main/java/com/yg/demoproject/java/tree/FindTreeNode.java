package com.yg.demoproject.java.tree;

import java.util.Stack;

/**
 * 给定二叉树的节点a，找出从根到节点a的路径
 *
 * 思路：1 如果根节点是a，则直接返回a
 *      2 如果根节点不是，则把根节点入栈，然后找左子树和右子树，如果找到则返回，如果找不到则出栈根节点
 */
public class FindTreeNode {
    Node mRoot;
    Stack<Node> stack = new Stack<>();
    class Node {
        String value;
        Node left;
        Node right;

        Node(String value) {
            this.value = value;
        }
    }

    public FindTreeNode() {
        initTree();
    }

    /**
     *      a
     *     / \
     *    b   c
     *   / \  / \
     *  d  e  f  g
     *  /
     * h
     */
    void initTree() {
        mRoot = new Node("a");
        Node s = mRoot;
        addNode("b", s, true);
        addNode("c", s, false);
        s = s.left;
        addNode("d", s, true);
        addNode("e", s, false);
        addNode("h", s.left, true);
        s = mRoot.right;
        addNode("f", s, true);
        addNode("g", s, false);
    }

    void addNode(String value, Node root, boolean left) {
        Node node = new Node(value);
        if (left) {
            root.left = node;
        } else {
            root.right = node;
        }
    }

    public String findNode(String s) {
        return findNode(new Node(s));
    }

    String findNode(Node target) {
        if (!find(mRoot, target)) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        while (!stack.empty()) {
            Node node = stack.pop();
            result.append(node.value);
        }
        return result.reverse().toString();
    }

    boolean find(Node root, Node target) {
        if (root == null) return false;
        stack.push(root);
        if (root.value.equals(target.value)) {
            return true;
        }

        if (find(root.left, target)) {
            return true;
        }

        if (find(root.right, target)) {
            return true;
        }

        stack.pop();
        return false;
    }
}
