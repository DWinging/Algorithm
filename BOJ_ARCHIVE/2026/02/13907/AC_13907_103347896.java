/**
 * [BOJ] 13907 - 세금
 * - 제출 날짜: 2026년 2월 27일
 * - 결과: 맞았습니다!!
 * - 메모리: 27100 KB
 * - 시간: 252 ms
 */

import java.util.*;
import java.io.*;

class Main {

    private static class Node implements Comparable<Node> {
        int v, cnt, w;

        Node(int v, int cnt, int w) {
            this.v = v;
            this.cnt = cnt;
            this.w = w;
        }

        @Override
        public int compareTo(Node n) {
            if (this.w != n.w) return Long.compare(this.w, n.w);
            return Integer.compare(this.cnt, n.cnt);
        }
    }
    
    static ArrayList<int[]>[] edge;
    static ArrayList<int[]> list = new ArrayList<>();
    static int[][] dist;
    static int c, n, m;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        n = readInt();
        m = readInt();
        int k = readInt();

        int start = readInt();
        int end = readInt();
        
        init();
        inputEdge();
        dijkstra(start, end);
        System.out.println(solve(end, k));
    }

    private static void init() {
        dist = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);

        edge = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) edge[i] = new ArrayList<>();
    }

    private static void inputEdge() throws IOException {
        for(int i = 0; i < m; i++) {
            int n1 = readInt();
            int n2 = readInt();
            int w = readInt();
            edge[n1].add(new int[] {n2, w});
            edge[n2].add(new int[] {n1, w});
        }
    }

    private static void dijkstra(int start, int end) {
        dist[start][0] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int from = cur.v;
            int cnt = cur.cnt;
            int w = cur.w;

            if(dist[from][cnt] < w || check(from, cnt, w)) continue;

            for(int[] next : edge[from]) {
                int to = next[0];
                int cost = next[1] + w;

                if(dist[to][cnt + 1] > cost) {
                    dist[to][cnt + 1] = cost;
                    if(to == end) continue;
                    pq.add(new Node(to, cnt + 1, cost));
                }
            }
        }
    }

    private static boolean check(int from, int cnt, long w) {
        for(int i = 0; i < cnt; i++) {
            if(dist[from][i] < w) return true;
        }
        return false;
    }

    private static String solve(int v, int k) throws IOException {
        StringBuilder sb = new StringBuilder();

        filterValue(v);
        
        long tax = 0;
        sb.append(getMinValue(tax)).append('\n');
        for(int i = 1; i <= k; i++) {
            tax += readInt();
            sb.append(getMinValue(tax)).append('\n');
        }
        return sb.toString();
    }

    private static void filterValue(int v) {
        int minW = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            if(dist[v][i] == Integer.MAX_VALUE) continue;

            if(dist[v][i] < minW) {
                minW = dist[v][i];
                list.add(new int[]{i, minW});
            }            
        }
    }

    private static long getMinValue(long tax) {
        long result = Long.MAX_VALUE;
        for(int[] node : list) {
            int cnt = node[0];
            int w = node[1];
            result = Math.min(result, w + cnt * tax);
        }
        return result;
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int temp = 0;
        while(c >= '0' && c <= '9') {
            temp = (temp << 3) + (temp << 1) + (c - '0'); 
            c = System.in.read();
        }
        return temp;
    }
}