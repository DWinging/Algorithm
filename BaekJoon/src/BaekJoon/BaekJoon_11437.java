package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_11437 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        List<Integer>[] nodes = inputNodes(n, br);

        int maxDepth = (int) Math.ceil(Math.log(n) / Math.log(2.0));
        int root = 1;
        int[][] parents = new int[n + 1][maxDepth];
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

    private static List<Integer>[] inputNodes(int n, BufferedReader br) throws IOException {
        List<Integer>[] nodes = new List[n + 1];
        for(int i = 0; i <= n; i++) nodes[i] = new ArrayList<>();

        StringTokenizer st;
        for(int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            nodes[n1].add(n2);
            nodes[n2].add(n1);
        }
        return nodes;
    }

    private static void settingTree(List<Integer>[] nodes, int[][] parents, int[] depth, int root) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addLast(root);
        depth[root] = 0;
        parents[root][0] = root;

        while(!deque.isEmpty()) {
            int cur = deque.pollFirst();
            int d = depth[cur];
            int p = parents[cur][0];

            for(int next : nodes[cur]) {
                if(next != p) {
                    depth[next] = d + 1;
                    parents[next][0] = cur;
                    deque.addLast(next);
                }
            }
        }
    }

    private static void searchAllAncestor(int[][] parents, int n, int maxDepth) {
        for(int k = 1; k < maxDepth; k++) {
            for(int u = 1; u <= n; u++) {
                int midAncestor = parents[u][k-1];
                parents[u][k] = parents[midAncestor][k-1];
            }
        }
    }

    private static int las(int[][] parents, int[] depth, int maxDepth, int u, int v){
        if(depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        u = settingDepth(parents, depth, u, v, maxDepth);

        if(u == v) return u;

        for(int k = maxDepth-1; k >= 0; k--) {
            if(parents[u][k] != parents[v][k]) {
                u = parents[u][k];
                v = parents[v][k];
            }
        }
        return parents[u][0];
    }

    private static int settingDepth(int[][] parents, int[] depth, int u, int v, int maxDepth) {
        for(int k = maxDepth - 1; k >= 0; k--) {
            if(depth[u] - depth[v] >= (1 << k)) {
                u = parents[u][k];
            }
        }
        return u;
    }
}