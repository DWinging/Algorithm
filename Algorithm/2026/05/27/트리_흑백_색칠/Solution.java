import java.util.*;
import java.io.*;

class Solution {

    static final int MAX_SIZE = 100_000;
    static final int MOD = 1_000_000_007;

    static List<Integer>[] edges = new ArrayList[MAX_SIZE + 1];
    static { for(int i = 1; i <= MAX_SIZE; i++) edges[i] = new ArrayList<>(); }
    static long[][] dp = new long[MAX_SIZE + 1][2];
    static int[] visited = new int[MAX_SIZE + 1];
    
    static int c;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int T = readInt();

        for(int t = 1; t <= T; t++) {
            int n = readInt();

            init(n);
            inputEdges(n);
            dfs(1, t);
            long res = (dp[1][0] + dp[1][1]) % MOD;
            sb.append('#').append(t).append(' ').append(res).append('\n');
        }
        System.out.print(sb);
    }

    private static void init(int n) {
        for(int i = 1; i <= n; i++) {
            edges[i].clear();
            dp[i][0] = 1;
            dp[i][1] = 1;
        }
    }

    private static void inputEdges(int n) throws IOException {
        while(n-- > 1) {
            int v1 = readInt();
            int v2 = readInt();

            edges[v1].add(v2);
            edges[v2].add(v1);
        }
    }

    private static void dfs(int e, int mark) {
        visited[e] = mark;
        for(int child : edges[e]) {
            if(visited[child] == mark) continue;
            dfs(child, mark);
            long white = (dp[child][0] + dp[child][1]) % MOD;
            long black = dp[child][0];
            dp[e][0] = (dp[e][0] * white) % MOD;
            dp[e][1] = (dp[e][1] * black) % MOD;
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
