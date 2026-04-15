package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_2240 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] dp = new int[k+1][t+1];
        calculateTree(dp, t, k, br);
        System.out.println(maxOf(dp, t, k));
    }

    private static void calculateTree(int[][] dp, int t, int k, BufferedReader br) throws IOException {
        for(int i = 1; i <= t; i++) {
            int tree = Integer.parseInt(br.readLine());
            for(int j = 0; j <= Math.min(i, k); j++) {
                if(j % 2 != tree % 2) {
                    dp[j][i] = j == 0 ? dp[j][i-1] + 1 : Math.max(dp[j-1][i-1], dp[j][i-1]) + 1;
                }
                else {
                    dp[j][i] = dp[j][i-1];
                }
            }
        }
    }

    private static int maxOf(int[][] dp, int t, int k) {
        int value = 0;
        for(int i = 0; i <= k; i++) {
            value = Math.max(value, dp[i][t]);
        }
        return value;
    }
}
