/**
 * [BOJ] 1854 - K번째 최단경로 찾기
 * - 제출 날짜: 2026년 2월 26일
 * - 결과: 틀렸습니다
 */

import java.util.*;
import java.io.*;

class Main {

    private static class Node {
        int to, cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static ArrayList<Node>[] edge;
    static PriorityQueue<Integer>[] visited;
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();
        int k = readInt();

        init(n);
        inputEdge(m);
        dijkstra(1, k);
        System.out.print(buildString(n, k));
    }

    private static void init(int n) {
        edge = new ArrayList[n + 1];
        visited = new PriorityQueue[n+ 1];
        for(int i = 1; i <= n; i++) {
            edge[i] = new ArrayList<>();
            visited[i] = new PriorityQueue<>((n1, n2) -> Integer.compare(n2, n1));
        }
    }

    private static void inputEdge(int m) throws IOException {
        while(m-- > 0) {
            int from = readInt();
            int to = readInt();
            int cost = readInt();
            edge[from].add(new Node(to, cost));
        }
    }

    private static void dijkstra(int start, int k) {
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.cost, n2.cost));
        pq.add(new Node(start, 0));
        visited[start].add(0);
        
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int from = cur.to;
            int cost = cur.cost;

            if(visited[from].size() == k && cost > visited[from].peek()) continue;

            for(Node node : edge[from]) {
                int next = node.to;
                int nextCost = node.cost + cost;
                if(visited[next].size() < k) {
                    visited[next].add(nextCost);
                    pq.add(new Node(next, nextCost));
                } else {
                    visited[next].add(nextCost + cost);
                    visited[next].poll();
                    pq.add(new Node(next, nextCost + cost));    
                }                
            }
        }
    }

    private static String buildString(int n, int k) {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            int value = visited[i].size() == k ? visited[i].peek() : -1;
            sb.append(value).append('\n');
        }
        return sb.toString();
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