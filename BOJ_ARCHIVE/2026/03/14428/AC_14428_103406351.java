/**
 * [BOJ] 14428 - 수열과 쿼리 16
 * - 제출 날짜: 2026년 3월 1일
 * - 결과: 맞았습니다!!
 * - 메모리: 17108 KB
 * - 시간: 240 ms
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
        inputTree(n);
        int m = readInt();
        System.out.print(solve(n, m));
    }

    private static void inputTree(int n) throws IOException {
        int size = 1;
        while(size < n) size <<= 1;

        tree = new int[size << 1];
        arr = new int[n + 1];
        arr[0] = 1_000_000_005;

        for(int i = 0; i < tree.length; i++) tree[i] = -1;

        for(int i = 0; i < n; i++) {
            arr[i + 1] = readInt();
            tree[i + n] = i + 1;
        }

        for(int i = n - 1; i >= 0; i--) {
            tree[i] = compare(tree[i << 1], tree[(i << 1) | 1]);
        }
    }

    private static String solve(int n, int m) throws IOException {
        StringBuilder sb = new StringBuilder();
        while(m-- > 0) {
            int command = readInt();
            int a = readInt();
            int b = readInt();
            if(command == 1) update(n, a, b);
            else sb.append(searchQuery(n, a, b)).append('\n');
        }
        return sb.toString();
    }

    private static void update(int n, int a, int b) {
        arr[a] = b;
        int idx = a + n - 1;
        while(idx > 1) {
            idx >>= 1;
            tree[idx] = compare(tree[idx << 1], tree[(idx << 1) | 1]);
        }
    }

    private static int searchQuery(int n, int a, int b) {
        int res = -1;
        int left = a + n - 1;
        int right = b + n - 1;

        while(left <= right) {
            if(left % 2 != 0) res = compare(res, tree[left++]);
            if(right % 2 == 0) res = compare(res, tree[right--]);
            left >>= 1;
            right >>= 1;
        }
        return res;
    }

    private static int compare(int idx1, int idx2) {
        if(idx1 == -1) return idx2;
        if(idx2 == -1) return idx1;
        if(arr[idx1] < arr[idx2]) return idx1;
        if(arr[idx1] > arr[idx2]) return idx2;
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
