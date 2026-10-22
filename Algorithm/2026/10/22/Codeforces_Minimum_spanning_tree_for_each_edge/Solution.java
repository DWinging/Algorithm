import java.util.*;
import java.io.*;

class Main {

    static final int LOG = 19;

    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();

        int n = readInt();
        int m = readInt();

        ArrayList<int[]>[] list = new ArrayList[n + 1];
        int[][] edge = new int[m][3];
        int[] parents = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
            parents[i] = i;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[2], b[2])
        );

        for (int i = 0; i < m; i++) {
            int u = readInt();
            int v = readInt();
            int w = readInt();

            edge[i][0] = u;
            edge[i][1] = v;
            edge[i][2] = w;

            pq.add(new int[]{u, v, w});
        }

        long weight = kruskal(list, pq, parents, n);

        int[][] up = new int[LOG][n + 1];
        int[][] maxWeight = new int[LOG][n + 1];
        int[] depth = new int[n + 1];

        buildTree(up, maxWeight, depth, list, 1);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            int a = edge[i][0];
            int b = edge[i][1];
            int w = edge[i][2];

            int max = findLca(up, maxWeight, depth, a, b);

            long res = weight - max + w;
            sb.append(res).append('\n');
        }

        System.out.print(sb);
    }

    private static long kruskal(
        ArrayList<int[]>[] list,
        PriorityQueue<int[]> pq,
        int[] parents,
        int n
    ) {
        long sum = 0;
        int cnt = 0;

        while (!pq.isEmpty() && cnt < n - 1) {
            int[] cur = pq.poll();
            int a = cur[0];
            int b = cur[1];
            int w = cur[2];

            if (union(parents, a, b)) {
                list[a].add(new int[]{b, w});
                list[b].add(new int[]{a, w});

                sum += w;
                cnt++;
            }
        }

        return sum;
    }

    private static boolean union(int[] parents, int a, int b) {
        int pA = find(parents, a);
        int pB = find(parents, b);

        if (pA == pB) return false;

        parents[pB] = pA;
        return true;
    }

    private static int find(int[] parents, int p) {
        if (parents[p] == p) return p;
        return parents[p] = find(parents, parents[p]);
    }

    private static void buildTree(
        int[][] up,
        int[][] maxWeight,
        int[] depth,
        ArrayList<int[]>[] list,
        int start
    ) {
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{start, 0});

        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int node = cur[0];
            int p = cur[1];

            for (int[] child : list[node]) {
                int temp = child[0];
                int w = child[1];

                if (temp == p) continue;

                depth[temp] = depth[node] + 1;

                up[0][temp] = node;
                maxWeight[0][temp] = w;

                for (int k = 1; k < LOG; k++) {
                    int mid = up[k - 1][temp];

                    up[k][temp] = up[k - 1][mid];

                    maxWeight[k][temp] = Math.max(
                        maxWeight[k - 1][temp],
                        maxWeight[k - 1][mid]
                    );
                }

                que.add(new int[]{temp, node});
            }
        }
    }

    private static int findLca(
        int[][] up,
        int[][] maxWeight,
        int[] depth,
        int a,
        int b
    ) {
        int max = 0;

        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        int diff = depth[a] - depth[b];

        for (int k = LOG - 1; k >= 0; k--) {
            if ((diff & (1 << k)) != 0) {
                max = Math.max(max, maxWeight[k][a]);
                a = up[k][a];
            }
        }

        if (a == b) return max;

        for (int k = LOG - 1; k >= 0; k--) {
            if (up[k][a] != up[k][b]) {
                max = Math.max(max, maxWeight[k][a]);
                max = Math.max(max, maxWeight[k][b]);

                a = up[k][a];
                b = up[k][b];
            }
        }

        max = Math.max(max, maxWeight[0][a]);
        max = Math.max(max, maxWeight[0][b]);

        return max;
    }

    private static int readInt() throws IOException {
        while (c <= ' ') c = System.in.read();

        int n = 0;

        while (c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }

        return n;
    }
}
