package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_10838 {

    static Set<Integer> set = new HashSet<>();
    static long[] visited;
    static int[] parents;
    static int[] color;
    static long ver = 1;
    static int input;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        input = System.in.read();

        int n = readInt();
        int k = readInt();

        init(n);

        while(k-- > 0) {
            int command = readInt();
            if(command == 1) paint();
            else if(command == 2) move();
            else sb.append(count()).append('\n');
        }
        System.out.print(sb);
    }

    private static void init(int n) {
        visited = new long[n];
        parents = new int[n];
        color = new int[n];
        parents[0] = -1;
    }

    private static void paint() throws IOException {
        int a = readInt();
        int b = readInt();
        int c = readInt();

        int lca = searchLCA(a, b);
        paintColor(a, lca, c);
        paintColor(b, lca, c);
    }

    private static void paintColor(int n, int lca, int c) {
        while(n != lca) {
            color[n] = c;
            n = parents[n];
        }
    }

    private static void move() throws IOException {
        int a = readInt();
        int b = readInt();
        parents[a] = b;
    }

    private static int count() throws IOException {
        int a = readInt();
        int b = readInt();

        int lca = searchLCA(a, b);

        set.clear();
        countColor(a, lca);
        countColor(b, lca);
        return set.size();
    }

    private static void countColor(int n, int lca) {
        while(n != lca) {
            set.add(color[n]);
            n = parents[n];
        }
    }

    private static int searchLCA(int a, int b) {
        int lca;
        while(true) {
            if(a != -1) {
                if(visited[a] == ver) {
                    lca = a;
                    break;
                } else {
                    visited[a] = ver;
                    a = parents[a];
                }
            }

            if(b != -1) {
                if(visited[b] == ver) {
                    lca = b;
                    break;
                } else {
                    visited[b] = ver;
                    b = parents[b];
                }
            }
        }
        ver++;
        return lca;
    }

    private static int readInt() throws IOException {
        while(input <= ' ') input = System.in.read();
        int n = 0;
        while(input >= '0' && input <= '9') {
            n = (n << 3) + (n << 1) + (input - '0');
            input = System.in.read();
        }
        return n;
    }
}
