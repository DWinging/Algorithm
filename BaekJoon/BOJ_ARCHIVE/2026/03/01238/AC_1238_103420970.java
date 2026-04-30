/**
 * [BOJ] 1238 - 파티
 * - 제출 날짜: 2026년 3월 2일
 * - 결과: 맞았습니다!!
 * - 메모리: 12764 KB
 * - 시간: 88 ms
 */

import java.io.*;
import java.util.*;

public class Main {

    private static class Node implements Comparable<Node> {
        int v, t;

        Node(int v, int t) {
            this.v = v;
            this.t = t;
        }

        @Override
        public int compareTo(Node node) {
            return Integer.compare(this.t, node.t);
        }
    }

    final static int INF = 100_005;

    static ArrayList<int[]>[] edge, reverseEdge;
    static int[] dict, reverseDict;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();
        int x = readInt();

        inputArray(n, m);
        dijkstra(edge, dict, x);
        dijkstra(reverseEdge, reverseDict, x);
        System.out.println(solve(n));
    }

    private static void inputArray(int n, int m) throws IOException{
        edge = new ArrayList[n + 1];
        reverseEdge = new ArrayList[n + 1];
        dict = new int[n + 1];
        reverseDict = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            edge[i] = new ArrayList<>();
            reverseEdge[i] = new ArrayList<>();
            dict[i] = INF;
            reverseDict[i] = INF;
        }

        while(m-- > 0) {
            int v1 = readInt();
            int v2 = readInt();
            int t = readInt();

            edge[v1].add(new int[] {v2, t});
            reverseEdge[v2].add(new int[] {v1, t});
        }
    }

    private static void dijkstra(ArrayList<int[]>[] edge, int[] dict, int x) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(x, 0));
        dict[x] = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int from = cur.v;
            int time = cur.t;

            if(dict[from] < time) continue;

            for(int[] next : edge[from]) {
                int to = next[0];
                int t = time + next[1];

                if(dict[to] > t) {
                    pq.add(new Node(to, t));
                    dict[to] = t;
                }
            }
        }
    }

    private static int solve(int n) {
        int result = 0;
        for(int i = 1; i <= n; i++) {
            int time = dict[i] + reverseDict[i];
            if(result < time) result = time;
        }
        return result;
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
