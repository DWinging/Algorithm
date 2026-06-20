import java.util.*;
import java.io.*;

class Solution {

    private static class Node {
        int y, x, t;

        public Node(int y, int x, int t) {
            this.y = y;
            this.x = x;
            this.t = t;
        }
    }

    static final int MAX_SIZE = 2_000;
    static final int[][] DIST = {{0, 1}, {1, 0}};

    static Queue<Node> que = new ArrayDeque<>();
    static char[][] arr = new char[MAX_SIZE][MAX_SIZE];
    static char[] res = new char[MAX_SIZE * 2 - 1];
    static int[][] visited = new int[MAX_SIZE][MAX_SIZE];

    static StringBuilder sb = new StringBuilder();
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int T = readInt();

        for(int t = 1; t <= T; t++) {
            int n = readInt();
            int m = readInt();

            for(int i = 0; i < n; i++) {
                inputArray(arr[i], m);
            }

            for(int i = 0; i < n + m - 1; i++) {
                res[i] = 'z';
            }
        
            sb.append('#').append(t).append(' ');
            solve(n, m, t);    
            sb.append('\n');
        }
        System.out.print(sb);
    }

    private static void solve(int n, int m, int mark) {
        que.add(new Node(0, 0, 0));
        visited[0][0] = mark;
        res[0] = arr[0][0];

        while(!que.isEmpty()) {
            Node cur = que.poll();
            int cy = cur.y;
            int cx = cur.x;
            int ct = cur.t;

            if(res[ct] < arr[cy][cx]) continue;

            for(int[] d : DIST) {
                int ny = cy + d[0];
                int nx = cx + d[1];
                int nt = ct + 1;

                if(check(ny, nx, n, m) && visited[ny][nx] < mark) {
                    if(arr[ny][nx] <= res[nt]) {
                        que.add(new Node(ny, nx, nt));
                        res[nt] = arr[ny][nx];
                    }
                    visited[ny][nx] = mark;
                }
            }
        }

        for(int i = 0; i < n + m - 1; i++) {
            sb.append(res[i]);
        }
    }

    private static boolean check(int y, int x, int n, int m) {
        return y >= 0 && y < n && x >= 0 && x < m;
    }

    private static void inputArray(char[] arr, int m) throws IOException {
        while(c <= ' ') c = System.in.read();
        for(int i = 0; i < m; i++) {
            arr[i] = (char) c;
            c = System.in.read();
        }
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