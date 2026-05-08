/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

import java.util.*;

class Solution {

    HashMap<Integer, Integer> map = new HashMap<>();
    int rootIdx = 0, max_size = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        max_size = preorder.length;
        for(int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return searchTree(preorder, 0, inorder.length);
    }

    private TreeNode searchTree(int[] preorder, int s, int e) {
        if(s > e || rootIdx == max_size) return null;

        int root = preorder[rootIdx++];
        int mid = map.get(root);

        TreeNode tree = new TreeNode(root);
        tree.left = searchTree(preorder, s, mid - 1);
        tree.right = searchTree(preorder, mid + 1, e);
        return tree;
    }
}