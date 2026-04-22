/**
 * [BOJ] 11909 - 배열 탈출
 * - 제출 날짜: 2026년 4월 19일
 * - 결과: 맞았습니다!!
 * - 메모리: 50800 KB
 * - 시간: 532 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

    final static int INF = 300;
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
        int[][] dp = new int[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int val = arr[i][j];
                if(i == 0 && j == 0) continue;
                else if(i == 0) {
                    dp[i][j] = (arr[i][j - 1] > val ? 0 : val + 1 - arr[i][j - 1]) + dp[i][j - 1];
                } else if(j == 0) {
                    dp[i][j] = (arr[i-1][j] > val ? 0 : val + 1 - arr[i-1][j]) + dp[i - 1][j];
                } else {
                    int down = (arr[i-1][j] > val ? 0 : val + 1 - arr[i-1][j]) + dp[i - 1][j];
                    int right = (arr[i][j - 1] > val ? 0 : val + 1 - arr[i][j - 1]) + dp[i][j - 1];
                    dp[i][j] = down > right ? right : down;
                }
            }
        }

        return dp[n - 1][n - 1];
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
