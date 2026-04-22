/**
 * [BOJ] 14950 - 정복자
 * - 제출 날짜: 2026년 3월 21일
 * - 결과: 맞았습니다!!
 * - 메모리: 14204 KB
 * - 시간: 128 ms
 */

import java.io.*;
import java.util.*;

public class Main {

    static int[] parent;
    static int c;

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int M = readInt();
        int T = readInt();

        long[] edges = new long[M];
        for (int i = 0; i < M; i++) {
            int u = readInt();
            int v = readInt();
            long cost = readInt();
            edges[i] = (cost << 30) | ((long) u << 15) | v;
        }

        Arrays.sort(edges);

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;

        long mstCost = 0;
        int edgeCount = 0;

        for (long edge : edges) {
            int u = (int) (edge >> 15) & 0x7FFF;
            int v = (int) (edge & 0x7FFF);
            int cost = (int) (edge >> 30);

            if (find(u) != find(v)) {
                union(u, v);
                mstCost += cost;
                if (++edgeCount == N - 1) break;
            }
        }

        long extraCost = (long) (N - 1) * (N - 2) / 2 * T;
        System.out.println(mstCost + extraCost);
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) parent[b] = a;
    }

    private static int readInt() throws IOException {
        if (c <= ' ') c = System.in.read();
        while (c <= ' ') c = System.in.read();
        int n = 0;
        while (c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}