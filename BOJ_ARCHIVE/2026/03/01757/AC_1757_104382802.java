/**
 * [BOJ] 1757 - 달려달려
 * - 제출 날짜: 2026년 3월 27일
 * - 결과: 맞았습니다!!
 * - 메모리: 12116 KB
 * - 시간: 100 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();
        
        int[] sum = getPrefixSum(n);
        System.out.println(solve(n, m, sum));
    }

    private static int[] getPrefixSum(int n) throws IOException {
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + readInt();
        }
        return sum;
    }

    private static int solve(int n, int m, int[] sum) {
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];
            
            int limit = Math.min(m, i / 2);
            for (int j = 1; j <= limit; j++) {
                int runDist = sum[i - j] - sum[i - 2 * j];
                int totalDist = dp[i - 2 * j] + runDist;
                
                if (dp[i] < totalDist) {
                    dp[i] = totalDist;
                }
            }
        }
        return dp[n];
    }

    private static int readInt() throws IOException {
        while (c <= ' ') c = System.in.read();
        int n = 0;
        while (c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}