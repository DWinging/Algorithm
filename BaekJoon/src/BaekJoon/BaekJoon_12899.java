package BaekJoon;

import java.io.*;

public class BaekJoon_12899 {

    final static int MAX_SIZE = 2_000_000;
    static int[] tree;
    static int c;

    public static void main(String[] args) throws IOException {
        init();
        System.out.print(solve());
    }

    private static void init() throws IOException {
        c = System.in.read();
        tree = new int[MAX_SIZE * 4];
    }

    private static String solve() throws IOException {
        StringBuilder sb = new StringBuilder();
        int n = readInt();
        while(n-- > 0) {
            int comm = readInt();
            if(comm == 1) updateQuery(readInt(), 1, 1, MAX_SIZE);
            else sb.append(searchQuery(readInt(), 1, 1, MAX_SIZE)).append('\n');
        }
        return sb.toString();
    }

    private static void updateQuery(int k, int node, int s, int e) {
        tree[node]++;
        if(s == e) { return; }

        int mid = (s + e) / 2;
        if(k <= mid) updateQuery(k, node << 1, s, mid);
        else updateQuery(k, (node << 1) | 1, mid + 1, e);
    }

    private static int searchQuery(int k, int node, int s, int e) {
        tree[node]--;
        if(s == e) { return s; }

        int mid = (s + e) / 2;
        if(tree[node << 1] >= k) return searchQuery(k, node << 1, s, mid);
        else return searchQuery(k - tree[node << 1], (node << 1) | 1, mid + 1, e);
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
