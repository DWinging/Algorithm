package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_2565 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] lines = inputLine(n, br);
        System.out.println(searchCuttingLines(lines, n));
    }

    private static int[][] inputLine(int n, BufferedReader br) throws IOException {
        int[][] lines = new int[n][2];
        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            lines[i][0] = Integer.parseInt(st.nextToken());
            lines[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(lines, (l1, l2) -> Integer.compare(l1[0], l2[0]));
        return lines;
    }

    private static int searchCuttingLines(int[][] lines, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int cnt = 1;
        for(int i = 1; i < n; i++) {
            int line = lines[i][1];
            for(int j = 0; j < i; j++) {
                if(lines[j][1] < line) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            cnt = Math.max(dp[i], cnt);
        }
        return n - cnt;
    }
}
