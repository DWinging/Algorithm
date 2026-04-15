package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_4095 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        while(true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if(n == 0 && m == 0) break;
            int[][] arr = inputArray(n, m, br);
            bw.write(calculateMaxSquare(arr, n, m) + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static int[][] inputArray(int n, int m, BufferedReader br) throws IOException {
        int[][] arr = new int[n][m];
        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }
        return arr;
    }

    private static int calculateMaxSquare(int[][] arr, int n, int m) {
        int[][] dp = new int[n][m];
        int maxRange = 0;
        for(int y = 0; y < n; y++) {
            for(int x = 0; x < m; x++) {
                if(arr[y][x] == 0) continue;
                if(y - 1 >= 0 && x - 1 >= 0) {
                    dp[y][x] = Math.min(dp[y-1][x], Math.min(dp[y][x-1], dp[y-1][x-1]));
                }
                dp[y][x] += 1;
                maxRange = Math.max(dp[y][x], maxRange);
            }
        }
        return maxRange;
    }
}
