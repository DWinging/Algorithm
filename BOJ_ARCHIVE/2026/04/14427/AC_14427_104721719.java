/**
 * [BOJ] 14427 - 수열과 쿼리 15
 * - 제출 날짜: 2026년 4월 5일
 * - 결과: 맞았습니다!!
 * - 메모리: 16708 KB
 * - 시간: 224 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

    static int[] tree, arr;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        inputArray(n);

        tree = new int[n << 2];
        settingTree(1, 1, n);
        System.out.println(solve(n));
    }

    private static void inputArray(int n) throws IOException {
        arr = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            arr[i] = readInt();
        }
    }

    private static void settingTree(int node, int s, int e) {
        if(s == e) {
            tree[node] = s;
            return;
        }

        int mid = (s + e) >> 1;
        settingTree(node << 1, s, mid);
        settingTree(node << 1 | 1, mid + 1, e);
        tree[node] = getMinIndex(tree[node << 1], tree[node << 1 | 1]);
    }

    private static String solve(int n) throws IOException {
        StringBuilder sb = new StringBuilder();
        int m = readInt();
        while(m-- > 0) {
            int comm = readInt();
            if(comm == 1) {
                int i = readInt();
                int v = readInt();
                arr[i] = v;
                updateQuery(1, 1, n, i);
            } else {
                sb.append(tree[1]).append('\n');
            }
        }
        return sb.toString();
    }

    private static void updateQuery(int node, int s, int e, int k) {
        if(k < s || e < k) return;
        if(s == e) {
            tree[node] = k;
            return;
        }

        int mid = (s + e) >> 1;
        updateQuery(node << 1, s, mid, k);
        updateQuery(node << 1 | 1, mid + 1, e, k);
        tree[node] = getMinIndex(tree[node << 1], tree[node << 1 | 1]);
    }

    private static int getMinIndex(int idx1, int idx2) {
        if (idx1 == 0) return idx2;
        if (idx2 == 0) return idx1;

        if (arr[idx1] < arr[idx2]) return idx1;
        if (arr[idx1] > arr[idx2]) return idx2;

        return Math.min(idx1, idx2);
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}
