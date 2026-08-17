class Solution {
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        boolean[] check = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(leftChild[i] > -1) {
                if(check[leftChild[i]]) return false;
                check[leftChild[i]] = true;
            }

            if(rightChild[i] > -1) {
                if(check[rightChild[i]]) return false;
                check[rightChild[i]] = true;
            }
        }

        int root = -1;
        for(int i = 0; i < n; i++) {
            if(!check[i]) {
                if(root == -1) root = i;
                else return false;
            }
        }
        
        return root == -1 ? false : bfs(n, root, leftChild, rightChild);
    }

    private boolean bfs(int n, int root, int[] leftChild, int[] rightChild) {
        int[] que = new int[n];
        int head = 0, tail = 0;
        que[tail++] = root;

        while(head < tail) {
            int cur = que[head++];

            int left = leftChild[cur];
            if(left > -1) {
                que[tail++] = left;
            }

            int right = rightChild[cur];
            if(right > -1) {
                que[tail++] = right;
            }
        }

        return tail == n;
    }
}