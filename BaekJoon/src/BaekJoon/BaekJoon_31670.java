package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_31670 {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long[][] dp = new long[n + 1][2];
        for(int i = 1; i <= n; i++) {
            dp[i][0] = dp[i-1][1];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][1]) + arr[i];
        }

        System.out.println(Math.min(dp[n][0], dp[n][1]));
    }
}
