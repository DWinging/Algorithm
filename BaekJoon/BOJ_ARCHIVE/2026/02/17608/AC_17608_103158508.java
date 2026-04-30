/**
 * [BOJ] 17608 - 막대기
 * - 제출 날짜: 2026년 2월 21일
 * - 결과: 맞았습니다!!
 * - 메모리: 12348 KB
 * - 시간: 104 ms
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
        int[] stack = new int[100_000];
        int top = -1;
        while(n-- > 0) {
            int num = readInt();
            while(top >= 0 && stack[top] <= num) top--;
            stack[++top] = num;
        }
        return top + 1;
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
