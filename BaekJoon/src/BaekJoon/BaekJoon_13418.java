package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_13418 {

    static ArrayList<int[]>[] edge;
    static int[][] dist;
    static boolean[][] visited;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();
        init(n);
        inputEdge(m);
        System.out.println(solve(n));
    }

    private static void init(int n) {
        edge = new ArrayList[n + 1];
        dist = new int[2][n + 1];
        visited = new boolean[2][n + 1];
        for(int i = 0; i <= n; i++) {
            edge[i] = new ArrayList<>();
            dist[0][i] = 2;
            dist[1][i] = -1;
        }
    }

    private static void inputEdge(int m) throws IOException {
        while(m-- >= 0) {
            int v1 = readInt();
            int v2 = readInt();
            int type = readInt() ^ 1;

            edge[v1].add(new int[]{v2, type});
            edge[v2].add(new int[]{v1, type});
        }
    }

    private static int solve(int n) {
        int easy = 0, u_easy = 0, cnt = 0;
        int hard = 0, u_hard = 0;
        visited[0][0] = true;
        visited[1][0] = true;

        while(cnt < n) {
            updateQuery(0, u_easy);
            updateQuery(1, u_hard);

            u_easy = nextNode(u_easy, 2, n, 0);
            u_hard = nextNode(u_hard, -1, n, 1);

            visited[0][u_easy] = true;
            easy += dist[0][u_easy];

            visited[1][u_hard] = true;
            hard += dist[1][u_hard];

            cnt++;
        }

        return (hard * hard) - (easy * easy);
    }

    private static void updateQuery(int type, int u) {
        if(type == 0) {
            for(int[] cur : edge[u]) {
                int next = cur[0];
                int w = cur[1];
                if(!visited[type][next] && dist[type][next] > w)
                    dist[type][next] = w;
            }
        } else {
            for(int[] cur : edge[u]) {
                int next = cur[0];
                int w = cur[1];
                if(!visited[type][next] && dist[type][next] < w)
                    dist[type][next] = w;
            }
        }

    }

    private static int nextNode(int u, int v, int n, int row) {
        if(v == 2) {
            for(int i = 1; i <= n; i++) {
                if(!visited[row][i] && dist[row][i] < v) {
                    v = dist[row][i];
                    u = i;
                }
            }
        } else {
            for(int i = 1; i <= n; i++) {
                if(!visited[row][i] && dist[row][i] > v) {
                    v = dist[row][i];
                    u = i;
                }
            }
        }
        return u;
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
