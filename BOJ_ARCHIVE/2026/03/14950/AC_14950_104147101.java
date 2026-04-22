/**
 * [BOJ] 14950 - 정복자
 * - 제출 날짜: 2026년 3월 21일
 * - 결과: 맞았습니다!!
 * - 메모리: 20972 KB
 * - 시간: 280 ms
 */

import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<int[]>[] edge;
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
            int cost = readInt();

            edge[v1].add(new int[]{v2, cost});
            edge[v2].add(new int[]{v1, cost});
        }
    }

    private static long solve(int s, int t) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        long total = 0, w = 0; int cnt = 0;

        visited[s] = true;
        for(int[] next : edge[s]) {
            pq.add(next);
        }

        while(cnt < N - 1) {
            int[] cur = pq.poll();
            int to = cur[0];
            int cost = cur[1];

            if(visited[to]) continue;
            visited[to] = true;
            total += cost + (w * t);
            cnt++;
            w++;

            for(int[] next : edge[to]) {
                if(!visited[next[0]]) {
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
