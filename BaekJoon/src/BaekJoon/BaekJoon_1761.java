package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_1761 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        List<int[]>[] nodes = inputNode(n, br);

        int maxDepth = (int)Math.ceil(Math.log(n) / Math.log(2.0));
        int root = 1;
        int[][][] parents = new int[n + 1][maxDepth][2];
        int[] depth = new int[n + 1];

        settingTree(nodes, parents, depth, root);
        searchAllAncestor(parents, n, maxDepth);

        int m = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            bw.write(las(parents, depth, maxDepth, u, v) + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static List<int[]>[] inputNode(int n, BufferedReader br) throws IOException {
        List<int[]>[] nodes = new List[n + 1];
        for(int i = 0; i <= n; i++) {
            nodes[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for(int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            nodes[u].add(new int[]{v, w});
            nodes[v].add(new int[]{u, w});
        }
        return nodes;
    }

    private static void settingTree(List<int[]>[] nodes, int[][][] parents, int[] depth, int root) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addLast(root);
        parents[root][0][0] = root;

        while(!deque.isEmpty()) {
            int cur = deque.pollFirst();
            int d = depth[cur];
            int p = parents[cur][0][0];

            for(int[] node : nodes[cur]) {
                int next = node[0];
                int weight = node[1];
                if(next != p) {
                    depth[next] = d + 1;
                    parents[next][0][0] = cur;
                    parents[next][0][1] = weight;
                    deque.addLast(next);
                }
            }
        }
    }

    private static void searchAllAncestor(int[][][] parents, int n, int maxDepth) {
        for(int k = 1; k < maxDepth; k++) {
            for(int u = 1; u <= n; u++) {
                int midAncestor = parents[u][k-1][0];
                parents[u][k][0] = parents[midAncestor][k-1][0];
                parents[u][k][1] = parents[u][k-1][1] + parents[midAncestor][k-1][1];
            }
        }
    }

    private static int las(int[][][] parents, int[] depth, int maxDepth, int u, int v){
        if(depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int totalDistance = 0;
        for(int k = maxDepth - 1; k >= 0; k--) {
            if(depth[u] - depth[v] >= (1 << k)) {
                totalDistance += parents[u][k][1];
                u = parents[u][k][0];
            }
        }

        if(u == v) {
            return totalDistance;
        }

        for(int k = maxDepth-1; k >= 0; k--) {
            if(parents[u][k][0] != parents[v][k][0]) {
                totalDistance += parents[u][k][1];
                totalDistance += parents[v][k][1];

                u = parents[u][k][0];
                v = parents[v][k][0];
            }
        }

        totalDistance += parents[u][0][1];
        totalDistance += parents[v][0][1];

        return totalDistance;
    }
}
