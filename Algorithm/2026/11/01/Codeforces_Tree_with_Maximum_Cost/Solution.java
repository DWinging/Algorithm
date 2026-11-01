import java.util.*;
import java.io.*;

class Main {

    static long res = 0;
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();

        int n = readInt();
        
        List<Integer>[] list = new ArrayList[n + 1];
        int[] cost = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
            cost[i] = readInt();
        }

        for(int i = 0; i < n - 1; i++) {
            int u = readInt();
            int v = readInt();

            list[u].add(v);
            list[v].add(u);
        }

        long[] total = new long[n + 1];
        long[] val = new long[n + 1];
        dfs(list, total, val, cost, 0, 1);
        solve(list, total, val, cost, 0, 1, 0);

        System.out.println(res);
    }

    private static long dfs(List<Integer>[] list, long[] total, long[] val, int[] cost, int p, int node) {
        for(int child : list[node]) {
            if(child == p) continue;
            val[node] += dfs(list, total, val, cost, node, child);
            total[node] += total[child];            
        }

        total[node] += cost[node];
        return val[node] + total[node];
    }

    private static void solve(List<Integer>[] list, long[] total, long[] val, int[] cost, int p, int node, long diff) {
        res = Math.max(res, val[1] + diff);

        for (int child : list[node]) {
            if (child == p) continue;
    
            long next = diff + total[1] - 2 * total[child];
            solve(list, total, val, cost, node, child, next);
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