import java.util.*;
import java.io.*;

class Main {
    
    static int[][] dist = {
        {-1, 0, 0, 0}, 
        {0, -1, -1, 0}, 
        {1, 0, 0, 0}, 
        {0, 1, 0, -1}
    };
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();

        int n = readInt();
        int m = readInt();

        int sy = readInt() - 1;
        int sx = readInt() - 1;

        int y = readInt();
        int x = readInt();

        int[][] map = new int[n][m];
        for(int i = 0; i < n; i++) {
            while(c <= ' ') c = System.in.read();
            for(int j = 0; j < m; j++) {
                map[i][j] = c;
                c = System.in.read();
            }
        }

        System.out.println(bfs(map, n, m, sy, sx, y, x));
    }

    private static int bfs(int[][] map, int n, int m, int sy, int sx, int sl, int sr) {
        Deque<int[]> deque = new ArrayDeque<>();
        deque.addFirst(new int[]{sy, sx, sl, sr});

        int[][] visited = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                visited[i][j] = -1;
            }
        }

        visited[sy][sx] = sl + sr;

        int res = 1;
        while(!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int cy = cur[0];
            int cx = cur[1];
            int cl = cur[2];
            int cr = cur[3];

            if (visited[cy][cx] > cl + cr) continue;

            for(int i = 0; i < 4; i++) {
                int ny = cy + dist[i][0];
                int nx = cx + dist[i][1];
                int nl = cl + dist[i][2];
                int nr = cr + dist[i][3];

                if(check(ny, nx, n, m, nl, nr) && map[ny][nx] == '.' && visited[ny][nx] < nl + nr) {
                    if((i & 1) == 0) {
                        deque.addFirst(new int[]{ny, nx, nl, nr});
                    } else {
                        deque.addLast(new int[]{ny, nx, nl, nr});
                    }
                    if(visited[ny][nx] == -1) res++;
                    visited[ny][nx] = nl + nr;
                }
            }
        }

        return res;
    }

    private static boolean check(int y, int x, int n, int m, int l, int r) {
        return y >= 0 && y < n && x >= 0 && x < m && l >= 0 && r >= 0;
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