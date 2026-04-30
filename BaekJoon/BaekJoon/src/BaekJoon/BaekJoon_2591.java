package BaekJoon;

import java.io.*;

public class BaekJoon_2591 {

    final static int MAX_RANGE = 34;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String arr = br.readLine();
        int[] dp = new int[arr.length()];
        System.out.println(calculateArray(dp, arr, 0));
    }

    private static int calculateArray(int[] dp, String arr, int idx) {
        if(idx + 1 < arr.length()) dp[idx] = calculateArray(dp, arr, idx + 1);
        if(arr.charAt(idx) == '0') { return dp[idx] = 0; }
        if(idx == arr.length() - 1) return dp[idx] = 1;

        if(Integer.parseInt(arr.substring(idx, idx + 2)) <= MAX_RANGE) {
            if(idx + 1 == arr.length()-1) {
                dp[idx]++;
            }
            else {
                dp[idx] += dp[idx + 2];
            }
        }
        return dp[idx];
    }
}