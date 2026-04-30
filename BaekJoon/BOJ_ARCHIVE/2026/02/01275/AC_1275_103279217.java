/**
 * [BOJ] 1275 - 커피숍2
 * - 제출 날짜: 2026년 2월 25일
 * - 결과: 맞았습니다!!
 * - 메모리: 29420 KB
 * - 시간: 332 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    static long[] tree, arr;
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = (int) readLong();
        int m = (int) readLong();
        tree = new long[n * 4];
        arr = new long[n + 1];
        inputArray(n);
        settingTree(1, 1, n);
        System.out.print(solve(n, m));
    }

    private static void inputArray(int n) throws IOException {
        for(int i = 1; i <= n; i++) arr[i] = readLong();
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
            int x = (int) readLong();
            int y = (int) readLong();
            if(x > y) {
                int temp = x;
                x = y;
                y = temp;
            }
            sb.append(searchQuery(1, 1, n, x, y)).append('\n');
            
            int a = (int) readLong();
            long b = readLong();
            changeNode(1, 1, n, a, b - arr[a]);
            arr[a] = b;
        }
        return sb.toString();
    }

    private static long searchQuery(int node, int start, int end, int x, int y) {
        if(end < x || y < start) return 0L;
        if(x <= start && end <= y) return tree[node];
        long value = 0L;
        int mid = (start + end) / 2;
        value += searchQuery(node * 2, start, mid, x, y);
        value += searchQuery(node * 2 + 1, mid + 1, end, x, y);
        return value;
    }

    private static void changeNode(int node, int start, int end, int a, long w) {
        if(end < a || a < start) return;
        tree[node] += w;
        if(start == end) return;
        int mid = (start + end) / 2;
        changeNode(node * 2, start, mid, a, w);
        changeNode(node * 2 + 1, mid + 1, end, a, w);
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
            n = (n << 3) + (n << 1) + (c - '0');
            c = System.in.read();
        }
        return flag ? -n : n;
    }
}