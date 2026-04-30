package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_2631 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = inputArray(n, br);
        System.out.println(minMoveCount(arr, n));
    }

    private static int[] inputArray(int n, BufferedReader br) throws IOException {
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        return arr;
    }

    private static int minMoveCount(int[] arr, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int cnt = 1;
        for(int i = 1; i < n; i++) {
            int temp = arr[i];
            for(int j = 0; j < i; j++) {
                if(temp > arr[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            cnt = Math.max(cnt, dp[i]);
        }
        return n - cnt;
    }
}
