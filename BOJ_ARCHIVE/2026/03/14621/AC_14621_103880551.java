/**
 * [BOJ] 14621 - 나만 안되는 연애
 * - 제출 날짜: 2026년 3월 14일
 * - 결과: 맞았습니다!!
 * - 메모리: 17048 KB
 * - 시간: 116 ms
 */

import java.io.*;
import java.util.*;

public class Main {

    static int[][] edge;
    static int[] univ;
    static int[] dist;
    static boolean[] visited;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();
        init(n);
        System.out.println(solve(n, m));
    }

    private static void init(int n) throws IOException {
        univ = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            while(c <= ' ') c = System.in.read();
            univ[i] = c;
            c = System.in.read();
        }
    }

    private static int solve(int n, int m) throws IOException {

        edge = new int[n + 1][n + 1];

        while(m-- > 0) {
            int u1 = readInt();
            int u2 = readInt();
            int w = readInt();

            if(univ[u1] != univ[u2]) {
                if(edge[u1][u2] == 0 || edge[u1][u2] > w) {
                    edge[u1][u2] = w;
                    edge[u2][u1] = w;
                }
            }
        }

        visited = new boolean[n + 1];
        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        int res = 0; dist[1] = 0;

        for(int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            int v = -1;
            for(int j = 1; j <= n; j++) {
                if(!visited[j] && dist[j] < min) {
                    min = dist[j];
                    v = j;
                }
            }
            if(v == -1) return -1;
            visited[v] = true;
            res += min;

            for(int j = 1; j <= n; j++) {
                if(!visited[j] && edge[v][j] != 0 && dist[j] > edge[v][j]) {
                    dist[j] = edge[v][j];
                }
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