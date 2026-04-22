/**
 * [BOJ] 13308 - 주유소
 * - 제출 날짜: 2026년 2월 27일
 * - 결과: 100점
 * - 메모리: 181296 KB
 * - 시간: 1040 ms
 */

import java.util.*;
import java.io.*;

class Main {

    private static class Node implements Comparable<Node> {
        int v, oil; long cost;

        Node(int v, long cost) {
            this.v = v;
            this.cost = cost;
        }

        Node(int v, int oil, long cost) {
            this.v = v;
            this.oil = oil;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            if(this.cost == n.cost) return Integer.compare(this.oil, n.oil);
            return Long.compare(this.cost, n.cost);
        }
    }
    
    static ArrayList<Node>[] edge;
    static int[] oils;
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();
        inputOil(n);    
        inputEdge(n, m);
        System.out.println(dijkstra(1, n));
    }

    private static void inputOil(int n) throws IOException {
        oils = new int[n + 1];
        for(int i = 1; i <= n; i++) oils[i] = readInt();
    }

    private static void inputEdge(int n, int m) throws IOException {
        edge = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) edge[i] = new ArrayList<>();

        while(m-- > 0) {
            int from = readInt();
            int to = readInt();
            int cost = readInt();

            edge[from].add(new Node(to, cost));
            edge[to].add(new Node(from, cost));
        }
    }

    private static long dijkstra(int start, int target) {
        long max = 2500L * 2500 * 2500 + 5;
        long[][] dist = new long[target + 1][2500 + 5];
        for(int i = 1; i <= target; i++) {
            Arrays.fill(dist[i], max);
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, oils[start], 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int from = cur.v;
            int oil = cur.oil;
            long cost = cur.cost;

            if(dist[from][oil] < cost) continue;
            if(from == target) return cost;
            
            oil = Math.min(cur.oil, oils[from]);
            for(Node to : edge[from]) {
                long toCost = cost + oil * to.cost;
                if(dist[to.v][oil] > toCost) {
                    pq.add(new Node(to.v, oil, toCost));
                    dist[to.v][oil] = toCost;
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