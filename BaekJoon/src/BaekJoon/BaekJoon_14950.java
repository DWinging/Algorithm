package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_14950 {

    final static int BIT_SHIFT = 14;

    static ArrayList<Long>[] edge;
    static boolean[] visited;
    static int c, N;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        N = readInt();
        int m = readInt();
        int t = readInt();

        init();
        inputEdge(m);
        System.out.println(solve(1, t));
    }

    private static void init() {
        edge = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for(int i = 1; i <= N; i++) {
            edge[i] = new ArrayList<>();
        }
    }

    private static void inputEdge(int m) throws IOException {
        while(m-- >0) {
            int v1 = readInt();
            int v2 = readInt();
            long cost = readInt();

            edge[v1].add(cost << BIT_SHIFT | v2);
            edge[v2].add(cost << BIT_SHIFT | v1);
        }
    }

    private static long solve(int s, int t) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        long total = 0, w = 0; int cnt = 0;

        visited[s] = true;
        for(long next: edge[s]) {
            pq.add(next);
        }

        while(cnt < N - 1) {
            long cur = pq.poll();
            int to = (int) (cur & ((1 << BIT_SHIFT) - 1));

            if(visited[to]) continue;
            visited[to] = true;
            long cost = (cur >> BIT_SHIFT);
            total += cost + (w * t);
            cnt++;
            w++;

            for(long next: edge[to]) {
                int idx = (int) (next & ((1 << BIT_SHIFT) - 1));
                if(!visited[idx]) {
                    pq.add(next);
                }
            }
        }
        return total;
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
