/**
 * [BOJ] 21924 - 도시 건설
 * - 제출 날짜: 2026년 4월 4일
 * - 결과: 메모리 초과
 */

import java.util.*;
import java.io.*;

class Main {

    final static long INF = 100_000_000_001L;
    
    static long[] costs;
    static int[][] edge;
    static boolean[] visited;
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();
        long total = inputEdge(n, m);
        System.out.print(prim(total, n, 1));
    }

    private static long inputEdge(int n, int m) throws IOException {
        edge = new int[n + 1][n + 1];
        long total = 0;
        while(m-- > 0) {
            int a = readInt();
            int b = readInt();
            int k = readInt();

            edge[a][b] = k;
            edge[b][a] = k;
            total += k;
        }
        return total;
    }

    private static long prim(long total, int n, int idx) {
        long[] costs = new long[n + 1];
        for(int i = 1; i <= n; i++)
            costs[i] = INF;
        costs[idx] = 0;
        
        visited = new boolean[n + 1];
        int cnt = 0;
        
        while(cnt < n) {
            long min = INF;
            int u = -1;
            for(int i = 1; i <= n; i++) {
                if(!visited[i] && costs[i] < min) {
                    min = costs[i];
                    u = i;
                }
            }

            if(u == -1) return -1;

            visited[u] = true;
            total -= min;
            cnt++;
            
            for(int v = 1; v <= n; v++) {
                if(!visited[v] && edge[u][v] != 0 && edge[u][v] < costs[v]) {
                    costs[v] = edge[u][v];
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