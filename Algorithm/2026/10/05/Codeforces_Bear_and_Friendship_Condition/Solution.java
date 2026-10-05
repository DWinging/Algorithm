import java.io.*;

class Main {

    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();

        int n = readInt();
        int m = readInt();

        int[] parents = new int[n + 1];
        int[] node = new int[n + 1];
        long[] edge = new long[n + 1];
        for(int i = 1; i <= n; i++) {
            parents[i] = i;
            node[i] = 1;
        }

        while(m-- > 0) {
            int u = readInt();
            int v = readInt();

            union(parents, node, edge, u, v);
        }

        System.out.println(solve(parents, node, edge) ? "YES" : "NO");
    }

    private static void union(
        int[] parents,
        int[] node,
        long[] edge,
        int a,
        int b
    ) {
        int pA = find(a, parents);
        int pB = find(b, parents);

        if(pA != pB) {
            parents[pB] = pA;
            node[pA] += node[pB];
            edge[pA] += edge[pB];
        }
        edge[pA]++;
    }

    private static int find(int p, int[] parents) {
        if(parents[p] == p) return p;
        else return parents[p] = find(parents[p], parents);
    }

    private static boolean solve(
        int[] parents,
        int[] node,
        long[] edge
    ) {
        for(int i = 1; i < parents.length; i++) {
            if(parents[i] == i && !checkClique(node[i], edge[i])) return false;
        }

        return true;
    }

    private static boolean checkClique(int node, long edge) {
        return edge == (long) node * (node - 1) / 2;
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}