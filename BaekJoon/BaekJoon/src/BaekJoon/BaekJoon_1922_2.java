package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_1922_2 {

    private static class Edge implements Comparable<Edge> {
        int from, to, w;
        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Edge e) {
            return this.w - e.w;
        }
    }

    static PriorityQueue<Edge> pq;
    static int[] parents;
    static int c, n;

    public static void main(String[] args) throws IOException {
        init();
        inputEdge();
        System.out.println(solve());
    }

    private static void init() throws IOException {
        c = System.in.read();
        n = readInt();
        pq = new PriorityQueue<>();

        parents = new int[n + 1];
        for(int i = 1; i <= n; i++) parents[i] = i;
    }

    private static void inputEdge() throws IOException {
        int m = readInt();
        int c1, c2, w;
        while(m-- > 0) {
            c1 = readInt();
            c2 = readInt();
            w = readInt();
            pq.add(new Edge(c1, c2, w));
        }
    }

    private static int solve() {
        int cost = 0, edgeCnt = 0;
        while(edgeCnt < n - 1) {
            Edge e = pq.poll();
            if(union(e.from, e.to)) {
                cost += e.w;
                edgeCnt++;
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
