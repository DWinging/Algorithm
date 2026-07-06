class Solution {

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;

        int[] parents = new int[n];
        for(int i = 0; i < n; i++) parents[i] = i;
        
        int res = n;
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if(isConnected[i][j] == 1) {
                    if(union(i, j, parents)) {
                        res--;
                    }
                }
            }
        }

        return res;
    }

    private boolean union(int a, int b, int[] parents) {
        int pA = find(a, parents);
        int pB = find(b, parents);

        if(pA == pB) {
            return false;
        } else {
            parents[pB] = pA;
            return true;
        }
    }

    private int find(int p, int[] parents) {
        if(parents[p] == p) return p;
        return parents[p] = find(parents[p], parents);
    }
}