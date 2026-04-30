/**
 * [BOJ] 1395 - 스위치
 * - 제출 날짜: 2026년 2월 25일
 * - 결과: 맞았습니다!!
 * - 메모리: 18184 KB
 * - 시간: 252 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    static int[] tree, lazy;
    static int c;
    
    public static void main(String[] args) throws IOException {
        int n = init();
        System.out.println(solve(n));
    }

    private static int init() throws IOException {
        c = System.in.read();
        int n = readInt();
        tree = new int[n * 4];
        lazy = new int[n * 4];
        return n;
    }

    private static String solve(int n) throws IOException {
        StringBuilder sb = new StringBuilder();
        int m = readInt();
        while(m-- > 0) {
            int command = readInt();
            int a = readInt();
            int b = readInt();
            if(command == 0) switchNode(1, 1, n, a, b);
            else sb.append(searchCnt(1, 1, n, a, b)).append('\n');
        }
        return sb.toString();
    }

    private static void switchNode(int node, int start, int end, int a, int b) {
        updateLazy(node, start, end);
        if(end < a || start > b) return;

        if(a <= start && end <= b) {
            lazy[node] ^= 1;
            updateLazy(node, start, end);
            return;
        }

        int mid = (start + end) / 2;
        switchNode(node * 2, start, mid, a, b);
        switchNode(node * 2 + 1, mid + 1, end, a, b);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    private static int searchCnt(int node, int start, int end, int a, int b) {
        updateLazy(node, start, end);
        if(end < a || start > b) return 0;
        if(a <= start && end <= b) return tree[node];

        int mid = (start + end) / 2, value = 0;
        value += searchCnt(node * 2, start, mid, a, b);
        value += searchCnt(node * 2 + 1, mid + 1, end, a, b);
        return value;
    }

    private static void updateLazy(int node, int start, int end) {
        if(lazy[node] == 0) return;
        if(start < end) {
            lazy[node * 2] ^= 1;
            lazy[node * 2 + 1] ^= 1; 
        }
        tree[node] = (end - start + 1) - tree[node];
        lazy[node] ^= 1;
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