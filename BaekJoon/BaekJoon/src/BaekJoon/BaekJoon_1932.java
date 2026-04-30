package BaekJoon;

import java.io.*;

public class BaekJoon_1932 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = getLastStepDp(n, br);
        System.out.println(maxOf(dp));
    }

    private static int[] getLastStepDp(int n, BufferedReader br) throws IOException {
        int[] dp = new int[n];
        for(int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
            for(int j = i; j >= 0; j--) {
                int num = Integer.parseInt(temp[j]);
                if(j == 0) dp[j] += num;
                else if(j == i) dp[j] = num + dp[j-1];
                else dp[j] = num + Math.max(dp[j-1], dp[j]);
            }
        }
        return dp;
    }

    private static int maxOf(int[] dp) {
        int value = 0;
        for(int sum : dp) {
            value = Math.max(value, sum);
        }
        return value;
    }
}
