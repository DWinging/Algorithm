package BaekJoon;

import java.io.*;

public class BaekJoon_2268 {

    static long[] tree, arr;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();
        tree = new long[n * 2];
        arr = new long[n];
        System.out.print(solve(n, m));
    }

    private static String solve(int n, int m) throws IOException {
        StringBuilder sb = new StringBuilder();
        while(m-- > 0) {
            int comm = readInt();
            if(comm == 0) {
                int i = readInt() - 1;
                int j = readInt() - 1;
                if(i > j) {
                    int temp = i;
                    i = j;
                    j = temp;
                }
                sb.append(getSum(i, j, n)).append('\n');
            } else {
                int i = readInt() - 1;
                int k = readInt();
                updateQuery(i, k, n);
            }
        }
        return sb.toString();
    }

    private static long getSum(int l, int r, int n) {
        long res = 0L;
        l += n;
        r += n;

        while(l <= r) {
            if((l & 1) == 1) res += tree[l++];
            if((r & 1) == 0) res += tree[r--];
            l >>= 1;
            r >>= 1;
        }
        return res;
    }

    private static void updateQuery(int idx, int k, int n) {
        long diff = k - arr[idx];
        arr[idx] = k;

        idx += n;
        while(idx > 0) {
            tree[idx] += diff;
            idx >>= 1;
        }
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
