/**
 * [BOJ] 1939 - 중량제한
 * - 제출 날짜: 2026년 3월 7일
 * - 결과: 맞았습니다!!
 * - 메모리: 22636 KB
 * - 시간: 276 ms
 */

import java.io.*;
import java.util.*;

public class Main {

    static PriorityQueue<int[]> pq = new PriorityQueue<>((l1, l2) -> Integer.compare(l2[2], l1[2]));
    static int[] parents;
    static int c, N;
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        N = readInt();
        init();
        int s = readInt();
        int e = readInt();
        System.out.println(solve(s, e));
    }

    private static void init() throws IOException {
        parents = new int[N + 1];
        for(int i = 1; i <= N; i++) parents[i] = i;

        int m = readInt();
        while(m-- > 0) {
            int v1 = readInt();
            int v2 = readInt();
            int w = readInt();

            pq.add(new int[]{v1, v2, w});
        }
    }

    private static int solve(int s, int e) {
        int res = 0;
        while(find(s) != find(e)) {
            int[] cur = pq.poll();
            int p1 = find(cur[0]);
            int p2 = find(cur[1]);
            int w = cur[2];

            if(p1 != p2) {
                union(p1, p2);
                res = w;
            }
        }
        return res;
    }

    private static int find(int p) {
        if(parents[p] == p) return p;
        return parents[p] = find(parents[p]);
    }

    private static void union(int p1, int p2) {
        parents[p1] = p2;
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

