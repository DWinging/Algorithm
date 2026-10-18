import java.util.*;
import java.io.*;

class Main {

    private static class Node {
        int p, v, edge;

        public Node(int p, int v, int edge) {
            this.p = p;
            this.v = v;
            this.edge = edge;
        }
    }

    static final int LOG = 18;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();

        int n = readInt();
        ArrayList<int[]>[] list = buildGraph(n);

        int[][] parents = new int[LOG][n + 1];
        int[] depth = new int[n + 1];
        Node[] que = new Node[n];

        buildParentTable(list, parents, depth, que);

        int[] cnt = new int[n + 1];
        int m = readInt();
        markPaths(parents, depth, cnt, m);

        int[] res = new int[n - 1];
        countEdgeUsage(que, cnt, res, n);

        System.out.println(formatResult(res));
    }

    private static ArrayList<int[]>[] buildGraph(int n) throws IOException {
        ArrayList<int[]>[] list = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < n - 1; i++) {
            int u = readInt();
            int v = readInt();

            list[u].add(new int[]{v, i});
            list[v].add(new int[]{u, i});
        }
        return list;
    }

    private static void buildParentTable(
        ArrayList<int[]>[] list,
        int[][] parents,
        int[] depth,
        Node[] que
    ) {
        int head = 0, tail = 0;
        que[tail++] = new Node(-1, 1, -1);

        while(head < tail) {
            Node cur = que[head++];
            int p = cur.p;
            int v = cur.v;

            for(int[] next : list[v]) {
                if(next[0] == p) continue;

                depth[next[0]] = depth[v] + 1;
                parents[0][next[0]] = v;

                for(int k = 1; k < LOG; k++) {
                    parents[k][next[0]] = parents[k - 1][parents[k - 1][next[0]]];
                }

                que[tail++] = new Node(v, next[0], next[1]);
            }
        }
    }

    private static int findLca(int[][] parents, int[] depth, int a, int b) {
        if(depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        int diff = depth[a] - depth[b];

        for(int k = 0; k < LOG; k++) {
            if((diff & (1 << k)) != 0) {
                a = parents[k][a];
            }
        }

        if(a == b) return a;

        for(int k = LOG - 1; k >= 0; k--) {
            if(parents[k][a] != parents[k][b]) {
                a = parents[k][a];
                b = parents[k][b];
            }
        }

        return parents[0][a];
    }

    private static void markPaths(int[][] parents, int[] depth, int[] cnt, int m) throws IOException {
        for(int i = 0; i < m; i++) {
            int u = readInt();
            int v = readInt();
            int lca = findLca(parents, depth, u, v);

            cnt[u]++;
            cnt[v]++;
            cnt[lca] -= 2;
        }
    }

    private static void countEdgeUsage(Node[] que, int[] cnt, int[] res, int n) {
        for(int i = n - 1; i >= 1; i--) {
            int p = que[i].p;
            int v = que[i].v;
            int edge = que[i].edge;

            res[edge] += cnt[v];
            cnt[p] += cnt[v];
        }
    }

    private static String formatResult(int[] res) {
        StringBuilder sb = new StringBuilder();
        for(int i : res) {
            sb.append(i).append(' ');
        }
        return sb.toString();
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