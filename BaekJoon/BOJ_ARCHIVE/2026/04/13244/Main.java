/**
 * [BOJ] 13244 - Tree
 * - 제출 날짜: 2026년 4월 27일
 * - 결과: 맞았습니다!!
 * - 메모리: 27520 KB
 * - 시간: 84 ms
 */

import java.util.*;
import java.io.*;

public class Main {

    static int c;
    static int[] headArr = new int[1001];
    static int[] nextArr = new int[2000005];
    static int[] toArr = new int[2000005];
    static int edgeIdx;
    
    static int[] que = new int[1001];
    static int[] visited = new int[1001];

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int T = readInt();
        for(int t = 1; t <= T; t++) {
            int n = readInt();
            int m = readInt();
            
            // 초기화
            edgeIdx = 0;
            for(int i = 1; i <= n; i++) {
                headArr[i] = -1;
                visited[i] = 0;
            }

            for(int i = 0; i < m; i++) {
                int u = readInt();
                int v = readInt();
                addEdge(u, v);
                addEdge(v, u);
            }

            if (n - 1 == m) {
                sb.append(bfs(n, t) ? "tree" : "graph").append('\n');
            } else {
                sb.append("graph").append('\n');
            }
        }
        System.out.print(sb);
    }

    private static void addEdge(int u, int v) {
        toArr[edgeIdx] = v;
        nextArr[edgeIdx] = headArr[u];
        headArr[u] = edgeIdx++;
    }

    private static boolean bfs(int n, int t) {
        int qHead = 0, qTail = 0;
        que[qTail++] = 1;
        visited[1] = t;
        int count = 1;

        while(qHead < qTail) {
            int cur = que[qHead++];
            for(int i = headArr[cur]; i != -1; i = nextArr[i]) {
                int next = toArr[i];
                if(visited[next] < t) {
                    visited[next] = t;
                    que[qTail++] = next;
                    count++;
                }
            }
        }
        return count == n;
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