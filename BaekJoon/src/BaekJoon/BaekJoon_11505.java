package BaekJoon;

import java.io.*;

public class BaekJoon_11505 {

    final static int MOD = 1_000_000_007;
    static long[] tree;
    static int c, n, m;

    public static void main(String[] args) throws IOException {
        init();
        inputTree();
        System.out.print(solve());
    }

    private static void init() throws IOException {
        c = System.in.read();
        n = readInt();
        m = readInt() + readInt();
        tree = new long[n * 2];
    }

    private static void inputTree() throws IOException {
        for(int i = 0; i < n; i++) {
            tree[n + i] = readInt();
        }

        for(int i = n - 1; i >= 0; i--) {
            tree[i] = (tree[i << 1] * tree[(i << 1) + 1]) % MOD;
        }
    }

    private static String solve() throws IOException {
        StringBuilder sb = new StringBuilder();
        while(m-- > 0) {
            int command = readInt();
            int a = readInt();
            int b = readInt();
            if(command == 1) update(a, b);
            else sb.append(searchQuery(a, b)).append('\n');
        }
        return sb.toString();
    }

    private static void update(int v, int value) {
        int idx = n + v - 1;
        tree[idx] = value;

        while(idx > 1) {
            idx >>= 1;
            tree[idx] = (tree[idx << 1] * tree[(idx << 1) + 1]) % MOD;
        }
    }

    private static long searchQuery(int s, int e) {
        long res = 1;
        int left = n + s - 1;
        int right = n + e - 1;
        while(left <= right) {
            if(left % 2 != 0) res = (res * tree[left++]) % MOD;
            if(right % 2 == 0) res = (res * tree[right--]) % MOD;
            left >>= 1;
            right >>= 1;
        }
        return res;
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int temp = 0;
        while(c >= '0' && c <= '9') {
            temp = (temp << 3) + (temp << 1) + (c - '0');
            c = System.in.read();
        }
        return temp;
    }
}
