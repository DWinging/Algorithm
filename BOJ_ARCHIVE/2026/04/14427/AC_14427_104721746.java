/**
 * [BOJ] 14427 - 수열과 쿼리 15
 * - 제출 날짜: 2026년 4월 5일
 * - 결과: 맞았습니다!!
 * - 메모리: 16048 KB
 * - 시간: 196 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

    static int[] tree, arr;
    static int c, offset;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        
        inputArray(n);
        settingTree(n);
        
        System.out.print(solve(n));
    }

    private static void inputArray(int n) throws IOException {
        arr = new int[n + 1];
        arr[0] = Integer.MAX_VALUE; 
        for (int i = 1; i <= n; i++) {
            arr[i] = readInt();
        }
    }

    private static void settingTree(int n) {
        offset = 1;
        while (offset < n) offset <<= 1;

        tree = new int[offset << 1];

        for (int i = 1; i <= n; i++) {
            tree[offset + i - 1] = i;
        }

        for (int i = offset - 1; i > 0; i--) {
            tree[i] = getMinIndex(tree[i << 1], tree[i << 1 | 1]);
        }
    }

    private static String solve(int n) throws IOException {
        StringBuilder sb = new StringBuilder();
        int m = readInt();
        while (m-- > 0) {
            int comm = readInt();
            if (comm == 1) {
                update(readInt(), readInt());
            } else {
                sb.append(tree[1]).append('\n');
            }
        }
        return sb.toString();
    }

    private static void update(int i, int v) {
        arr[i] = v;
        int node = offset + i - 1;
        
        while (node > 1) {
            node >>= 1;
            tree[node] = getMinIndex(tree[node << 1], tree[node << 1 | 1]);
        }
    }

    private static int getMinIndex(int idx1, int idx2) {
        if (arr[idx1] < arr[idx2]) return idx1;
        if (arr[idx1] > arr[idx2]) return idx2;
        return Math.min(idx1, idx2);
    }

    private static int readInt() throws IOException {
        while (c <= ' ') c = System.in.read();
        int n = 0;
        while (c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}