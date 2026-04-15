package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_17845 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] arr = inputArray(k, br);
        System.out.println(maxOf(arr, n, k));
    }

    private static int[][] inputArray(int k, BufferedReader br) throws IOException {
        StringTokenizer st;
        int[][] arr = new int[k][2];
        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        return arr;
    }

    private static int maxOf(int[][] arr, int n, int k) {
        int[] dp = new int[n + 1];
        for(int i = 0; i < k; i++) {
            int weight = arr[i][0];
            int time = arr[i][1];
            for(int j = n; j >= time; j--) {
                dp[j] = Math.max(dp[j], weight + dp[j - time]);
            }
        }
        return dp[n];
    }
}