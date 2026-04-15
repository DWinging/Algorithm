package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_11048 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);

        int[][] candies = new int[n + 1][m + 1];
        for(int i = 1; i <= n; i++) {
            temp = br.readLine().split(" ");
            for(int j = 1; j <= m; j++) {
                int candyStack = Math.max(Math.max(candies[i - 1][j], candies[i][j - 1]), candies[i - 1][j - 1]);
                candies[i][j] = candyStack + Integer.parseInt(temp[j - 1]);
            }
        }

        System.out.println(candies[n][m]);
    }
}
