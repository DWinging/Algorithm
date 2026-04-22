/**
 * [BOJ] 21924 - 도시 건설
 * - 제출 날짜: 2026년 4월 4일
 * - 결과: 맞았습니다!!
 * - 메모리: 32148 KB
 * - 시간: 576 ms
 */

import java.util.*;
import java.io.*;

class Main {

    private static class Node implements Comparable<Node> {
        int a, b, cost;

        Node(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return Integer.compare(this.cost, n.cost);
        }
    }

    final static long INF = 100_000_000_001L;
    
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static int[] parents, rank;
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();
        init(n);
        long total = inputEdge(n, m);
        System.out.print(kruskal(total, n));
    }

    private static void init(int n) {
        parents = new int[n + 1];
        rank = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            parents[i] = i;
        }
    }

    private static long inputEdge(int n, int m) throws IOException {
        long total = 0;
        while(m-- > 0) {
            int a = readInt();
            int b = readInt();
            int k = readInt();
            pq.add(new Node(a, b, k));
            total += k;
        }
        return total;
    }

    private static long kruskal(long total, int n) {
        int cnt = 0;
        while(!pq.isEmpty() && cnt < n - 1) {
            Node cur = pq.poll();
            int pA = find(cur.a);
            int pB = find(cur.b);
            
            if(pA == pB) continue;

            union(pA, pB);
            total -= cur.cost;
            cnt++;
        }
        return cnt == n - 1 ? total : -1;
    }

    private static int find(int x) {
        int root = x;
    
        while (parents[root] != root) {
            root = parents[root];
        }
    
        while (x != root) {
            int next = parents[x];
            parents[x] = root;
            x = next;
        }
    
        return root;
    }

    private static void union(int a, int b) {
        if(rank[a] < rank[b]) {
            parents[a] = b;
        } else {
            parents[b] = a;
            if(rank[a] == rank[b]) rank[a]++;
        }
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