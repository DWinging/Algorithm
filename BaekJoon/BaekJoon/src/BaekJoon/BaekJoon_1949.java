package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_1949 {

    final static int ROOT_NODE = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] peoples = inputPeople(n, br);
        List<List<Integer>> tree = settingTree(n, br);
        Deque<int[]> order = settingOrder(tree, n);
        System.out.println(maxOf(order, peoples, n));
    }

    private static int[] inputPeople(int n, BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] peoples = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            peoples[i] = Integer.parseInt(st.nextToken());
        }
        return peoples;
    }

    private static List<List<Integer>> settingTree(int n, BufferedReader br) throws IOException {
        List<List<Integer>> tree = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }

        StringTokenizer st;
        while(n-- > 1) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            tree.get(n1).add(n2);
            tree.get(n2).add(n1);
        }
        return tree;
    }

    private static Deque<int[]> settingOrder(List<List<Integer>> tree, int n) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addLast(ROOT_NODE);
        Deque<int[]> order = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        visited[ROOT_NODE] = true;

        while(!deque.isEmpty()) {
            int cur = deque.pollFirst();
            for(int node : tree.get(cur)) {
                if(visited[node]) continue;
                order.addLast(new int[]{cur, node});
                deque.addLast(node);
                visited[node] = true;
            }
        }
        return order;
    }

    private static int maxOf(Deque<int[]> order, int[] peoples, int n) {
        int[] dp = new int[n + 1];
        while(!order.isEmpty()) {
            int[] temp = order.pollLast();
            int p = temp[0];
            int child = temp[1];

            dp[p] += Math.max(dp[child], peoples[child]);
            peoples[p] += dp[child];
        }
        return Math.max(dp[ROOT_NODE], peoples[ROOT_NODE]);
    }
}
