/**
 * [BOJ] 10423 - 전기가 부족해
 * - 제출 날짜: 2026년 3월 11일
 * - 결과: 맞았습니다!!
 * - 메모리: 30372 KB
 * - 시간: 320 ms
 */

import java.io.*;
import java.util.*;

class Main {

    static PriorityQueue<int[]> pq;
    static ArrayList<int[]>[] edge;
    static boolean[] visited;
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();
        int k = readInt();

        init(n, k);
        inputEdge(m);
        System.out.println(solve(n, k));
    }

    private static void init(int n, int k) throws IOException {
        pq = new PriorityQueue<>((l1, l2) -> Integer.compare(l1[1], l2[1]));

        edge = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) 
            edge[i] = new ArrayList<>();
        
        visited = new boolean[n + 1];
        while(k-- > 0) {
            int v = readInt();
            pq.add(new int[]{v, 0});
        }
    }

    private static void inputEdge(int m) throws IOException {
        while(m-- > 0) {
            int v1 = readInt();
            int v2 = readInt();
            int w = readInt();
            edge[v1].add(new int[]{v2, w});
            edge[v2].add(new int[]{v1, w});
        }
    }

    private static int solve(int n, int k) throws IOException {
        int cnt = n, res = 0;
        while(cnt > 0) {
            int[] node = pq.poll();
            int v = node[0];
            int w = node[1];

            if(visited[v]) continue;
            visited[v] = true;
            res += w;
            cnt--;

            for(int[] next : edge[v]) {
                if(!visited[next[0]]) pq.add(new int[] {next[0], next[1]});
            }
        }
        return res;
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