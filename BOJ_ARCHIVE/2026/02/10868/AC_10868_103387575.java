/**
 * [BOJ] 10868 - 최솟값
 * - 제출 날짜: 2026년 2월 28일
 * - 결과: 맞았습니다!!
 * - 메모리: 21380 KB
 * - 시간: 232 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

    final static int INF = 1_000_000_005;
    static int[] tree, arr;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();
        tree = new int[n * 4];
        inputArray(n);
        settingTree(1, 1, n);
        System.out.print(solve(n, m));
    }

    private static void inputArray(int n) throws IOException {
        arr = new int[n + 1];
        for(int i = 1; i <= n; i++) arr[i] = readInt();
    }

    private static void settingTree(int node, int start, int end){
        if(start == end) {
            tree[node] = arr[start];
            return;
        }

        int mid = (start + end) >> 1;
        settingTree(node << 1, start, mid);
        settingTree((node << 1) + 1, mid + 1, end);
        tree[node] = Math.min(tree[node << 1], tree[(node << 1) + 1]);
    }

    private static String solve(int n, int m) throws IOException {
        StringBuilder sb = new StringBuilder();
        while(m-- > 0) {
            int a = readInt();
            int b = readInt();
            if(a > b) {
                a ^= b;
                b ^= a;
                a ^= b;
            }
            sb.append(searchQuery(1, 1, n, a, b)).append('\n');
        }
        return sb.toString();
    }

    private static int searchQuery(int node, int start, int end, int a, int b) {
        if(b < start || end < a) return INF;
        if(a <= start && end <= b) return tree[node];

        int mid = (start + end) >> 1;
        int value1 = searchQuery(node << 1, start, mid, a, b);
        int value2 = searchQuery((node << 1) + 1, mid + 1, end, a, b);
        return Math.min(value1, value2);
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
