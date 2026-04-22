/**
 * [BOJ] 2293 - 동전 1
 * - 제출 날짜: 2026년 2월 20일
 * - 결과: 맞았습니다!!
 * - 메모리: 11440 KB
 * - 시간: 72 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int k = readInt();
        System.out.println(solve(n, k));
    }

    private static int solve(int n, int k) throws IOException {
        int[] dp = new int[k + 1];
        dp[0] = 1;
        
        while(n -- > 0) {
            int coin = readInt();
            for(int i = coin; i <= k; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[k];
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