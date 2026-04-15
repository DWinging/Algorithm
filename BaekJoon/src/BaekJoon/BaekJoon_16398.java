package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_16398 {

    private static class Edge implements Comparable<Edge> {
        int from, to, w;

        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(this.w, e.w);
        }
    }

    static PriorityQueue<Edge> edge;
    static int[] parents;
    static int c, n;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }

    private static void init() throws IOException {
        c = System.in.read();
        n = readInt();

        parents = new int[n];
        for(int i = 0; i < n; i++) parents[i] = i;

        edge = new PriorityQueue<>();
        int idx = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j <= i; j++) {
                readInt();
            }
            for(int j = i + 1; j < n; j++) {
                int w = readInt();
                edge.add(new Edge(i, j, w));
            }
        }
    }

    private static long solve() {
        long cost = 0;
        int edgeCnt = 0;

        while(edgeCnt < n - 1) {
            Edge e = edge.poll();
            int n1 = e.from;
            int n2 = e.to;
            int w = e.w;

            if(union(n1, n2)) {
                edgeCnt++;
                cost += w;
            }
        }

        return cost;
    }

    private static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if(pa == pb) return false;
        parents[pa] = pb;
        return true;
    }

    private static int find(int node) {
        if(parents[node] == node) return node;
        return parents[node] = find(parents[node]);
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c - '0');
            c = System.in.read();
        }
        return n;
    }
}
