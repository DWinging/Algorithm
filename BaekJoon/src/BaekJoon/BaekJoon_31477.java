package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_31477 {

    final static int ROOT_NODE = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<List<int[]>> tree = settingTree(n, br);
        Deque<int[]> order = setOrder(tree, n);
        System.out.println(getMinWeight(order, n));
    }

    private static List<List<int[]>> settingTree(int n, BufferedReader br) throws IOException {
        List<List<int[]>> tree = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }

        StringTokenizer st;
        while(n-- > 1) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            tree.get(n1).add(new int[]{n2, w});
            tree.get(n2).add(new int[]{n1, w});
        }
        return tree;
    }

    private static Deque<int[]> setOrder(List<List<int[]>> tree, int n) {
        boolean[] visited = new boolean[n + 1];
        visited[ROOT_NODE] = true;
        Deque<int[]> order = new ArrayDeque<>();
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addLast(ROOT_NODE);

        while(!deque.isEmpty()) {
            int cur = deque.pollFirst();
            for(int[] temp : tree.get(cur)) {
                int child = temp[0];
                if(visited[child]) continue;

                order.addLast(new int[] {cur, child, temp[1]});
                deque.addLast(child);
                visited[child] = true;
            }
        }
        return order;
    }

    private static int getMinWeight(Deque<int[]> order, int n) {
        int[] dp = new int[n + 1];
        while(!order.isEmpty()) {
            int[] temp = order.pollLast();
            int p = temp[0];
            int cur = temp[1];
            int weight = temp[2];

            dp[p] += dp[cur] == 0 ? weight : Math.min(weight, dp[cur]);
        }
        return dp[ROOT_NODE];
    }
}
