/**
 * [BOJ] 14722 - 우유 도시
 * - 제출 날짜: 2026년 2월 4일
 * - 결과: 맞았습니다!!
 * - 메모리: 19596 KB
 * - 시간: 156 ms
 */

import java.util.*;
import java.io.*;

import java.io.IOException;

public class Main {
    static int c;
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt() + 1;
        int[] arr = inputArray(n);
        System.out.println(solve(arr, n));
    }

    private static int[] inputArray(int n) throws IOException {
        int[] arr = new int[n * n];
        for(int i = 1; i < n; i++) {
            for(int j = 1; j < n; j++) {
                arr[i * n + j] = readInt();
            }
        }
        return arr;
    }

    private static int solve(int[] arr, int n) {
        int[] dp = new int[n * n];
        int kind = 3;
        for(int i = 0; i < n; i++) {
            arr[i * n] = -1;
            arr[i] = -1;
        }
        for(int y = 1; y < n; y++) {
            for(int x = 1; x < n; x++) {
                int w1 = (arr[(y-1) * n + x] + 1) % kind == arr[y * n + x] ? 1 : 0;
                int n1 = dp[(y - 1) * n + x] + w1;

                int w2 = (arr[y * n + (x-1)] + 1) % kind == arr[y * n + x] ? 1 : 0;
                int n2 = dp[y * n + (x - 1)] + w2;

                if(n1 > n2) {
                    if(w1 == 0) arr[y * n + x] = arr[(y-1) * n + x];
                    dp[y * n + x] = n1;
                }
                else {
                    if(w2 == 0) arr[y * n + x] = arr[y * n + (x - 1)];
                    dp[y * n + x] = n2;
                }
            }
        }

        return dp[(n - 1) * n + (n - 1)];
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
