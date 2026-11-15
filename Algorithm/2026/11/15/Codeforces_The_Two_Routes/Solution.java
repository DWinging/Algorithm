import java.io.*;

class Main {

    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();

        int n = readInt();
        int m = readInt();

        boolean[][] railway = new boolean[n + 1][n + 1];
        for(int i = 0; i < m; i++) {
            int u = readInt();
            int v = readInt();

            railway[u][v] = railway[v][u] = true;
        }
        
        boolean flag = !railway[1][n];

        System.out.println(bfs(railway, 1, n, flag));
    }

    private static int bfs(boolean[][] railway, int start, int n, boolean flag) {
        int[] que = new int[n + 1];
        int head = 0, tail = 0;
        que[tail++] = start;
        
        int[] visited = new int[n + 1];
        visited[start] = 1;

        while(head < tail) {
            int cur = que[head++];
            int t = visited[cur];

            if(cur == n) return t - 1;

            for(int next = 1; next <= n; next++) {
                if(next != cur && railway[cur][next] == flag && visited[next] == 0) {
                    que[tail++] = next;
                    visited[next] = t + 1;
                }
            }
        }

        return -1;
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