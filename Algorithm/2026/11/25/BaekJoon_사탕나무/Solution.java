import java.util.*;
import java.io.*;

class Main {

    static List<Integer>[] list;
    static int[][] dp;
    
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();

        int n = readInt();
        int k = readInt();

        list = new ArrayList[n + 1];
        dp = new int[n + 1][k + 1];

        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < n - 1; i++) {
            int u = readInt();
            int v = readInt();

            list[u].add(v);
            list[v].add(u);
        }

        settingTree(n, k, 1, 0);
        downTree(n, k, 1, 0);
        System.out.println(solve(n, k));
    }

    private static void settingTree(int n, int k, int node, int p) {
        dp[node][0] = 1;
        for(int child : list[node]) {
            if(child == p) continue;

            settingTree(n, k, child, node);
            for(int i = 1; i <= k; i++) {
                dp[node][i] += dp[child][i - 1];
            }
        }
    }

    private static void downTree(int n, int k, int node, int p) {
        if(p != 0) {
            for(int i = k; i >= 2; i--) {
                dp[node][i] += dp[p][i - 1] - dp[node][i - 2];
            }
    
            if(k >= 1) dp[node][1]++;
        }
        
        for(int child : list[node]) {
            if(child == p) continue;
            downTree(n, k, child, node);
        }
    }

    private static int solve(int n, int k) {
        int res = 0;
        for(int i = 1; i <= n; i++) {
            int sum = 0;
            for(int j = 0; j <= k; j++) {
                sum += dp[i][j];
            }
            if(sum > res) res = sum;
        }
        return res;
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