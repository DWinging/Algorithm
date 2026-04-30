package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_16947 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<List<Integer>> route = inputRoute(n, br);
        int[] dp = searchGraph(route, n);
        setDp(route, dp);

        System.out.println(buildString(dp));
    }

    private static List<List<Integer>> inputRoute(int n, BufferedReader br) throws IOException {
        List<List<Integer>> route = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            route.add(new ArrayList<>());
        }

        StringTokenizer st;
        while(n-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());

            route.get(r1).add(r2);
            route.get(r2).add(r1);
        }
        return route;
    }

    private static int[] searchGraph(List<List<Integer>> route, int n) {
        int start = 1, end = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addLast(start);
        int[] parents = new int[n + 1];
        parents[start] = -1;

        while(!deque.isEmpty()) {
            int cur = deque.pollLast();

            for(int child : route.get(cur)) {
                if(child == parents[cur]) continue;
                if(parents[child] != 0) {
                    start = child;
                    end = parents[child];
                    parents[child] = cur;
                    deque.clear();
                    break;
                }
                else {
                    deque.addLast(child);
                    parents[child] = cur;
                }
            }
        }

        int[] dp = new int[n + 1];
        while(start != end) {
            dp[start] = -1;
            start = parents[start];
        }
        dp[end] = -1;
        return dp;
    }

    private static void setDp(List<List<Integer>> route, int[] dp) {
        Deque<int[]> deque = new ArrayDeque<>();
        for(int i = 1; i < dp.length; i++) {
            if(dp[i] == -1) deque.addLast(new int[]{i, 0});
        }

        while(!deque.isEmpty()) {
            int[] temp = deque.pollFirst();
            int cur = temp[0];
            int len = temp[1] + 1;

            for(int node : route.get(cur)) {
                if(dp[node] != 0) continue;
                dp[node] = len;
                deque.add(new int[]{node, len});
            }
        }
    }

    private static String buildString(int[] dp) {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < dp.length; i++) {
            sb.append(dp[i] == -1 ? 0 : dp[i]).append(" ");
        }
        return sb.toString();
    }
}
