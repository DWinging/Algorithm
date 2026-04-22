/**
 * [BOJ] 2357 - 최솟값과 최댓값
 * - 제출 날짜: 2026년 2월 24일
 * - 결과: 맞았습니다!!
 * - 메모리: 29472 KB
 * - 시간: 284 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

    final static int MAX_VALUE = 1_000_000_000;

    static int[] minTree;
    static int[] maxTree;
    static int[] arr;
    static int c, minValue, maxValue;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();
        init(n);
        System.out.print(solve(n, m));
    }

    private static void init(int n) throws IOException {
        inputArray(n);

        minTree = new int[n * 4];
        maxTree = new int[n * 4];
        settingTree(1, 1, n);
    }

    private static void inputArray(int n) throws IOException {
        arr = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            arr[i] = readInt();
        }
    }

    private static void settingTree(int node, int start, int end) {
        if(start == end) {
            minTree[node] = arr[start];
            maxTree[node] = arr[start];
            return;
        }

        int mid = (start + end) / 2;
        settingTree(node * 2, start, mid);
        settingTree(node * 2 + 1, mid + 1, end);

        minTree[node] = Math.min(minTree[node * 2], minTree[node * 2 + 1]);
        maxTree[node] = Math.max(maxTree[node * 2], maxTree[node * 2 + 1]);
    }

    private static String solve(int n, int m) throws IOException {
        StringBuilder sb = new StringBuilder();
        while(m-- > 0) {
            minValue = MAX_VALUE;
            maxValue = 0;

            int a = readInt();
            int b = readInt();
            search(1, 1, n, a, b);
            sb.append(minValue).append(' ').append(maxValue).append('\n');
        }
        return sb.toString();
    }

    private static void search(int node, int s, int e, int a, int b) {
        if(b < s || a > e) return;
        if(a <= s && b >= e) {
            minValue = Math.min(minValue, minTree[node]);
            maxValue = Math.max(maxValue, maxTree[node]);
            return;
        }

        int mid = (s + e) / 2;
        search(node * 2, s, mid, a, b);
        search(node * 2 + 1, mid + 1, e, a, b);
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
