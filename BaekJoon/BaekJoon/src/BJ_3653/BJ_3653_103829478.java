package BJ_3653;

import java.io.*;
/**
 * 2026년 3월 13일 풀이
 * BaekJoon_3653 영화 수집
 * 메모리 25552 KB
 * 시간 412 ms
 */
public class BJ_3653_103829478 {

    static StringBuilder sb = new StringBuilder();
    static int[] tree = new int[200_000 * 4];
    static int[] arr = new int[100_000 + 1];
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int t = readInt();
        while(t-- > 0) {
            int n = readInt();
            int m = readInt();
            init(n, m);
            solve(n, m);
            System.out.println(sb);
            sb.setLength(0);
        }
    }

    private static void init(int n, int m) {
        for(int i = 1; i <= n; i++) arr[i] = i + m;
        for(int i = 0; i < (n + m) * 4; i++) tree[i] = 0;
        setTree(1, 1, n + m, m + 1);
    }

    private static void setTree(int node, int s, int e, int idx) {
        if(e < idx) return;
        if(s == e) {
            tree[node] += 1;
            return;
        }

        int mid = (s + e) / 2;
        setTree(node << 1, s, mid, idx);
        setTree(node << 1 | 1, mid + 1, e, idx);
        tree[node] = tree[node << 1] + tree[node << 1 | 1];
    }

    private static void solve(int n, int m) throws IOException {
        for(int i = m; i > 0; i--) {
            int movie = readInt();
            int point = arr[movie];
            sb.append(getSum(1, 1, n + m, 1, point - 1)).append(' ');
            update(1, 1, n + m, point, -1);
            update(1, 1, n + m, i, 1);
            arr[movie] = i;
        }
    }

    private static int getSum(int node, int s, int e, int a, int b) {
        if(b < s || e < a) return 0;

        if(a <= s && e <= b) return tree[node];

        int mid = (s + e) / 2;
        int sum1 = getSum(node << 1, s, mid, a, b);
        int sum2 = getSum(node << 1 | 1, mid + 1, e, a, b);
        return sum1 + sum2;
    }

    private static void update(int node, int s, int e, int idx, int w) {
        if(idx < s || e < idx) return;
        tree[node] += w;
        if(s == e) return;
        int mid = (s + e) / 2;
        update(node << 1, s, mid, idx, w);
        update(node << 1 | 1, mid + 1, e, idx, w);
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
