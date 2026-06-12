#include <stdlib.h>

/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */

TreeNode* searchTree(vector<int>& preorder, int sPre, int ePre, vector<int>& inorder, int sIn, int eIn) {
    if(sPre + 1 > ePre) return nullptr;

    int root = preorder[sPre];
    int left = 0, right = 0;
    for(int i = sIn; i < eIn; i++) {
        if(inorder[i] == root) {
            left = i - sIn;
            right = eIn - (i + 1);
            break;
        }
    }

    TreeNode* tree = new TreeNode(root);
    tree -> left = searchTree(preorder, sPre + 1, sPre + left + 1, inorder, sIn, sIn + left);
    tree -> right = searchTree(preorder, ePre - right, ePre, inorder, eIn - right, eIn);
    return tree;
}

class Solution {
public:
    TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
        return searchTree(preorder, 0, preorder.size(), inorder, 0, inorder.size());
    }
};