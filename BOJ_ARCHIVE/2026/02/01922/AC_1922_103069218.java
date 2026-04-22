/**
 * [BOJ] 1922 - 네트워크 연결
 * - 제출 날짜: 2026년 2월 18일
 * - 결과: 맞았습니다!!
 * - 메모리: 19188 KB
 * - 시간: 244 ms
 */

import java.io.*;
import java.util.*;

public class Main {

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

    static ArrayList<Edge> edge;
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
        edge = new ArrayList<>();

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
            edge.add(new Edge(c1, c2, w));
        }
        Collections.sort(edge);
    }

    private static int solve() {
        int cost = 0, edgeCnt = 0, idx = 0;
        while(edgeCnt < n - 1) {
            Edge e = edge.get(idx++);
            int c1 = e.from;
            int c2 = e.to;
            int w = e.w;

            if(union(c1, c2)) {
                cost += w;
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
