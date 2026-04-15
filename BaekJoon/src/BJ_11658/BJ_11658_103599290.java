package BJ_11658;

import java.io.*;
/**
 * 2026년 3월 7일 풀이
 * BaekJoon_11658 구간 합 구하기 3
 * 메모리 24456 KB
 * 시간 2004 ms
 */
public class BJ_11658_103599290 {

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
        tree = new int[n][n * 2];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                tree[i][j + n] = readInt();
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = n - 1; j > 0; j--) {
                tree[i][j] = tree[i][j << 1] + tree[i][j << 1 | 1];
            }
        }
    }

    private static String solve(int n, int m) throws IOException {
        StringBuilder sb = new StringBuilder();
        while(m-- > 0) {
            int command = readInt();
            if(command == 0) {
                int y = readInt()-1;
                int x = readInt()-1;
                int k = readInt();
                updateQuery(y, x, n, k);
            } else {
                int y1 = readInt()-1;
                int x1 = readInt()-1;
                int y2 = readInt()-1;
                int x2 = readInt()-1;
                int value = 0;
                for(int y = y1; y <= y2; y++) {
                    value += searchQuery(y, x1, x2, n);
                }
                sb.append(value).append('\n');
            }
        }
        return sb.toString();
    }

    private static void updateQuery(int row, int col, int n, int value) {
        int idx = col + n;
        tree[row][idx] = value;
        while(idx > 1) {
            idx >>= 1;
            tree[row][idx] = tree[row][idx << 1] + tree[row][idx << 1 | 1];
        }
    }

    private static int searchQuery(int row, int s, int e, int n) {
        int res = 0;
        for(s += n, e += n; s <= e; s >>= 1, e >>= 1) {
            if((s & 1) == 1) res += tree[row][s++];
            if((e & 1) == 0) res += tree[row][e--];
        }
        return res;
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

