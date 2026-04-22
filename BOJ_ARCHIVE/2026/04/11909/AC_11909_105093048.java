/**
 * [BOJ] 11909 - 배열 탈출
 * - 제출 날짜: 2026년 4월 19일
 * - 결과: 맞았습니다!!
 * - 메모리: 31344 KB
 * - 시간: 428 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int[][] arr = inputArray(n);
        System.out.println(solve(arr, n));
    }

    private static int[][] inputArray(int n) throws IOException {
        int[][] arr = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                arr[i][j] = readInt();
            }
        }
        return arr;
    }

    private static int solve(int[][] arr, int n) {
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int val = arr[i][j];

                if (i == 0 && j == 0) {
                    dp[j] = 0;
                    continue;
                }

                if (i == 0) {
                    dp[j] = (arr[i][j - 1] > val ? 0 : val + 1 - arr[i][j - 1]) + dp[j - 1];
                } else if (j == 0) {
                    dp[j] = (arr[i - 1][j] > val ? 0 : val + 1 - arr[i - 1][j]) + dp[j];
                } else {
                    int down = (arr[i - 1][j] > val ? 0 : val + 1 - arr[i - 1][j]) + dp[j];
                    int right = (arr[i][j - 1] > val ? 0 : val + 1 - arr[i][j - 1]) + dp[j - 1];
                    dp[j] = down > right ? right : down;
                }
            }
        }

        return dp[n - 1];
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