/**
 * [BOJ] 6549 - 히스토그램에서 가장 큰 직사각형
 * - 제출 날짜: 2026년 2월 25일
 * - 결과: 맞았습니다!!
 * - 메모리: 12968 KB
 * - 시간: 152 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    final static int MAX_RANGE = 100_000;
    static int[] arr = new int[MAX_RANGE];
    static int[] stack = new int[MAX_RANGE];
    static int c;
    
    public static void main(String[] args) throws IOException{
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int n;
        while((n = (int) readLong()) > 0) sb.append(solve(n)).append('\n');
        System.out.print(sb);
    }

    private static long solve(int n) throws IOException {
        long value = 0; int top = -1;
        for(int i = 0; i < n; i++) {
            int num = (int) readLong();
            arr[i] = num;
            while(top > -1 && arr[stack[top]] > num) {
                int idx = stack[top--];
                int height = arr[idx];
                long width = (top == -1) ? i : i - stack[top] - 1;
                value = Math.max(value, height * width);
            }
            stack[++top] = i;
        }

        while(top > -1) {
            int idx = stack[top--];
            int height = arr[idx];
            long width = (top == -1) ? n : n - stack[top] - 1;
            value = Math.max(value, height * width);
        }
        return value;
    }

    private static long readLong() throws IOException {
        while(c <= ' ') c = System.in.read();
        long n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c - '0');
            c = System.in.read();
        }
        return n;
    }
}