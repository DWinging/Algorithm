package BaekJoon;

import java.io.*;

public class BaekJoon_7570 {

    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        System.out.println(solve(n));
    }

    private static int solve(int n) throws IOException {
        int[] dp = new int[n + 1];
        int max = 1;
        for(int i = 1; i <= n; i++) {
            int num = readInt();
            dp[num] = dp[num-1] != 0 ? dp[num-1] + 1 : 1;
            max = Math.max(dp[num], max);
        }
        return n - max;
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c - '0');
            c = System.in.read();
        }
        return n;
    }
}
