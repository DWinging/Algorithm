import java.util.*;
import java.io.*;

class Main {

    final static int INF = 200_005;
    
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();

        int n = readInt();
        int m = readInt();

        List<Integer>[] list = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            int u = readInt();
            int v = readInt();

            list[v].add(u);
        }

        int k = readInt();
        int[] route = new int[k];
        for(int i = 0; i < k; i++) {
            route[i] = readInt();
        }

        int[][] dist = new int[n + 1][2];

        bfs(list, dist, n, route[k - 1]);

        System.out.println(solve(dist, route));
    }

    private static void bfs(List<Integer>[] list, int[][] dist, int n, int node) {
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{node, 0});

        for(int i = 0; i < dist.length; i++) {
             dist[i][0] = INF;           
        }

        dist[node][0] = 0;

        while(!que.isEmpty()) {
            int[] cur = que.poll();
            int v = cur[0];
            int t = cur[1];

            for(int next : list[v]) {
                if(dist[next][0] > t + 1) {
                    que.add(new int[]{next, t + 1});
                    dist[next][0] = t + 1;
                    dist[next][1] = 1;
                } else if(dist[next][0] == t + 1) {
                    dist[next][1]++;
                }
            }
        }
    }

    private static String solve(int[][] dist, int[] route) {
        int shortCut = 0;
        int longCut = 0;
        
        for(int i = 0; i < route.length - 1; i++) {
            int v = route[i];
            int next = route[i + 1];

            if(dist[v][0] != dist[next][0] + 1) {
                shortCut++;
                longCut++;
            } else if(dist[v][1] > 1) {
                longCut++;
            }
        }

        return shortCut + " " + longCut;
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