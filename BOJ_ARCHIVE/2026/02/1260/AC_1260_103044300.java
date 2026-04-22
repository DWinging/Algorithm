/**
 * [BOJ] 1260 - DFS와 BFS
 * - 제출 날짜: 2026년 2월 17일
 * - 결과: 맞았습니다!!
 * - 메모리: 12700 KB
 * - 시간: 92 ms
 */

import java.io.*;
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static ArrayList<Integer>[] list;
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
        list = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        visited = new int[n + 1];
    }

    private static void inputEdge(int m) throws IOException {
        while(m-- > 0) {
            int n1 = readInt();
            int n2 = readInt();
            list[n1].add(n2);
            list[n2].add(n1);
        }
    }

    private static void dfs(int v, int ver) {
        sb.append(v).append(' ');
        visited[v] = ver;

        Collections.sort(list[v]);
        for(int next : list[v]) {
            if(visited[next] == ver) continue;
            dfs(next, ver);
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

            for(int next : list[cur]) {
                if(visited[next] == ver) continue;
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
