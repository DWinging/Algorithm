/**
 * [BOJ] 11909 - 배열 탈출
 * - 제출 날짜: 2026년 4월 19일
 * - 결과: 맞았습니다!!
 * - 메모리: 31284 KB
 * - 시간: 260 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

    private static final int BUFFER_SIZE = 1 << 16;
    private static byte[] buffer = new byte[BUFFER_SIZE];
    private static int bufferPointer = 0, bytesRead = 0;
    private static InputStream in = System.in;

    public static void main(String[] args) throws IOException {
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
        byte c = read();
        while (c <= ' ') c = read();
        int n = 0;
        while (c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = read();
        }
        return n;
    }

    private static byte read() throws IOException {
        if (bufferPointer == bytesRead) {
            fillBuffer();
        }
        return buffer[bufferPointer++];
    }

    private static void fillBuffer() throws IOException {
        bytesRead = in.read(buffer, bufferPointer = 0, BUFFER_SIZE);
        if (bytesRead == -1) buffer[0] = -1;
    }
}