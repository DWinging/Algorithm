/**
 * [BOJ] 10999 - 구간 합 구하기 2
 * - 제출 날짜: 2026년 2월 25일
 * - 결과: 컴파일 에러
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    static long[] tree, lazy, arr;
    static int, c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = (int) readLong();
        int k = (int) readLong() + (int) readLong();
        init(n);
        inputArray(n);
        System.out.print(solve(n, k));
    }

    private static void init(int n) {
        tree = new long[n * 4];
        lazy = new long[n * 4];
        arr = new long[n + 1];
    }

    private static void inputArray(int n) throws IOException {
        for(int i = 1; i <= n; i++) arr[i] = readLong();
        settingTree(1, 1, n);
    }

    private static void settingTree(int node, int start, int end) {
        if(start == end) {
            tree[node] = arr[start];
            return;
        }

        int mid = (start + end) / 2;
        settingTree(node * 2, start, mid);
        settingTree(node * 2 + 1, mid + 1, end);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    private static String solve(int n, int k) throws IOException {
        StringBuilder sb = new StringBuilder();
        while(k-- > 0) {
            int command = (int) readLong();
            int a = (int) readLong();
            int b = (int) readLong();
            if(command == 1) {
                long value = readLong();
                sumValue(1, 1, n, a, b, value);
            } else {
                sb.append(searchTree(1, 1, n, a, b)).append('\n');
            }
        }
        return sb.toString();
    }

    private static void sumValue(int node, int start, int end, int a, int b, long value) {
        updateLazy(node, start, end);
        if(end < a || b < start) return;  
        if(a <= start && end <= b) {
            lazy[node] += value;
            updateLazy(node, start, end);
            return;
        }
        
        int mid = (start + end) / 2;
        sumValue(node * 2, start, mid, a, b, value);
        sumValue(node * 2 + 1, mid + 1, end, a, b, value);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    private static long searchTree(int node, int start, int end, int a, int b) {
        updateLazy(node, start, end);
        if(end < a || b < start) return 0;        
        if(a <= start && end <= b) return tree[node];

        int mid = (start + end) / 2;
        long value1 = searchTree(node * 2, start, mid, a, b);
        long value2 = searchTree(node * 2 + 1, mid + 1, end, a, b);
        return value1 + value2;
    }

    private static void updateLazy(int node, int start, int end) {
        if(lazy[node] == 0) return;
        if(start < end) {
            lazy[node * 2] += lazy[node];
            lazy[node * 2 + 1] += lazy[node];    
        }    
        tree[node] += (end - start + 1) * lazy[node];
        lazy[node] = 0;
    }

    private static long readLong() throws IOException {
        while(c <= ' ') c = System.in.read();
        boolean flag = false;
        if(c == '-') { flag = true; c = System.in.read(); }
        long n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c - '0');
            c = System.in.read();
        }
        return flag ? -n : n;
    }
}