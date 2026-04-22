/**
 * [BOJ] 20183 - 골목 대장 호석 - 효율성 2
 * - 제출 날짜: 2026년 2월 28일
 * - 결과: 맞았습니다!! (43/43)
 * - 메모리: 64080 KB
 * - 시간: 616 ms
 */

import java.io.*;
import java.util.*;

public class Main {

    private static class Node implements Comparable<Node> {
        int v, max;
        long cost;

        Node(int v, int max, long cost) {
            this.v = v;
            this.max = max;
            this.cost = cost;
        }

        public int compareTo(Node node) {
            if(this.max != node.max) return Long.compare(this.max, node.max);
            return Long.compare(this.cost, node.cost);
        }
    }

    static ArrayList<int[]>[] edge;
    static int c, INF = 1_000_000_005;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();
        int start = readInt();
        int end = readInt();
        long total = readLong();

        inputEdge(n, m);
        System.out.print(dijkstra(n, start, end, total));
    }

    private static void inputEdge(int n, int m) throws IOException {
        edge = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) edge[i] = new ArrayList<>();
        while(m-- > 0) {
            int v1 = readInt();
            int v2 = readInt();
            int cost = readInt();
            edge[v1].add(new int[]{v2, cost});
            edge[v2].add(new int[]{v1, cost});
        }
    }

    private static int dijkstra(int n, int start, int end, long total) {
        int[] visited = new int[n + 1];
        Arrays.fill(visited, INF);
        visited[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0, 0));
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int from = cur.v;
            int max = cur.max;
            long cost = cur.cost;

            if(visited[from] < max ) continue;
            if(from == end) return max;

            for(int[] next : edge[from]) {
                int to = next[0];
                int maxN = Math.max(max, next[1]);
                long costN = cost + next[1];
                if(costN <= total && visited[to] > maxN) {
                    visited[to] = maxN;
                    pq.add(new Node(to, maxN, costN));
                }
            }
        }

        return -1;
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

    private static long readLong() throws IOException {
        while(c <= ' ') c = System.in.read();
        long n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c - '0');
            c = System.in.read();
        }
        return n;
    }
}
