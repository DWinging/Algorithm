package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_2233 {

    static int[][] nodes;
    static int[] parents;
    static int[] depths;
    static int n1, n2, x, y, idx = 0, node = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        nodes = new int[n + 1][2];
        parents = new int[n + 1];
        depths = new int[n + 1];
        String tree = br.readLine();

        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken()) - 1;
        y = Integer.parseInt(st.nextToken()) - 1;

        dfs(tree, -1, 0);
        searchLCA();
        System.out.println((nodes[n1][0] + 1) + " " + (nodes[n1][1] + 1));
    }

    private static void dfs(String tree, int prev, int d) {
        int cur = node++;
        if(idx == x) n1 = cur;
        if(idx == y) n2 = cur;
        nodes[cur][0] = idx++;
        parents[cur] = prev;
        depths[cur] = d;

        while(tree.charAt(idx) == '0') dfs(tree, cur,d + 1);

        if(idx == x) n1 = cur;
        if(idx == y) n2 = cur;
        nodes[cur][1] = idx++;
    }

    private static void searchLCA() {
        while(depths[n1] != depths[n2]) {
            if(depths[n1] > depths[n2]) {
                n1 = parents[n1];
            }
            else {
                n2 = parents[n2];
            }
        }

        while(n1 != n2) {
            n1 = parents[n1];
            n2 = parents[n2];
        }
    }
}
