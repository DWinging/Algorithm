/**
 * [BOJ] 1260 - DFS와 BFS
 * - 제출 날짜: 2026년 2월 17일
 * - 결과: 맞았습니다!!
 * - 메모리: 93912 KB
 * - 시간: 180 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static boolean[][] graph;
    static int[] visited, deque;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();

        int n = readInt();
        int m = readInt();
        int v = readInt();

        init(n, m);
        inputEdge(m);

        dfsAndBfs(n, v, 1, true);
        dfsAndBfs(n, v, 2, false);
        System.out.print(sb);
    }

    private static void init(int n, int m) {
        graph = new boolean[n + 1][n + 1];
        visited = new int[n + 1];
        deque = new int[n * m * 2];
    }

    private static void inputEdge(int m) throws IOException {
        while(m-- > 0) {
            int n1 = readInt();
            int n2 = readInt();
            graph[n1][n2] = true;
            graph[n2][n1] = true;
        }
    }

    private static void dfsAndBfs(int n, int v, int ver, boolean flag) {
        int head = 0, tail = flag ? 0 : 1;
        deque[0] = v;
        if(!flag) visited[v] = ver;

        while(flag ? tail > -1 : head < tail) {
            int cur = flag ? deque[tail--] : deque[head++];
            if(flag && visited[cur] == ver) continue;
            if(flag) visited[cur] = ver;
            sb.append(cur).append(' ');

            for(int next = flag ? n : 0; flag ? next >= 0 : next <= n; next = flag ? next - 1 : next + 1) {
                if(cur == next || visited[next] == ver || !graph[cur][next]) continue;
                deque[flag ? ++tail : tail++] = next;
                if(!flag) visited[next] = ver;
            }
        }
        sb.append('\n');
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