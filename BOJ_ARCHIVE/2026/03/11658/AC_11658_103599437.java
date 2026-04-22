/**
 * [BOJ] 11658 - 구간 합 구하기 3
 * - 제출 날짜: 2026년 3월 7일
 * - 결과: 맞았습니다!!
 * - 메모리: 24668 KB
 * - 시간: 372 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

    static int[][] tree, arr;
    static int N, M, c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        N = readInt();
        M = readInt();

        tree = new int[N + 1][N + 1];
        arr = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int val = readInt();
                update(i, j, val);
            }
        }

        System.out.print(solve());
    }

    private static String solve() throws IOException {
        StringBuilder sb = new StringBuilder();
        while (M-- > 0) {
            int command = readInt();
            if (command == 0) {
                int y = readInt();
                int x = readInt();
                int k = readInt();
                update(y, x, k);
            } else {
                int y1 = readInt();
                int x1 = readInt();
                int y2 = readInt();
                int x2 = readInt();

                int result = getSum(y2, x2)
                        - getSum(y1 - 1, x2)
                        - getSum(y2, x1 - 1)
                        + getSum(y1 - 1, x1 - 1);
                sb.append(result).append('\n');
            }
        }
        return sb.toString();
    }

    private static void update(int y, int x, int val) {
        int diff = val - arr[y][x];
        arr[y][x] = val;

        for (int i = y; i <= N; i += i & -i) {
            for (int j = x; j <= N; j += j & -j) {
                tree[i][j] += diff;
            }
        }
    }

    private static int getSum(int y, int x) {
        int res = 0;
        for (int i = y; i > 0; i -= i & -i) {
            for (int j = x; j > 0; j -= j & -j) {
                res += tree[i][j];
            }
        }
        return res;
    }

    private static int readInt() throws IOException {
        while (c <= ' ') c = System.in.read();
        int n = 0;
        while (c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c - '0');
            c = System.in.read();
        }
        return n;
    }
}