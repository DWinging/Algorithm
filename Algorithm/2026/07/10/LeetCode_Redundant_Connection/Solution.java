class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int[] res = new int[2];

        int n = edges.length;
        int[] parents = new int[n + 1];
        for(int i = 0; i <= n; i++) parents[i] = i;

        for(int[] edge : edges) {
            if(!union(edge[0], edge[1], parents)) {
                res[0] = edge[0];
                res[1] = edge[1];
                break;
            }
        }

        return res;
    }

    private boolean union(int a, int b, int[] parents) {
        int pA = find(a, parents);
        int pB = find(b, parents);

        if(pA == pB) return false;
        else {
            parents[pB] = pA;
            return true;
        }
    }

    private int find(int p, int[] parents) {
        if(p == parents[p]) return p;
        return parents[p] = find(parents[p], parents);
    }
}