/**
 * [BOJ] 2749 - 피보나치 수 3
 * - 제출 날짜: 2026년 1월 30일
 * - 결과: 맞았습니다!!
 * - 메모리: 11532 KB
 * - 시간: 68 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    final static long mod = 1_000_000;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        System.out.println(solve(n));
    }

    private static long solve(long n) {
        if(n == 0 || n == 1) return n;

        long[][] result = {{1, 0}, {0, 1}};
        long[][] base = {{1, 1}, {1, 0}};

        long steps = n;
        while(steps > 0) {
            if(steps % 2 == 1) result = multiply(result, base);
            base = multiply(base, base);
            steps /= 2;
        }

        return result[0][1];
    }

    private static long[][] multiply(long[][] arr1, long[][] arr2) {
        int n = arr1.length;
        int m = arr2[0].length;
        int p = arr2.length;

        long[][] temp = new long[n][m];
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