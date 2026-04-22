/**
 * [BOJ] 16118 - 달빛 여우
 * - 제출 날짜: 2026년 3월 15일
 * - 결과: 맞았습니다!!
 * - 메모리: 44612 KB
 * - 시간: 556 ms
 */

import java.io.*;
import java.util.*;

public class Main {

    final static int MAX_RANGE = 800_000_000;
    static ArrayList<int[]>[] edge;
    static int[][] dist;

    static FastScanner fs = new FastScanner();

    public static void main(String[] args) throws Exception {
        int n = fs.nextInt();
        int m = fs.nextInt();

        init(n);
        inputEdge(m);
        dijkstra(1);
        System.out.println(count(n));
    }

    private static void init(int n) {
        edge = new ArrayList[n + 1];
        dist = new int[n + 1][3];

        for(int i = 1; i <= n; i++) {
            edge[i] = new ArrayList<>();
            dist[i][0] = MAX_RANGE;
            dist[i][1] = MAX_RANGE;
            dist[i][2] = MAX_RANGE;
        }
    }

    private static void inputEdge(int m) throws Exception {
        while(m-- > 0) {
            int v1 = fs.nextInt();
            int v2 = fs.nextInt();
            int w = fs.nextInt() << 1;

            edge[v1].add(new int[]{v2, w});
            edge[v2].add(new int[]{v1, w});
        }
    }

    private static void dijkstra(int s) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.add(new int[]{s, 0, 0});
        pq.add(new int[]{s, 0, 2});
        dist[s][0] = dist[s][2] = 0;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int from = cur[0];
            int d = cur[1];
            int toggle = cur[2];

            if(dist[from][toggle] < d) continue;

            for(int[] next : edge[from]) {
                if(toggle == 2) {
                    int nextD = d + next[1];
                    if(dist[next[0]][toggle] > nextD) {
                        pq.add(new int[]{next[0], nextD, toggle});
                        dist[next[0]][toggle] = nextD;
                    }
                } else {
                    int nextD = d + ((toggle & 1) == 0 ? next[1] >> 1 : next[1] << 1);
                    if(dist[next[0]][toggle ^ 1] > nextD) {
                        pq.add(new int[]{next[0], nextD, toggle ^ 1});
                        dist[next[0]][toggle ^ 1] = nextD;
                    }
                }
            }
        }
    }

    private static int count(int n) {
        int cnt = 0;
        for(int i = 1; i <= n; i++) {
            if(dist[i][2] < Math.min(dist[i][0], dist[i][1])) cnt++;
        }
        return cnt;
    }

    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int read() throws IOException {
            if(ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if(len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            while((c = read()) <= ' ') if(c == -1) return -1;

            int n = 0;
            while(c >= '0' && c <= '9') {
                n = (n << 3) + (n << 1) + (c & 15);
                c = read();
            }
            return n;
        }
    }
}