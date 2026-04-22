/**
 * [BOJ] 12837 - 가계부 (Hard)
 * - 제출 날짜: 2026년 4월 13일
 * - 결과: 맞았습니다!!
 * - 메모리: 47128 KB
 * - 시간: 236 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    static long[] tree;
    static int c;
    
    public static void main(String[] args) throws IOException{
        c = System.in.read();
        int n = readInt();
        int q = readInt();
        tree = new long[n * 4];
        System.out.print(solve(n, q));
    }

    private static String solve(int n, int k) throws IOException {
        StringBuilder sb = new StringBuilder();
        while(k-- > 0) {
            int command = readInt();
            if(command == 1) {
                int p = readInt();
                int x = readInt();
                updateQuery(1, 1, n, p, x);
            } else {
                int p = readInt();
                int q = readInt();
                sb.append(searchQuery(1, 1, n, p, q)).append('\n');
            }            
        }
        return sb.toString();
    }

    private static void updateQuery(int n, int s, int e, int x, int val) {
        if(x < s || e < x) return;
        tree[n] += val;
        if(s == e) return;

        int mid = (s + e) / 2;
        updateQuery(n << 1, s, mid, x, val);
        updateQuery((n << 1) + 1, mid + 1, e, x, val);
    }

    private static long searchQuery(int n, int s, int e, int a, int b) {
        if(b < s || e < a) return 0;
        if(a <= s && e <= b) return tree[n];

        int mid = (s + e) / 2;
        long val1 = searchQuery(n << 1, s, mid, a, b);
        long val2 = searchQuery((n << 1) + 1, mid + 1, e, a, b);
        return val1 + val2;
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        boolean flag = c == '-';
        if(c == '-') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return flag ? -n : n;
    }
}