package BaekJoon;

import java.io.*;

public class BaekJoon_2610 {

    static int[] parents, rank;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();

        init(n);
    }

    private static void init(int n) {
        parents = new int[n + 1];
        rank = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            parents[i] = i;
            rank[i] = 1;
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
