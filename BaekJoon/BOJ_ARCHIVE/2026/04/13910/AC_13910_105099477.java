/**
 * [BOJ] 13910 - 개업
 * - 제출 날짜: 2026년 4월 20일
 * - 결과: 맞았습니다!!
 * - 메모리: 11544 KB
 * - 시간: 104 ms
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

        boolean[] check = new boolean[n + 1];
        int max_val = inputWoks(n, m, check);
        System.out.println(max_val == n ? 1 : solve(n, max_val, check));
    }

    private static int inputWoks(int n, int m, boolean[] check) throws IOException {
        int[] woks = new int[m];
        for(int i = 0; i < m; i++) {
            woks[i] = readInt();
        }
        
        int max_val = 0;
        for(int i = 0; i < m; i++) {
            int val = woks[i];
            check[val] = true;
            
            if(max_val < val) max_val = val;
            for(int j = i + 1; j < m; j++) {
                int sum_val = val + woks[j];
                if(sum_val > n) continue;

                check[sum_val] = true;
                if(max_val < sum_val) max_val = sum_val;
            }
        }
        return max_val;
    }

    private static int solve(int n, int max_val, boolean[] check) {
        int[] dp = new int[n + 1];
        for(int i = 1; i <= n; i++) dp[i] = n + 5;
        
        for(int i = 1; i <= max_val; i++) {
            if(!check[i]) continue;

            for(int j = i; j <= n; j++) {
                if(dp[j] > dp[j - i] + 1) {
                    dp[j] = dp[j - i] + 1; 
                }
            }
        }

        return dp[n] == n + 5 ? -1 : dp[n];
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