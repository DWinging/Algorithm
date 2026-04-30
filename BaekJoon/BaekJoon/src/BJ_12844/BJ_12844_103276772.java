package BJ_12844;

import java.io.*;
/**
 *  2026년 2월 25일 풀이
 * BaekJoon_12844 XOR
 * 메모리 38736 KB
 * 시간 836 ms
 */
public class BJ_12844_103276772 {

    static int[] tree, lazy, arr;
    static int c;

    public static void main(String[] args) throws IOException {
        int n = init();
        inputArray(n);
        settingTree(1, 1, n);
        System.out.print(solve(n));
    }

    private static int init() throws IOException {
        c = System.in.read();
        int n = readInt();
        tree = new int[n * 4];
        lazy = new int[n * 4];
        arr = new int[n + 1];
        return n;
    }

    private static void inputArray(int n) throws IOException {
        for(int i = 1; i <= n; i++) arr[i] = readInt();
    }

    private static void settingTree(int node, int start, int end) {
        if(start == end) {
            tree[node] = arr[start];
            return;
        }

        int mid = (start + end) / 2;
        settingTree(node * 2, start, mid);
        settingTree(node * 2 + 1, mid + 1, end);
        tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
    }

    private static String solve(int n) throws IOException {
        StringBuilder sb = new StringBuilder();
        int m = readInt();
        while(m-- > 0) {
            int command = readInt();
            int a = readInt() + 1;
            int b = readInt() + 1;
            if(a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            if(command == 1) {
                int k = readInt();
                calculateNode(1, 1, n, a, b, k);
            } else {
                sb.append(searchNode(1, 1, n, a, b)).append('\n');
            }
        }
        return sb.toString();
    }

    private static void calculateNode(int node, int start, int end, int a, int b, int k) {
        updateNode(node, start, end);
        if(end < a || b < start) return;

        if(a <= start && end <= b) {
            lazy[node] ^= k;
            updateNode(node, start, end);
            return;
        }

        int mid = (start + end) / 2;
        calculateNode(node * 2, start, mid, a, b, k);
        calculateNode(node * 2 + 1, mid + 1, end, a, b, k);
        tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
    }

    private static int searchNode(int node, int start, int end, int a, int b) {
        updateNode(node, start, end);
        if(end < a || b < start) return 0;
        if(a <= start && end <= b) return tree[node];

        int mid = (start + end) / 2;
        int value1 = searchNode(node * 2, start, mid, a, b);
        int value2 = searchNode(node * 2 + 1, mid + 1, end, a, b);
        return value1 ^ value2;
    }

    private static void updateNode(int node, int start, int end) {
        if(lazy[node] == 0) return;
        if(start < end) {
            lazy[node * 2] ^= lazy[node];
            lazy[node * 2 + 1] ^= lazy[node];
        }
        if((end - start + 1) % 2 != 0) tree[node] ^= lazy[node];
        lazy[node] = 0;
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <='9') {
            n = (n << 3) + (n << 1) + (c - '0');
            c = System.in.read();
        }
        return n;
    }
}
