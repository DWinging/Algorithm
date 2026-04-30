/**
 * [BOJ] 11658 - 구간 합 구하기 3
 * - 제출 날짜: 2026년 3월 7일
 * - 결과: 시간 초과
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

    static int[][] tree, arr;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();
        init(n);
        System.out.print(solve(n, m));
    }

    private static void init(int n) throws IOException{
        arr = new int[n][n + 1];
        for(int i = 0; i < n; i++) {
            for(int j = 1; j <= n; j++) {
                arr[i][j] = readInt();
            }
        }

        tree = new int[n][n * 4];

        for(int i = 0; i < n; i++)
            setTree(i, 1, 1, n);
    }

    private static void setTree(int idx, int node, int s, int e) {
        if(s == e) {
            tree[idx][node] = arr[idx][s];
            return;
        }

        int mid = (s + e) / 2;
        setTree(idx, node << 1, s, mid);
        setTree(idx, (node << 1) | 1, mid + 1, e);
        tree[idx][node] = tree[idx][node << 1] + tree[idx][(node << 1) | 1];
    }

    private static String solve(int n, int m) throws IOException {
        StringBuilder sb = new StringBuilder();
        while(m-- > 0) {
            int command = readInt();
            if(command == 0) {
                int y = readInt()-1;
                int x = readInt();
                int k = readInt();
                arr[y][x] = k;
                changeQuery(y, 1, 1, n, x, k);
            } else {
                int y1 = readInt()-1;
                int x1 = readInt();
                int y2 = readInt()-1;
                int x2 = readInt();
                int value = 0;
                for(int y = y1; y <= y2; y++) {
                    value += searchQuery(y, 1, 1, n, x1, x2);
                }
                sb.append(value).append('\n');
            }
        }
        return sb.toString();
    }

    private static void changeQuery(int idx, int node, int start, int end, int x, int k) {
        if(x < start || end < x) return;
        if(start == end) {
            tree[idx][node] = k;
            return;
        }

        int mid = (start + end) / 2;
        changeQuery(idx, node << 1, start, mid, x, k);
        changeQuery(idx, (node << 1) | 1, mid + 1, end, x, k);
        tree[idx][node] = tree[idx][node << 1] + tree[idx][(node << 1) | 1];
    }

    private static int searchQuery(int idx, int node, int start, int end, int a, int b) {
        if(b < start || end < a) return 0;
        if(a <= start && end <= b) return tree[idx][node];

        int mid = (start + end) / 2;
        int value = 0;
        value += searchQuery(idx, node << 1, start, mid, a, b);
        value += searchQuery(idx, (node << 1) | 1, mid + 1, end, a, b);
        return value;
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
