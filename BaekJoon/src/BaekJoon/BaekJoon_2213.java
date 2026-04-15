package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_2213 {

    final static int ROOT_NODE = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] dp = inputWeight(n, br);
        List<List<Integer>> tree = settingTree(n, br);

        Deque<int[]> order = setOrder(tree, n);
        buildDp(order, dp, n);
        List<Integer> sub = searchNode(tree, dp, n);

        System.out.println(buildString(dp[ROOT_NODE][0], dp[ROOT_NODE][1], sub));
    }

    private static int[][] inputWeight(int n, BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] dp = new int[n + 1][2];
        for(int i = 1; i <= n; i++) {
            dp[i][1] = Integer.parseInt(st.nextToken());
        }
        return dp;
    }

    private static List<List<Integer>> settingTree(int n, BufferedReader br) throws IOException {
        List<List<Integer>> tree = new ArrayList<>();
        for(int i = 0; i<= n; i++) {
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

    private static Deque<int[]> setOrder(List<List<Integer>> tree, int n) {
        Deque<int[]> order = new ArrayDeque<>();
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addLast(ROOT_NODE);
        boolean[] visited = new boolean[n + 1];
        visited[ROOT_NODE] = true;

        while(!deque.isEmpty()) {
            int cur = deque.pollFirst();
            for(int child : tree.get(cur)) {
                if(visited[child]) continue;
                order.addLast(new int[]{cur, child});
                deque.addLast(child);
                visited[child] = true;
            }
        }
        return order;
    }

    private static void buildDp(Deque<int[]> order, int[][] dp, int n) {
        while(!order.isEmpty()) {
            int[] temp = order.pollLast();
            int p = temp[0];
            int child = temp[1];

            dp[p][0] += Math.max(dp[child][0], dp[child][1]);
            dp[p][1] += dp[child][0];
        }
    }

    private static List<Integer> searchNode(List<List<Integer>> tree, int[][] dp, int n) {
        List<Integer> list = new ArrayList<>();
        int state = dp[ROOT_NODE][0] > dp[ROOT_NODE][1] ? 0 : 1;
        Deque<int[]> deque = new ArrayDeque<>();
        deque.addLast(new int[]{ROOT_NODE, state});
        boolean[] visited = new boolean[n + 1];
        visited[ROOT_NODE] = true;

        while(!deque.isEmpty()) {
            int[] temp = deque.pollFirst();
            int cur = temp[0];
            int s = temp[1];
            if(s == 1) list.add(cur);

            for(int child : tree.get(cur)) {
                if(visited[child]) continue;
                state = s == 1 ? 0 : dp[child][0] > dp[child][1] ? 0 : 1;
                deque.addLast(new int[]{child, state});
                visited[child] = true;
            }
        }

        Collections.sort(list);
        return list;
    }

    private static String buildString(int w1, int w2, List<Integer> list) {
        StringBuilder sb = new StringBuilder().append(Math.max(w1, w2)).append("\n");
        for(int i : list) {
            sb.append(i).append(" ");
        }
        return sb.toString();
    }
}
