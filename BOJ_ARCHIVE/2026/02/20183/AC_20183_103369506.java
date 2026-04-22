/**
 * [BOJ] 20183 - 골목 대장 호석 - 효율성 2
 * - 제출 날짜: 2026년 2월 28일
 * - 결과: 맞았습니다!! (43/43)
 * - 메모리: 205168 KB
 * - 시간: 1276 ms
 */

import java.io.*;
import java.util.*;

public class Main {

    private static class Node implements Comparable<Node> {
        int v;
        long cost;

        Node(int v, long cost) {
            this.v = v;
            this.cost = cost;
        }

        public int compareTo(Node node) {
            return Long.compare(this.cost, node.cost);
        }
    }

    static ArrayList<int[]>[] edge;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();
        int start = readInt();
        int end = readInt();
        long total = readLong();

        int max = inputEdge(n, m);
        System.out.print(solve(n, start, end, max, total));
    }

    private static int inputEdge(int n, int m) throws IOException {
        int max = 0;
        edge = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) edge[i] = new ArrayList<>();
        while(m-- > 0) {
            int v1 = readInt();
            int v2 = readInt();
            int cost = readInt();
            edge[v1].add(new int[]{v2, cost});
            edge[v2].add(new int[]{v1, cost});
            max = Math.max(max, cost);
        }
        return max;
    }

    private static int solve(int n, int start, int end, int max, long total) {
        int left = 1, right = max, result = -1;
        while(left <= right) {
            int mid = (left + right) / 2;

            if(dijkstra(n, start, end, mid, total)) {
                result = mid;
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        return result;
    }

    private static boolean dijkstra(int n, int start, int end, int ceiling, long total) {
        long[] visited = new long[n + 1];
        Arrays.fill(visited, total + 1);
        visited[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int from = cur.v;
            long cost = cur.cost;
            if(from == end) return true;
            if(visited[from] < cost) continue;

            for(int[] next : edge[from]) {
                if(next[1] > ceiling) continue;
                int to = next[0];
                long costN = cost + next[1];
                if (costN <= total && costN < visited[to]) {
                    visited[to] = costN;
                    pq.add(new Node(to, costN));
                }
            }
        }
        return false;
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
