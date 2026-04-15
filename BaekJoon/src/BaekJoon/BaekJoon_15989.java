package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_15989 {

    static final int MAX_NUM = 10_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        int[][] dp = settingDp();
        StringBuilder sb = new StringBuilder();
        while(testCase-- > 0) {
            int num = Integer.parseInt(br.readLine());
            sb.append(dp[num][3]).append("\n");
        }
        System.out.println(sb);
    }

    private static int[][] settingDp() {
        int[][] dp = new int[MAX_NUM + 1][4];
        for (int k = 1; k <= 3; k++) {
            dp[0][k] = 1;
        }

        for(int i = 1; i < dp.length; i++) {
            for(int k = 1; k <= 3; k++) {
                dp[i][k] = dp[i][k - 1];
                if(i - k >= 0) {
                    dp[i][k] += dp[i - k][k];
                }
            }
        }
        return dp;
    }
}
