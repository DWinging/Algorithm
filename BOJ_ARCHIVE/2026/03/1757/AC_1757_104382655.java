/**
 * [BOJ] 1757 - 달려달려
 * - 제출 날짜: 2026년 3월 27일
 * - 결과: 맞았습니다!!
 * - 메모리: 31708 KB
 * - 시간: 140 ms
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
        int[][] dp = new int[m + 1][n + 1];
        System.out.println(solve(dp, n, m));
    }

    private static int solve(int[][] dp, int n, int m) throws IOException {
        for(int i = 0; i < m; i++) dp[i + 1][i] = -1;
        for(int col = 1; col <= n; col++) {
            int val = readInt();
            dp[0][col] = Math.max(dp[0][col], dp[0][col - 1]);
            for(int row = 1; row <= m; row++) {
                if(dp[row-1][col-1] == -1) break;
                dp[row][col] = dp[row-1][col-1] + val;
                if(col + row <= n) {
                    dp[0][col + row] = Math.max(dp[0][col + row], dp[row][col]);
                }
            }
        }
        
        return dp[0][n];
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}