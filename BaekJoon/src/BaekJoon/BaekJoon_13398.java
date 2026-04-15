package BaekJoon;

import java.io.*;
import java.util.StringTokenizer;

public class BaekJoon_13398 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = inputArray(n, br);
        System.out.println(maxOf(n, arr));
    }

    private static int[] inputArray(int n, BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        return arr;
    }


    private static int maxOf(int n, int[] arr) {
        int[][] dp = new int[n][2];
        dp[0][0] = dp[0][1] = arr[0];
        int value = arr[0];

        for(int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0] + arr[i], arr[i]);
            dp[i][1] = Math.max(dp[i-1][0], dp[i-1][1] + arr[i]);
            value = Math.max(value, Math.max(dp[i][0], dp[i][1]));
        }

        return value;
    }
}
