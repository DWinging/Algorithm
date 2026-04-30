/**
 * [BOJ] 10830 - 행렬 제곱
 * - 제출 날짜: 2026년 1월 30일
 * - 결과: 맞았습니다!!
 * - 메모리: 11484 KB
 * - 시간: 56 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    static int c;
    static int MOD = 1_000;
    
    public static void main(String[] args) throws IOException {
        int n = System.in.read() - '0';
        long m = readLong();
        
        long[][] result = new long[n][n];
        long[][] base = new long[n][n];

        for(int i = 0; i < n; i++) result[i][i] = 1;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                long value = readLong() % MOD;
                base[i][j] = value;
            }
        }

        result = solve(result, base, m);
        System.out.println(buildString(result));
    }
    
    private static long readLong() throws IOException {
        c = System.in.read();
        while(c <= ' ') {
            c = System.in.read();
        }

        long n = 0;
        while(c >= '0' && c <= '9') {
            n = (n * 10) + (c - '0');
            c = System.in.read();
        }
        return n;
    }

    private static long[][] solve(long[][] result, long[][] base, long exp) {
        while(exp > 0) {
            if(exp % 2 == 1) result = multiply(result, base);
            base = multiply(base, base);
            exp /= 2;
        }
        return result;
    }

    private static long[][] multiply(long[][] arr1, long[][] arr2) {
        int n = arr1.length;
        int m = arr2[0].length;
        int p = arr2.length;

        long[][] temp = new long[n][m];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                for(int k = 0; k < p; k++) {
                    temp[i][j] = (temp[i][j] + (arr1[i][k] * arr2[k][j]) % MOD) % MOD;
                }
            }
        }
        return temp;
    }

    private static String buildString(long[][] result) {
        StringBuilder sb = new StringBuilder();
        for(long[] r : result) {
            for(long c : r) {
                sb.append(c).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}