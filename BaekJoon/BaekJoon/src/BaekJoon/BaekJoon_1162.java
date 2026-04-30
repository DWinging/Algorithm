package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_1162 {

    private static class Node implements Comparable<Node> {
        int v, cnt;
        long time;

        public Node(int v, int cnt, long time) {
            this.v = v;
            this.cnt = cnt;
            this.time = time;
        }

        @Override
        public int compareTo(Node n) {
            if(this.time == n.time) return Integer.compare(this.cnt, n.cnt);
            return Long.compare(this.time, n.time);
        }
    }

    final static long INF = 10_000_000_005L;
    static ArrayList<int[]>[] edge;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();
        int k = readInt();
        inputEdge(n, m);
        System.out.println(dijkstra(n, k));
    }

    private static void inputEdge(int n, int m) throws IOException {
        edge = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            edge[i] = new ArrayList<>();
        }

        while(m-- > 0) {
            int r1 = readInt();
            int r2 = readInt();
            int time = readInt();
            edge[r1].add(new int[]{r2, time});
            edge[r2].add(new int[]{r1, time});
        }
    }

    private static long dijkstra(int n, int k) {
        long[][] visited = new long[n + 1][k + 1];
        for(int i = 2; i <= n; i++) Arrays.fill(visited[i], INF);
        Arrays.fill(visited[1], 0);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0, 0));
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int from = cur.v;
            int cnt = cur.cnt;
            long time = cur.time;
            if(visited[from][cnt] < time) continue;
            if(from == n) return time;

            for(int[] next : edge[from]) {
                int to = next[0];
                long nextTime = time + next[1];

                if(cnt + 1 <= k) {
                    if(visited[to][cnt + 1] > time) {
                        pq.add(new Node(to, cnt + 1, time));
                        visited[to][cnt + 1] = time;
                    }
                }
                if(visited[to][cnt] > nextTime) {
                    pq.add(new Node(to, cnt, nextTime));
                    visited[to][cnt] = nextTime;
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
}
