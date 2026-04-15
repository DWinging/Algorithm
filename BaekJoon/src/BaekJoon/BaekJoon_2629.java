package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_2629 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] weight = new int[n];
        int total = inputWeight(weight, n, br);
        boolean[] dp = getDp(weight, n, total);
        System.out.println(getResult(dp, total, br));
    }

    private static int inputWeight(int[] arr, int n, BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int total = 0;
        for(int i = 0; i < n; i++) {
            int temp = Integer.parseInt(st.nextToken());
            arr[i] = temp;
            total += temp;
        }
        return total;
    }

    private static boolean[] getDp(int[] weight, int n, int total) {
        boolean[][] dp = new boolean[n + 1][total + 1];
        dp[0][0] = true;
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= total; j++) {
                dp[i][j] = dp[i-1][j];
            }

            int w = weight[i-1];
            for(int j = w; j <= total; j++) {
                if(dp[i-1][j-w]) dp[i][j] = true;
            }
        }
        return dp[n];
    }

    private static String getResult(boolean[] dp, int total, BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        while(n-- > 0) {
            int temp = Integer.parseInt(st.nextToken());
            if((temp <= total && dp[temp]) || check(dp, temp)) sb.append("Y");
            else sb.append("N");
            sb.append(" ");
        }
        return sb.toString();
    }

    private static boolean check(boolean[] dp, int temp) {
        for(int i = temp; i < dp.length; i++) {
            if(dp[i] && dp[i-temp]) return true;
        }
        return false;
    }
}
