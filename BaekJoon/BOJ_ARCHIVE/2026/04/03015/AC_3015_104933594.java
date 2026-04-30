/**
 * [BOJ] 3015 - 오아시스 재결합
 * - 제출 날짜: 2026년 4월 11일
 * - 결과: 맞았습니다!!
 * - 메모리: 15932 KB
 * - 시간: 212 ms
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

    private static long solve(int n) throws IOException {
        int[] stack = new int[n];
        int[] cnt = new int[n];
        long comb = 0; int top = -1;
        while(n-- > 0) {
            int val = readInt();
            int temp = 1;
            while(top > -1) {
                if(val < stack[top]) {
                    comb++;
                    break;
                }

                if(val == stack[top])
                    temp += cnt[top];

                comb += cnt[top--];
            }

            stack[++top] = val;
            cnt[top] = temp;
        }
        return comb;
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
