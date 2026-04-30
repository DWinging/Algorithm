package BaekJoon;

import java.io.*;

public class BaekJoon_10844 {

    final static int MOD = 1_000_000_000;
    final static int CNT = 10;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[][] dp = stepNumList(n);
        long result = getStepNum(n, dp);
        System.out.println(result);
    }

    private static long[][] stepNumList(int n) {
        long[][] dp = new long[n][10];
        for(int i = 1; i < CNT; i++) {
            dp[0][i] = 1;
        }
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < CNT; j++) {
                if(j > 0) dp[i][j] += dp[i-1][j - 1];
                if(j < 9) dp[i][j] += dp[i-1][j + 1];
                dp[i][j] %= MOD;
            }
        }
        return dp;
    }

    private static long getStepNum(int n, long[][] dp) {
        long sum = 0;
        for(int i = 0; i < CNT; i++) {
            sum = (sum + dp[n-1][i]) % MOD;
        }
        return sum;
    }
}
