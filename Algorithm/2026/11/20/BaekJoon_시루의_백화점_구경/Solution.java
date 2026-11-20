import java.io.*;

class Main {

    static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();

        int n = readInt();
        int m = readInt();
        int k = readInt();

        int[][] map = new int[n][m];

        int sy = 0, sx = 0;
        for(int y = 0; y < n; y++) {
            for(int x = 0; x < m; x++) {
                map[y][x] = readInt();
                if(map[y][x] == 4) {
                    sy = y;
                    sx = x;
                }
            }
        }

        marking(map, n, m, k);
        System.out.println(solve(map, n, m, sy, sx));
    }

    private static void marking(int[][] map, int n, int m, int k) {
        int[] que = new int[n * m];
        int head = 0, tail = 0;

        boolean[][] visited = new boolean[n][m];

        for(int y = 0; y < n; y++) {
            for(int x = 0; x < m; x++) {
                if(map[y][x] == 3) {
                    que[tail++] = y * m + x;
                    visited[y][x] = true;
                    map[y][x] = 1;
                }
            }
        }

        for(int dist = 0; dist < k && head < tail; dist++) {
            int size = tail - head;

            while(size-- > 0) {
                int pos = que[head++];
                int cy = pos / m;
                int cx = pos % m;

                for(int[] d : DIRS) {
                    int ny = cy + d[0];
                    int nx = cx + d[1];

                    if(check(ny, nx, n, m) && !visited[ny][nx]) {
                        visited[ny][nx] = true;
                        map[ny][nx] = 1;
                        que[tail++] = ny * m + nx;
                    }
                }
            }
        }
    }

    private static int solve(int[][] map, int n, int m, int y, int x) {
        int[] que = new int[n * m];
        int head = 0, tail = 0;

        boolean[][] visited = new boolean[n][m];

        que[tail++] = y * m + x;
        visited[y][x] = true;

        int dist = 0;

        while(head < tail) {
            int size = tail - head;

            while(size-- > 0) {
                int pos = que[head++];
                int cy = pos / m;
                int cx = pos % m;

                if(map[cy][cx] == 2) return dist;

                for(int[] d : DIRS) {
                    int ny = cy + d[0];
                    int nx = cx + d[1];

                    if(check(ny, nx, n, m) && !visited[ny][nx] && map[ny][nx] != 1) {
                        visited[ny][nx] = true;
                        que[tail++] = ny * m + nx;
                    }
                }
            }

            dist++;
        }

        return -1;
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