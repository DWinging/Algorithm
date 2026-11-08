import java.util.*;
import java.io.*;

class Main {

    static int[][] dist = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();

        int n = readInt();
        int m = readInt();
        int tc = readInt();

        int[][] map = new int[n][m];
        for(int i = 0; i < n; i++) {
            while(c <= ' ') c = System.in.read();
            for(int j = 0; j < m; j++) {
                map[i][j] = c == '*' ? -1 : 0;
                c = System.in.read();
            }
        }

        StringBuilder sb = new StringBuilder();
        int[] cnt = new int[tc + 1];
        for(int t = 1; t <= tc; t++) {
            int y = readInt() - 1;
            int x = readInt() - 1;

            int res = bfs(map, cnt, y, x, n, m, t);
            sb.append(res).append('\n');
        }
        
        System.out.println(sb);
    }

    private static int bfs(int[][] map, int[] cnt, int y, int x, int n, int m, int tc) {
        if(map[y][x] >= 1) return cnt[map[y][x]];

        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{y, x});
        map[y][x] = tc;

        int res = 0;
        while(!que.isEmpty()) {
            int[] cur = que.poll();
            int cy = cur[0];
            int cx = cur[1];

            for(int[] d : dist) {
                int ny = cy + d[0];
                int nx = cx + d[1];

                if(check(ny, nx, n, m)) {
                    if(map[ny][nx] == -1) res++;
                    else if(map[ny][nx] == 0) {
                        que.add(new int[]{ny, nx});
                        map[ny][nx] = tc;
                    }
                }
            }
        }

        return cnt[tc] = res;
    }

    private static boolean check(int y, int x, int n, int m) {
        return y >= 0 && y < n && x >= 0 && x < m;
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
