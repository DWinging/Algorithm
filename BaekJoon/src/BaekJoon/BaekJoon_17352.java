package BaekJoon;

import java.io.*;

public class BaekJoon_17352 {

    static int[] parents;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        init(n);
        System.out.println(solve(n));
    }

    private static void init(int n) {
        parents = new int[n + 1];
        for(int i = 1; i <= n; i++) parents[i] = i;
    }

    private static String solve(int n) throws IOException {
        int total = n - 2;
        while(total-- > 0) {
            int a = find(readInt());
            int b = find(readInt());

            if(a < b) parents[b] = a;
            else parents[a] = b;
        }

        return 1 + " " + search(n);
    }

    private static int find(int p) {
        if(parents[p] == p) return p;
        return parents[p] = find(parents[p]);
    }

    private static int search(int n) {
        for(int i = 2; i <= n; i++)
            if(parents[i] == i) return i;
        return -1;
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
