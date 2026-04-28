/**
 * [BOJ] 13244 - Tree
 * - 제출 날짜: 2026년 4월 27일
 * - 결과: 메모리 초과
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

    final static int SIZE = 1000;
    static int[][] edges = new int[SIZE + 1][1000005];
    static int[] que = new int[SIZE + 1];
    static int[] visited = new int[SIZE + 1];
    static int c;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int T = readInt();
        for(int t = 1; t <= T; t++) {
            int n = readInt();
            int m = readInt();
            inputEdge(n, m);

            if (n - 1 == m) {
                sb.append(bfs(n, t) ? "tree" : "graph").append('\n');
            } else {
                sb.append("graph").append('\n');
            }
        }
        System.out.println(sb);
    }

    private static void inputEdge(int n, int m) throws IOException {
        for(int i = 1; i <= n; i++) {
            edges[i][0] = 0;
            visited[i] = 0;
        }

        while(m-- > 0) {
            int e1 = readInt();
            int e2 = readInt();
            edges[e1][++edges[e1][0]] = e2;
            edges[e2][++edges[e2][0]] = e1;
        }
    }

    private static boolean bfs(int n, int t) {
        int head = 0, tail = 0;
        que[tail++] = 1;
        visited[1] = t;

        while(head < tail) {
            int cur = que[head++];
            int size = edges[cur][0];

            for(int i = 1; i <= size; i++) {
                int next = edges[cur][i];
                if(visited[next] < t) {
                    que[tail++] = next;
                    visited[next] = t;
                }
            }
        }

        return tail == n;
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
