import java.util.*;
import java.io.*;

class Main {

    static final int LOG = 18;

    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();

        int n = readInt();
        List<Integer>[] list = new ArrayList[n + 1];
        inputList(list, n);

        int[][] parents = new int[LOG][n + 1];
        int[] depth = new int[n + 1];
        buildTree(list, parents, depth, n, 1);

        System.out.println(solve(parents, depth));
    }

    private static void inputList(List<Integer>[] list, int n) throws IOException {
        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < n - 1; i++) {
            int u = readInt();
            int v = readInt();

            list[u].add(v);
            list[v].add(u);
        }
    }

    private static void buildTree(List<Integer>[] list, int[][] parents, int[] depth, int n, int root) {
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{-1, root});

        while(!que.isEmpty()) {
            int[] cur = que.poll();
            int p = cur[0];
            int node = cur[1];

            for(int child : list[node]) {
                if(child == p) continue;

                depth[child] = depth[node] + 1;
                parents[0][child] = node;

                for(int k = 1; k < LOG; k++) {
                    parents[k][child] = parents[k - 1][parents[k - 1][child]];
                }

                que.add(new int[]{node, child});
            }
        }
    }

    private static String solve(int[][] parents, int[] depth) throws IOException {
        StringBuilder sb = new StringBuilder();
        int m = readInt();
        while(m-- > 0) {
            int x = readInt();
            int y = readInt();
            int a = readInt();
            int b = readInt();
            int k = readInt();

            int d1 = findLca(parents, depth, a, b);
            int d2 = findLca(parents, depth, a, x) + 1 + findLca(parents, depth, b, y);
            int d3 = findLca(parents, depth, a, y) + 1 + findLca(parents, depth, b, x);

            if(check(d1, k) || check(d2, k) || check(d3, k)) {
                sb.append("YES").append('\n');
            } else {
                sb.append("NO").append('\n');
            }
        }

        return sb.toString();
    }

    private static int findLca(int[][] parents, int[] depth, int a, int b) {
        if(depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        int originA = a;
        int originB = b;
        int diff = depth[a] - depth[b];

        for(int k = 0; k < LOG; k++) {
            if((diff & (1 << k)) != 0) {
                a = parents[k][a];
            }
        }

        if(a == b) return depth[originA] - depth[a];

        for(int k = LOG - 1; k >= 0; k--) {
            if(parents[k][a] != parents[k][b]) {
                a = parents[k][a];
                b = parents[k][b];
            }
        }

        int lca = parents[0][a];
        return depth[originA] + depth[originB] - 2 * depth[lca];
    }

    private static boolean check(int d, int k) {
        return d <= k && (((k - d) & 1) == 0);
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