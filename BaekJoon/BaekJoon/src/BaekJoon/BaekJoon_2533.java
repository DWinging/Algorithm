package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_2533 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<List<Integer>> tree = inputTree(n, br);
        System.out.println(countEarlyAdaptor(tree, n));
    }

    private static List<List<Integer>> inputTree(int n, BufferedReader br) throws IOException {
        List<List<Integer>> tree = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }

        StringTokenizer st;
        for(int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            tree.get(n1).add(n2);
            tree.get(n2).add(n1);
        }
        return tree;
    }

    private static int countEarlyAdaptor(List<List<Integer>> tree, int n) {
        int[][] dp = new int[n + 1][2];
        dp[1][1] = 1;

        boolean[] visited = new boolean[n + 1];
        Deque<int[]> order = new ArrayDeque<>();
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addLast(1);
        visited[1] = true;
        while(!deque.isEmpty()) {
            int cur = deque.pollFirst();
            for(int i : tree.get(cur)) {
                if(!visited[i])  {
                    order.addLast(new int[]{cur, i});
                    deque.addLast(i);
                    visited[i] = true;
                    dp[i][1] = 1;
                }
            }
        }

        while(!order.isEmpty()) {
            int[] temp = order.pollLast();
            int p = temp[0];
            int node = temp[1];

            dp[p][0] += dp[node][1];
            dp[p][1] += Math.min(dp[node][0], dp[node][1]);
        }

        return Math.min(dp[1][0], dp[1][1]);
    }
}
