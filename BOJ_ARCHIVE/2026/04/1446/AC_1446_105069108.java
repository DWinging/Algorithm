/**
 * [BOJ] 1446 - 지름길
 * - 제출 날짜: 2026년 4월 17일
 * - 결과: 맞았습니다!!
 * - 메모리: 11812 KB
 * - 시간: 68 ms
 */

import java.util.*;
import java.io.*;

class Main {

    private static class Node implements Comparable<Node> {
        int to, dist;

        public Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node n) {
            return Integer.compare(this.dist, n.dist);
        }
    }

    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int d = readInt();

        List<Node>[] graph = new ArrayList[d + 1];
        for (int i = 0; i <= d; i++) {
            graph[i] = new ArrayList<>();
            if (i < d) {
                graph[i].add(new Node(i + 1, 1));
            }
        }

        for (int i = 0; i < n; i++) {
            int s = readInt();
            int e = readInt();
            int dist = readInt();

            if (e <= d && (e - s) > dist) {
                graph[s].add(new Node(e, dist));
            }
        }

        System.out.println(dijkstra(d, graph));
    }

    private static int dijkstra(int d, List<Node>[] graph) {
        int[] dist = new int[d + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        dist[0] = 0;
        pq.add(new Node(0, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.dist > dist[cur.to]) continue;

            for (Node next : graph[cur.to]) {
                if (dist[next.to] > dist[cur.to] + next.dist) {
                    dist[next.to] = dist[cur.to] + next.dist;
                    pq.add(new Node(next.to, dist[next.to]));
                }
            }
        }
        
        return dist[d];
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