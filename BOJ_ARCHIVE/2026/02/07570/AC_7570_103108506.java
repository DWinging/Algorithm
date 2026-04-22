/**
 * [BOJ] 7570 - 줄 세우기
 * - 제출 날짜: 2026년 2월 20일
 * - 결과: 맞았습니다!!
 * - 메모리: 15900 KB
 * - 시간: 220 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        System.out.println(solve(n));
    }

    private static int solve(int n) throws IOException {
        int[] dp = new int[n + 1];
        int max = 1;
        for(int i = 1; i <= n; i++) {
            int num = readInt();
            dp[num] = dp[num-1] != 0 ? dp[num-1] + 1 : 1;
            max = Math.max(dp[num], max);
        }
        return n - max;
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
