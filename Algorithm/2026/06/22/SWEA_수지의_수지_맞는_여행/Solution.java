import java.io.*;

class Solution {

    static final int MAX_SIZE = 20;
    static final int[][] DIST = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    static int[][] arr = new int[MAX_SIZE][MAX_SIZE];
    static int c, res;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int T = readInt();

        for(int t = 1; t <= T; t++) {
            int n = readInt();
            int m = readInt();

            for(int i = 0; i < n; i++) {
                while(c <= ' ') c = System.in.read();
                for(int j = 0; j < m; j++) {
                    arr[i][j] = (1 << (c - 'A'));
                    c = System.in.read();
                }
            }
            res = 0;
            dfs(n, m, 0, 0, arr[0][0], 1);
            sb.append('#').append(t).append(' ').append(res).append('\n');
        }
        System.out.print(sb);
    }

    private static void dfs(int n, int m, int y, int x, int bit, int cnt) {
        if(res < cnt) res = cnt;

        if(res == 26) return;

        for(int[] d : DIST) {
            int ny = y + d[0];
            int nx = x + d[1];

            if(check(ny, nx, n, m) && (bit & arr[ny][nx]) == 0) {
                dfs(n, m, ny, nx, bit | arr[ny][nx], cnt + 1);
            }
        }
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