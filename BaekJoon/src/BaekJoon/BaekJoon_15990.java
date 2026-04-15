package BaekJoon;

import java.io.*;

public class BaekJoon_15990 {

    final static int MOD = 1_000_000_009;
    final static int RANGE = 100_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] dp = setDp();
        System.out.println(getResult(br, dp));
    }

    private static int[][] setDp() {
        int[][] dp = new int[RANGE + 1][4];
        dp[1][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = dp[3][2] = dp[3][3] = 1;

        for(int i = 4; i <= RANGE; i++) {
            for(int j = 1; j <= 3; j++) {
                for(int k = 1; k <= 3; k++) {
                    if(j == k) continue;
                    dp[i][j] = (dp[i][j] + dp[i - j][k]) % MOD;
                }
            }
        }
        return dp;
    }

    private static String getResult(BufferedReader br, int[][] dp) throws IOException{
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());
        while(testCase-- > 0) {
            int n = Integer.parseInt(br.readLine());
            sb.append(sumOf(dp[n])).append("\n");
        }
        return sb.toString();
    }

    private static int sumOf(int[] list) {
        int sum = 0;
        for(int i : list ){
            sum = (sum + i) % MOD;
        }
        return sum;
    }
}
