import java.util.*;
import java.io.*;

class Main {

    static int c;

    static class Node {
        int node;
        long dist;

        Node(int node, long dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        c = System.in.read();

        int n = readInt();
        List<Node>[] arr = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }

        for(int i = 0; i < n - 1; i++) {
            int u = readInt();
            int v = readInt();
            int d = readInt();

            arr[u].add(new Node(v, d));
            arr[v].add(new Node(u, d));
        }

        int[] cnt = new int[n + 1];
        long[] dp = new long[n + 1];

        settingTree(arr, cnt, dp, 0, 1);
        solve(arr, cnt, dp, 1, 1, 0, n);

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            sb.append(dp[i]).append('\n');
        }
        System.out.print(sb);
    }

    private static void settingTree(
        List<Node>[] arr,
        int[] cnt,
        long[] dp,
        int p,
        int node
    ) {
        cnt[node]++;
        for(Node next : arr[node]) {
            int child = next.node;
            long d = next.dist;

            if(child == p) continue;
            settingTree(arr, cnt, dp, node, child);

            cnt[node] += cnt[child];
            dp[node] += dp[child] + d * cnt[child];
        }
    }

    private static void solve(
        List<Node>[] arr,
        int[] cnt,
        long[] dp,
        int p,
        int node,
        long w,
        int n
    ) {
        dp[node] = dp[p] + w * (n - 2 * cnt[node]);

        for(Node next : arr[node]) {
            int child = next.node;
            long d = next.dist;

            if(child == p) continue;
            solve(arr, cnt, dp, node, child, d, n);
        }
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}
