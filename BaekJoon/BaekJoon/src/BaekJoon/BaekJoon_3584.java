package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_3584 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        while(testCase-- > 0) {
            int n = Integer.parseInt(br.readLine());

            int[] parents = new int[n + 1];
            List<Integer>[] tree = inputTree(parents, n, br);
            int routeNode = getRouteNode(parents);
            int[] level = settingTreeLevel(tree, n, routeNode);

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            bw.write(searchNode(parents, level, n1, n2) + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static List<Integer>[] inputTree(int[] parents, int n, BufferedReader br) throws IOException {
        List<Integer>[] tree = new ArrayList[n + 1];

        for(int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for(int i = 0; i < n-1; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            parents[n2] = n1;
            tree[n1].add(n2);
        }
        return tree;
    }

    private static int getRouteNode(int[] parents) {
        for(int i = 1; i < parents.length; i++) {
            if(parents[i] == 0) return i;
        }
        return 1;
    }

    private static int[] settingTreeLevel(List<Integer>[] tree, int n, int routeNode) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addLast(routeNode);
        int[] level = new int[n + 1];
        level[routeNode] = 0;

        while(!deque.isEmpty()) {
            int cur = deque.pollFirst();
            int l = level[cur];

            for(int next : tree[cur]) {
                deque.addLast(next);
                level[next] = l + 1;
            }
        }
        return level;
    }

    private static int searchNode(int[] parents, int[] level, int n1, int n2) {
        int level1 = level[n1];
        int level2 = level[n2];

        if(level1 > level2) {
            n1 = settingLevel(parents, n1, level1 - level2);
        }
        else if(level1 < level2) {
            n2 = settingLevel(parents, n2, level2 - level1);
        }

        while(n1 != n2) {
            n1 = parents[n1];
            n2 = parents[n2];
        }
        return n1;
    }

    private static int settingLevel(int[] parents, int node, int level) {
        while(level-- > 0) {
            node = parents[node];
        }
        return node;
    }
}
