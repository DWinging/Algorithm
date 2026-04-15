package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_1939 {

    static int[][] edge;
    static int[] parents;
    static int c, N;
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        N = readInt();
        init();
        int s = readInt();
        int e = readInt();
        System.out.println(solve(s, e));
    }

    private static void init() throws IOException {
        parents = new int[N + 1];
        for(int i = 1; i <= N; i++) parents[i] = i;

        int m = readInt();
        edge = new int[m][3];
        for(int i = 0; i < m; i++) {
            edge[i][0] = readInt();
            edge[i][1] = readInt();
            edge[i][2] = readInt();
        }
        Arrays.sort(edge, (l1, l2) -> Integer.compare(l2[2], l1[2]));
    }

    private static int solve(int s, int e) {
        for(int[] cur : edge) {
            int p1 = find(cur[0]);
            int p2 = find(cur[1]);
            int w = cur[2];

            if(p1 != p2) {
                parents[p1] = p2;
                if(find(s) == find(e)) return w;
            }
        }
        return -1;
    }

    private static int find(int p) {
        if(parents[p] == p) return p;
        return parents[p] = find(parents[p]);
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

