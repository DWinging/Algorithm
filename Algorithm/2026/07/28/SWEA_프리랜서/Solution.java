import java.util.*;
import java.io.*;

class Solution {
    
    static int[][] arr = new int[500][3];
    static int[] dp = new int[500];
    static int c;    
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();

        int T = readInt();
        for(int t = 1; t <= T; t++) {
            int n = readInt();
            int m = readInt();

            Arrays.fill(dp, 0, n, -1);
            inputArray(n);

            int res = 0;
            for (int i = 0; i < n; i++) {
                res = Math.max(res, dfs(i, arr[i][1], n));
            }

            sb.append('#').append(t).append(' ').append(res).append('\n');
        }
        System.out.println(sb);
    }

    private static void inputArray(int n) throws IOException {
        for(int i = 0; i < n; i++) {
            arr[i][0] = readInt();
            arr[i][1] = readInt();
            arr[i][2] = readInt();
        }        

        Arrays.sort(arr, 0, n, (l1, l2) -> {
            if(l1[0] != l2[0]) return l1[0] - l2[0];
            else return l1[1] - l2[1];
        });
    }

    private static int dfs(int idx, int end, int n) {
        if (dp[idx] > -1) return dp[idx];
    
        int cost = 0;
    
        for (int i = idx + 1; i < n; i++) {
            if (arr[i][0] > end) {
                cost = Math.max(cost, dfs(i, arr[i][1], n));
            }
        }
    
        return dp[idx] = cost + arr[idx][2];
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