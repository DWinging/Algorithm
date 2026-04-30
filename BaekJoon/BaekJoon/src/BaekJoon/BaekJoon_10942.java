package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_10942 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] num = setInput(n, br);

        int[][] dp = settingDp(n, num);
        int m = Integer.parseInt(br.readLine());
        String result = solve(m, dp, br);
        System.out.println(result);
    }

    private static int[] setInput(int n, BufferedReader br) throws IOException {
        int[] num = new int[n + 1];
        String[] temp = br.readLine().split(" ");
        for(int i = 1; i <= n; i++) {
            num[i] = Integer.parseInt(temp[i - 1]);
        }
        return num;
    }

    private static int[][] settingDp(int n, int[] num){
        int[][] dp = new int[n + 1][n + 1];
        for(int i = 1; i < num.length; i++) {
            for(int j = i; j < num.length; j++) {
                dp[i][j] = isPalindrome(num, i, j);
            }
        }
        return dp;
    }

    private static int isPalindrome(int[] num, int s, int e) {
        while(s < e) {
            if(num[s] != num[e]) return 0;
            s++;
            e--;
        }
        return 1;
    }

    private static String solve(int m, int[][] dp, BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();
        while(m-- > 0) {
            String[] temp = br.readLine().split(" ");
            int s = Integer.parseInt(temp[0]);
            int e = Integer.parseInt(temp[1]);
            sb.append(dp[s][e]).append("\n");
        }
        return sb.toString();
    }
}