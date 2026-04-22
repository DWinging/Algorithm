/**
 * [BOJ] 17835 - 면접보는 승범이네
 * - 제출 날짜: 2026년 2월 25일
 * - 결과: 맞았습니다!!
 * - 메모리: 44536 KB
 * - 시간: 688 ms
 */

import java.io.*;
import java.util.*;

class Main {

    private static class Node implements Comparable<Node> {
        int idx;
        long len;

        Node(int idx, long len) {
            this.idx = idx;
            this.len = len;
        }

        @Override
        public int compareTo(Node n) {
            return Long.compare(this.len, n.len);
        }
    }

    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static ArrayList<Node>[] road;
    static long cost[];
    static boolean[] visited;
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();
        int k = readInt();

        init(n);
        inputRoad(m);
        inputCity(k);
        System.out.println(dijkstra(k));
    }

    private static void init(int n) {
        cost = new long[n + 1];
        visited = new boolean[n + 1];
        road = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) road[i] = new ArrayList<>();
    }

    private static void inputRoad(int m) throws IOException {
        while(m-- > 0) {
            int u = readInt();
            int v = readInt();
            int r = readInt();
            road[v].add(new Node(u, r));
        }
    }

    private static void inputCity(int k) throws IOException {
        while(k-- > 0) {
            int idx = readInt();
            pq.add(new Node(idx, 0));
            visited[idx] = true;
        }
    }
    
    private static String dijkstra(int k) {
        int result = k + 1; long maxLen = 0;
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int cur = node.idx;
            long l = node.len;

            if(l > cost[cur]) continue;
            if(l > maxLen) {
                result = cur;
                maxLen = l;
            } else if(l == maxLen) {
                result = Math.min(result, cur);
            }
            
            for(Node next : road[cur]) {
                if(!visited[next.idx] || (visited[next.idx] && cost[next.idx] > l + next.len)) {
                    pq.add(new Node(next.idx, l + next.len));
                    visited[next.idx] = true;
                    cost[next.idx] = l + next.len;
                }
            }
        }
        return result + "\n" + maxLen;
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