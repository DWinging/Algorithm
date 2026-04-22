/**
 * [BOJ] 5676 - 음주 코딩
 * - 제출 날짜: 2026년 4월 6일
 * - 결과: 맞았습니다!!
 * - 메모리: 17268 KB
 * - 시간: 284 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    final static int MAX_RANGE = 100_000;
    static int[] tree = new int[MAX_RANGE << 2];
    static int[] arr = new int[MAX_RANGE + 1];
    static int c;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        while(checkEOF()) {            
            int n = readInt();
            int k = readInt();

            inputArray(n);
            settingTree(1, 1, n);
            solve(sb, n, k);
        }
        System.out.print(sb);
    }

    private static boolean checkEOF() throws IOException {
        while(c <= ' ') {
            if (c == -1) return false;
            c = System.in.read();
        }
        return true;
    }

    private static void inputArray(int n) throws IOException {
        for(int i = 1; i <= n; i++) {
            int val = readInt();
            if(val > 0) arr[i] = 1;
            else if(val < 0) arr[i] = -1;
            else arr[i] = 0;
        }
    }

    private static void settingTree(int node, int s, int e) {
        if(s == e) {
            tree[node] = arr[s];
            return;
        }

        int mid = (s + e) >> 1;
        settingTree(node << 1, s, mid);
        settingTree(node << 1 | 1, mid + 1, e);
        tree[node] = tree[node << 1] * tree[node << 1 | 1];
    }

    private static void solve(StringBuilder sb, int n, int k) throws IOException {
        while(k-- > 0) {
            char comm = readCommand();
            if(comm == 'P') {
                int a = readInt();
                int b = readInt();
                int val = searchQeury(1, 1, n, a, b);
                if(val > 0) {
                    sb.append('+');
                } else if(val < 0) {
                    sb.append('-');
                } else {
                    sb.append('0');
                }
            } else {
                int i = readInt();
                int v = readInt();
                if(v > 0) v = 1;
                else if(v < 0) v = -1;
                else v = 0;

                if(v != arr[i]) {
                    arr[i] = v;
                    updateQuery(1, 1, n, i);                    
                }
            }
        }
        sb.append('\n');
    }

    private static int searchQeury(int node, int s, int e, int a, int b) {
        if(b < s || e < a) return 1;
        if(a <= s && e <= b) return tree[node];

        int mid = (s + e) >> 1;
        int val1 = searchQeury(node << 1, s, mid, a, b);
        int val2 = searchQeury(node << 1 | 1, mid + 1, e, a, b);
        return val1 * val2;
    }

    private static void updateQuery(int node, int s, int e, int idx) {
        if(idx < s || e < idx) return;
        if(s == e) {
            tree[node] = arr[idx];
            return;
        }

        int mid = (s + e) >> 1;
        updateQuery(node << 1, s, mid, idx);
        updateQuery(node << 1 | 1, mid + 1, e, idx);
        tree[node] = tree[node << 1] * tree[node << 1 | 1];
    }

    private static char readCommand() throws IOException {
        while(c <= ' ') c = System.in.read();
        char comm = (char) c;
        c = System.in.read();
        return comm;
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