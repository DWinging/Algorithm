/**
 * [BOJ] 1260 - DFS와 BFS
 * - 제출 날짜: 2026년 2월 17일
 * - 결과: 맞았습니다!!
 * - 메모리: 13976 KB
 * - 시간: 136 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static boolean[][] graph;
    static int[] visited;
    static int c, n;

    public static void main(String[] args) throws IOException {
        c = System.in.read();

        n = readInt();
        int m = readInt();
        int v = readInt();

        init(n);
        inputEdge(m);

        dfs(v, 1);
        bfs(v, 2);
        System.out.print(sb);
    }

    private static void init(int n) {
        graph = new boolean[n + 1][n + 1];
        visited = new int[n + 1];
    }

    private static void inputEdge(int m) throws IOException {
        while(m-- > 0) {
            int n1 = readInt();
            int n2 = readInt();
            graph[n1][n2] = true;
            graph[n2][n1] = true;
        }
    }

    private static void dfs(int v, int ver) {
        sb.append(v).append(' ');
        visited[v] = ver;

        for(int i = 1; i <= n; i++) {
            if(visited[i] == ver || !graph[v][i]) continue;
            dfs(i, ver);
        }
    }

    private static void bfs(int v, int ver) {
        int head = 0, tail = 0;
        int[] deque = new int[n];
        deque[tail++] = v;
        visited[v] = ver;
        sb.append('\n').append(v).append(' ');

        while(head < tail) {
            int cur = deque[head++];

            for(int next = 1; next <= n; next++) {
                if(visited[next] == ver || !graph[cur][next]) continue;
                deque[tail++] = next;
                visited[next] = ver;
                sb.append(next).append(' ');
            }
        }
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
