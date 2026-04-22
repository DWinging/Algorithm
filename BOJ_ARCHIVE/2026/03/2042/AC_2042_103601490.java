/**
 * [BOJ] 2042 - 구간 합 구하기
 * - 제출 날짜: 2026년 3월 7일
 * - 결과: 맞았습니다!!
 * - 메모리: 30864 KB
 * - 시간: 364 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

    static long[] tree;
    static long[] arr;
    static int c, N;

    public static void main(String[] args) throws IOException {
       c = System.in.read();
       N = readInt();
       int m = readInt() + readInt();
       init();
       System.out.print(solve(m));
    }

    private static void init() throws IOException {
        arr = new long[N + 1];
        tree = new long[N + 1];

        for(int i = 1; i <= N; i++) {
            long num = readLong();
            updateQuery(i, num);
        }
    }

    private static String solve(int m) throws IOException {
        StringBuilder sb = new StringBuilder();
        while(m-- > 0) {
            int comm = readInt();
            if(comm == 1) {
                int idx = readInt();
                long val = readLong();
                updateQuery(idx, val);
            } else {
                int a = readInt();
                int b = readInt();
                sb.append(getSum(b) - getSum(a-1)).append('\n');
            }
        }
        return sb.toString();
    }

    private static void updateQuery(int idx, long val) {
        long diff = val - arr[idx];
        arr[idx] = val;

        while(idx <= N) {
            tree[idx] += diff;
            idx += (idx & -idx);
        }
    }

    private static long getSum(int idx) {
        long res = 0L;
        while(idx > 0) {
            res += tree[idx];
            idx -= (idx & -idx);
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

    private static long readLong() throws IOException {
       while(c <= ' ') c = System.in.read();
       boolean flag = false;
       if(c == '-') {
           flag = true;
           c = System.in.read();
       }
       long n = 0;
       while(c >= '0' && c <= '9') {
           n = (n << 3) + (n << 1) + (c & 15);
           c = System.in.read();
       }
       return flag ? -n : n;
    }
}
