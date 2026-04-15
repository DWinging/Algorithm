package BaekJoon;

import java.io.*;

public class BaekJoon_1368 {

    static int[][] edge;
    static int[] dist;
    static boolean[] visited;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int u = init(n);
        System.out.println(solve(n, u));
    }

    private static int init(int n) throws IOException {
        edge = new int[n][n];
        dist = new int[n];
        visited = new boolean[n];

        int u = 0, v = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            dist[i] = readInt();
            if(v > dist[i]) {
                u = i;
                v = dist[i];
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                edge[i][j] = readInt();
            }
        }
        return u;
    }

    private static int solve(int n, int u) {
        int d = dist[u], cnt = 1;
        visited[u] = true;

        while(cnt < n) {
            for(int i = 0; i < n; i++) {
                if(!visited[i] && dist[i] > edge[u][i]) {
                    dist[i] = edge[u][i];
                }
            }

            int v = Integer.MAX_VALUE;
            for(int i = 0; i < n; i++) {
                if(!visited[i] && v > dist[i]) {
                    u = i;
                    v = dist[i];
                }
            }
            visited[u] = true;
            d += v;
            cnt++;
        }
        return d;
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
