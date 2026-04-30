package BaekJoon;

import java.io.*;

public class BaekJoon_1915 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);
        String[] arr = inputArray(n, br);
        System.out.println(calculateMaxSize(arr, n, m));
    }

    private static String[] inputArray(int n, BufferedReader br) throws IOException {
        String[] arr = new String[n];
        for(int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }
        return arr;
    }

    private static int calculateMaxSize(String[] arr, int n, int m) {
        int size = 0;
        int[][] dp = new int[n][m];

        for(int y = 0; y < n; y++) {
            for(int x = 0; x < m; x++) {
                if(arr[y].charAt(x) == '0') continue;
                if(y-1 >= 0 && x-1 >= 0) {
                    dp[y][x] = getMinValue(dp[y-1][x], dp[y][x-1], dp[y-1][x-1]);
                }
                dp[y][x] += 1;
                size = Math.max(dp[y][x], size);
            }
        }

        return size * size;
    }

    private static int getMinValue(int dp1, int dp2, int dp3) {
        return Math.min(dp1, Math.min(dp2, dp3));
    }
}
