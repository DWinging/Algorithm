/**
 * [BOJ] 35508 - Good Bye, 토마토!
 * - 제출 날짜: 2026년 4월 26일
 * - 결과: 메모리 초과
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {
    
    static int[][] arr;
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int d = readInt();

        inputArray(n);
        System.out.println(solve(n, d));
    }

    private static void inputArray(int n) throws IOException {
        arr = new int[n][3];
        for(int i = 0; i < n; i++) {
            arr[i][0] = readInt();
            arr[i][1] = readInt();
            arr[i][2] = readInt();
        }
    }

    private static int solve(int n, int d) {
        int[][] dp = new int[d + 1][2];
        for(int i = 1; i <= d; i++) {
            dp[i][0] = -1;
            dp[i][1] = -1;
        }
        
        for(int i = 0; i < n; i++) {
            int t = arr[i][0];
            int a = arr[i][1];
            int b = arr[i][2];

            for(int j = d; j >= t; j--) {
                if(dp[j-t][0] > -1) {
                    int tempA = Math.max(dp[j - t][0], a);
                    int tempB = Math.max(dp[j - t][1], b);
                    int tempS = tempA + tempB;
                    if(tempS > dp[j][0] + dp[j][1]) {
                        dp[j][0] = tempA;
                        dp[j][1] = tempB;
                    }
                }
            }
        }

        int res = 0;
        for(int i = 1; i <= d; i++) {
            int score = dp[i][0] + dp[i][1];
            if(res < score) res = score;
        }
        return res;
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