package BaekJoon;

import java.io.*;

public class BaekJoon_11055 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = settingArray(n, br.readLine());
        int result = increasingPartialSequence(n, arr);
        System.out.println(result);
    }

    private static int[] settingArray(int n, String text) {
        String[] temp = text.split(" ");
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(temp[i]);
        }
        return arr;
    }

    private static int increasingPartialSequence(int n, int[] arr) {
        int[] dp = new int[n];
        int maxNum = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[j], dp[i]);
                }
            }
            dp[i] += arr[i];
            maxNum = Math.max(maxNum, dp[i]);
        }

        return maxNum;
    }
}
