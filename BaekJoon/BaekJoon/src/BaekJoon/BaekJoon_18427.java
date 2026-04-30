package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_18427 {

    final static int MOD = 10_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int[][] blocks = inputBlocks(n, m, br);
        System.out.println(maxOf(blocks, n, m, h));
    }

    private static int[][] inputBlocks(int n, int m, BufferedReader br) throws IOException {
        int[][] blocks = new int[n][m];
        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = 0;
            while(st.hasMoreTokens()) {
                blocks[i][idx++] = Integer.parseInt(st.nextToken());;
            }
        }
        return blocks;
    }

    private static int maxOf(int[][] blocks, int n, int m, int h) {
        int[][] dp = new int[n+1][h+1];
        dp[0][0] = 1;
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= h; j++) {
                dp[i][j] = (dp[i][j] + dp[i-1][j]) % MOD;
            }

            for(int block : blocks[i-1]) {
                if(block == 0) break;
                for(int j = block; j <= h; j++) {
                    dp[i][j] = (dp[i][j] + dp[i-1][j-block]) % MOD;
                }
            }
        }
        return dp[n][h];
    }
}
