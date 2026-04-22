/**
 * [BOJ] 13976 - 타일 채우기 2
 * - 제출 날짜: 2026년 1월 30일
 * - 결과: 맞았습니다!!
 * - 메모리: 11500 KB
 * - 시간: 64 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {    
    final static long mod = 1_000_000_007;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        System.out.println(solve(n));
    }

    private static long solve(long n) {
        if(n % 2 == 1) return 0;
        if(n == 0) return 1;
        if(n == 2) return 3;
        
        long[][] result = {{1, 0}, {0, 1}};
        long[][] base = {{4, mod -1}, {1, 0}};
        
        long exp = (n / 2) - 1;
        while(exp > 0) {
            if(exp % 2 == 1) result = multiply(result, base);
            base = multiply(base, base);
            exp = exp / 2;
        }

        long ans = ((result[0][0] * 3) % mod + result[0][1]) % mod;
        return (ans + mod) % mod;
    }

    private static long[][] multiply(long[][] arr1, long[][] arr2) {
        int n = arr1.length;
        int m = arr2[0].length;
        int p = arr2.length;
        
        long[][] temp = new long[2][2];
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                for(int k = 0; k < p; k++) {
                    temp[i][j] = (temp[i][j] + (arr1[i][k] * arr2[k][j]) % mod) % mod;
                }
            }
        }
        return temp;
    }
}