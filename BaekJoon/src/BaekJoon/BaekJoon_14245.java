package BaekJoon;

import java.io.*;

public class BaekJoon_14245 {

    static int[] lazy, arr;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        inputArray(n);
        System.out.print(solve(n));
    }

    private static void inputArray(int n) throws IOException {
        lazy = new int[n * 4];
        arr = new int[n + 1];
        for(int i = 1; i <= n; i++) arr[i] = readInt();
    }

    private static String solve(int n) throws IOException {
        StringBuilder sb = new StringBuilder();
        int m = readInt();

        while(m-- > 0) {
            int command = readInt();
            if(command == 1) {
                int a = readInt() + 1;
                int b = readInt() + 1;
                int k = readInt();
                xorQuery(1, 1, n, a, b, k);
            } else {
                int idx = readInt() + 1;
                searchQuery(1, 1, n, idx);
                sb.append(arr[idx]).append('\n');
            }
        }
        return sb.toString();
    }

    private static void xorQuery(int node, int start, int end, int a, int b, int xor) {
        updateQuery(node, start, end);
        if(b < start || end < a) return;

        if(a <= start && end <= b) {
            lazy[node] ^= xor;
            updateQuery(node, start, end);
            return;
        }

        int mid = (start + end) / 2;
        xorQuery(node << 1, start, mid, a, b, xor);
        xorQuery(((node << 1) | 1), mid + 1, end, a, b, xor);
    }

    private static void searchQuery(int node, int start, int end, int idx) {
        updateQuery(node, start, end);
        if(start == end) return;
        int mid = (start + end) / 2;
        if(start <= idx && idx <= mid) {
            searchQuery(node << 1, start, mid, idx);
        } else if(mid + 1 <= idx && idx <= end) {
            searchQuery(((node << 1) | 1), mid + 1, end, idx);
        }
    }

    private static void updateQuery(int node, int start, int end) {
        if(start == end) {
            arr[start] ^= lazy[node];
        } else {
            lazy[node << 1] ^= lazy[node];
            lazy[(node << 1) | 1] ^= lazy[node];
        }
        lazy[node] = 0;
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
