package BJ_13308;

import java.util.*;
import java.io.*;
/**
 * 2026년 2월 27일 풀이
 * BaekJoon_13308 주유소
 * 메모리 114540 KB
 * 시간 1900 ms
 */
class Main {
    private static class Node implements Comparable<Node> {
        int v, oil;
        long cost;

        Node(int v, int oil, long cost) {
            this.v = v;
            this.oil = oil;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return Long.compare(this.cost, n.cost);
        }
    }

    static ArrayList<int[]>[] edge;
    static int[] oils;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();

        oils = new int[n + 1];
        for (int i = 1; i <= n; i++) oils[i] = readInt();

        edge = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) edge[i] = new ArrayList<>();

        while (m-- > 0) {
            int u = readInt();
            int v = readInt();
            int w = readInt();
            edge[u].add(new int[]{v, w});
            edge[v].add(new int[]{u, w});
        }

        System.out.println(dijkstra(1, n));
    }

    private static long dijkstra(int start, int target) {
        int MAX_OIL = 2500;
        long[][] dist = new long[target + 1][MAX_OIL + 1];
        long INF = Long.MAX_VALUE;
        for (int i = 1; i <= target; i++) Arrays.fill(dist[i], INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start][oils[start]] = 0;
        pq.add(new Node(start, oils[start], 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int u = cur.v;
            int oil = cur.oil;
            long cost = cur.cost;

            if (dist[u][oil] < cost) continue;
            if (u == target) return cost;

            boolean dominated = false;
            for (int p = 1; p < oil; p++) {
                if (dist[u][p] <= cost) {
                    dominated = true;
                    break;
                }
            }
            if (dominated) continue;

            for (int[] next : edge[u]) {
                int v = next[0];
                int weight = next[1];

                long nextCost = cost + (long)oil * weight;
                int nextOil = Math.min(oil, oils[v]);

                if (dist[v][nextOil] > nextCost) {
                    dist[v][nextOil] = nextCost;
                    pq.add(new Node(v, nextOil, nextCost));
                }
            }
        }
        return -1;
    }

    private static int readInt() throws IOException {
        while (c <= ' ') c = System.in.read();
        int n = 0;
        while (c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c - '0');
            c = System.in.read();
        }
        return n;
    }
}
