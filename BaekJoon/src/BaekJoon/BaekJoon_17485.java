package BaekJoon;

import java.io.*;
import java.util.StringTokenizer;

public class BaekJoon_17485 {

    final static int MAX_VALUE = 100_001;
    final static int DICT = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] cost = new int[n][m];
        inputCost(n, m, cost, br);
        int[][][] dp = settingDp(n, m);
        calculateCost(n, m, cost, dp);
        System.out.println(getTotalCost(dp, n, m));
    }

    private static void inputCost(int n, int m, int[][] cost, BufferedReader br) throws IOException {
        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static int[][][] settingDp(int n, int m) {
        int[][][] dp = new int[n][m][DICT];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                for(int d = 0; d < DICT; d++) {
                    dp[i][j][d] = MAX_VALUE;
                }
            }
        }
        return dp;
    }

    private static void calculateCost(int n, int m, int[][] cost, int[][][] dp) {
        for(int i = 0; i < m; i++) {
            for(int d = 0; d < DICT; d++) {
                dp[0][i][d] = cost[0][i];
            }
        }

        int temp = 0;
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < m; j++) {
                temp = cost[i][j];
                if(j == 0) {
                    dp[i][j][1] = temp + dp[i-1][j][2];
                    dp[i][j][2] = temp + Math.min(dp[i-1][j+1][0], dp[i-1][j+1][1]);
                }
                else if(j == m-1) {
                    dp[i][j][0] = temp + Math.min(dp[i-1][j-1][1], dp[i-1][j-1][2]);
                    dp[i][j][1] = temp + dp[i-1][j][0];
                }
                else {
                    dp[i][j][0] = temp + Math.min(dp[i-1][j-1][1], dp[i-1][j-1][2]);
                    dp[i][j][1] = temp + Math.min(dp[i-1][j][0], dp[i-1][j][2]);
                    dp[i][j][2] = temp + Math.min(dp[i-1][j+1][0], dp[i-1][j+1][1]);
                }
            }
        }
    }

    private static int getTotalCost(int[][][] dp, int n, int m) {
        int cost = MAX_VALUE;
        for(int j = 0; j < m; j++) {
            for(int d = 0; d < DICT; d++) {
                cost = Math.min(cost, dp[n-1][j][d]);
            }
        }
        return cost;
    }
}
