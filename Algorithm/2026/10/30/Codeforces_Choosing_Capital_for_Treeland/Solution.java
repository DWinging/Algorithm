import java.util.*;
import java.io.*;

class Main {

    static int[] stack;
    static int res = 200_005, top = 0;
    static int c;
    
    public static void main(String[] args) throws IOException {
        int n = readInt();

        List<int[]>[] list = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < n - 1; i++) {
            int u = readInt();
            int v = readInt();

            list[u].add(new int[]{v, 0});
            list[v].add(new int[]{u, 1});
        }

        int[] dp = new int[n + 1];
        dfs(list, dp, -1, 1);

        stack = new int[n + 1];
        solve(list, dp, 0, 1, 0, 0);

        System.out.println(buildString());
    }

    private static int dfs(List<int[]>[] list, int[] dp, int p, int node) {
        for(int[] next : list[node]) {
            if(next[0] == p) continue;
            dp[node] += dfs(list, dp, node, next[0]) + next[1];
        }

        return dp[node];
    }

    private static void solve(List<int[]>[] list, int[] dp, int p, int node, int depth, int val) {
        int temp = depth + dp[1] - val * 2;

        if(res > temp) {
            res = temp;
            top = 0;
            stack[top++] = node;
        } else if(res == temp) {
            stack[top++] = node;
        }
        
        for(int[] next : list[node]) {
            if(next[0] == p) continue;
            solve(list, dp, node, next[0], depth + 1, val + next[1]);
        }
    }

    private static String buildString() {
        StringBuilder sb = new StringBuilder();
        sb.append(res).append('\n');
        
        Arrays.sort(stack, 0, top);
        for(int i = 0; i < top; i++) {
            sb.append(stack[i]).append(' ');
        }        
        return sb.toString();
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