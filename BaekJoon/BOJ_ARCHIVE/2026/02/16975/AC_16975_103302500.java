/**
 * [BOJ] 16975 - 수열과 쿼리 21
 * - 제출 날짜: 2026년 2월 26일
 * - 결과: 맞았습니다!!
 * - 메모리: 24104 KB
 * - 시간: 316 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    static long[] tree, lazy;
    static int[] arr;
    static int c;
    
    public static void main(String[] args) throws IOException {
        int n = init();
        int m = readInt();
        System.out.print(solve(n, m));
    }

    private static int init() throws IOException {
        c = System.in.read();
        int n = readInt();
        inputArray(n);
        tree = new long[n * 4];
        lazy = new long[n * 4];
        settingTree(1, 1, n);
        return n;
    }

    private static void inputArray(int n) throws IOException {
        arr = new int[n + 1];
        for(int i = 1; i <= n; i++) arr[i] = readInt();
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

    private static String solve(int n, int m) throws IOException {
        StringBuilder sb = new StringBuilder();
        while(m-- > 0) {
            int command = readInt();
            if(command == 1) {
                int a = readInt();
                int b = readInt();
                long k = readLong();
                sumQuery(1, 1, n, a, b, k);
            } else {
                int k = readInt();
                sb.append(searchValue(1, 1, n, k)).append('\n');
            }
        }
        return sb.toString();
    }

    private static void sumQuery(int node, int start, int end, int a, int b, long k) {
        updateQuery(node, start, end);
        if(end < a || b < start) return;

        if(a <= start && end <= b) {
            lazy[node] += k;
            updateQuery(node, start, end);
            return;
        }

        int mid = (start + end) / 2;
        sumQuery(node * 2, start, mid, a, b, k);
        sumQuery(node * 2 + 1, mid + 1, end, a, b, k);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    private static long searchValue(int node, int start, int end, int target) {
        updateQuery(node, start, end);
        if(target < start || end < target) return 0;
        if(start == end) {
            tree[node] += lazy[node];
            lazy[node] = 0;
            return tree[node];
        }

        int mid = (start + end) / 2;
        long value = 0;
        value += searchValue(node * 2, start, mid, target);
        value += searchValue(node * 2 + 1, mid + 1, end, target);
        return value;
    }

    private static void updateQuery(int node, int start, int end) {
        if(lazy[node] == 0) return;
        if(start < end) {
            lazy[node * 2] += lazy[node];
            lazy[node * 2 + 1] += lazy[node];
        }
        tree[node] += lazy[node];
        lazy[node] = 0;
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