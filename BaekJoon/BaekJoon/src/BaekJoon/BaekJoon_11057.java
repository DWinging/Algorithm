package BaekJoon;

import java.io.*;

public class BaekJoon_11057 {

    static final int MOD = 10_007;
    static final int CNT = 10;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] dp = settingDP(n);
        int result = sumOf(dp, n);
        System.out.println(result);
    }

    private static int[][] settingDP(int n) {
        int[][] dp = new int[n][CNT];
        for(int i = 0; i < CNT; i++) {
            dp[0][i] = 1;
        }

        for(int i = 1; i < n; i++) {
            dp[i][0] = dp[i-1][0];
            for(int j = 1; j < CNT; j++) {
                dp[i][j] = (dp[i][j-1] + dp[i-1][j]) % MOD;
            }
        }

        return dp;
    }

    private static int sumOf(int[][] dp, int n) {
        int sum = 0;
        for(int i = 0; i < CNT; i++) {
            sum = (sum + dp[n-1][i]) % MOD;
        }
        return sum;
    }
}
