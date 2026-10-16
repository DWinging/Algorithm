import java.util.*;
import java.io.*;

class Main {

    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();

        int n = readInt();
        int m = readInt();

        char[][] arr = new char[n][];
        for(int i = 0; i < n; i++) {
            arr[i] = readChar(m);
        }

        System.out.println(bfs(arr, n, m));
    }

    private static String bfs(char[][] arr, int n, int m) {
        Deque<int[]> deque = new ArrayDeque<>();        

        int[][][] visited = new int[n][m][4];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                for(int k = 0; k < 4; k++) {
                    visited[i][j][k] = 3;   
                }
            }
        }
        
        int[] start = searchStart(arr, n, m);

        for(int i = 0; i < 4; i++) {
            deque.addLast(new int[]{start[0], start[1], i, 0});
            visited[start[0]][start[1]][i] = 0;
        }


        int[][] dist = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        
        while(!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int sy = cur[0];
            int sx = cur[1];
            int d = cur[2];
            int cnt = cur[3];

            if(visited[sy][sx][d] < cnt) continue;

            if(arr[sy][sx] == 'T') return "YES";

            for(int i = 0; i < dist.length; i++) {
                int ny = sy + dist[i][0];
                int nx = sx + dist[i][1];
                int nCnt = d != i ? cnt + 1 : cnt;

                if(check(ny, nx, n, m) && arr[ny][nx] != '*' && visited[ny][nx][i] > nCnt) {
                    if(d == i) deque.addFirst(new int[]{ny, nx, i, nCnt});
                    else deque.addLast(new int[]{ny, nx, i, nCnt});
                    visited[ny][nx][i] = nCnt;
                }
            }
        }

        return "NO";
    }

    private static int[] searchStart(char[][] arr, int n, int m) {
        for(int y = 0; y < n; y++) {
            for(int x = 0; x < m; x++) {
                if(arr[y][x] == 'S') {
                    return new int[]{y, x};
                }
            }
        }
        return new int[]{-1, -1, 0, 0};
    }
    
    private static boolean check(int y, int x, int n, int m) {
        return y >= 0 && y < n && x >= 0 && x < m;
    }

    private static char[] readChar(int m) throws IOException {
        while(c <= ' ') c = System.in.read();
        char[] arr = new char[m];
        for(int i = 0; i < m; i++) {
            arr[i] = (char) c;
            c = System.in.read();
        }
        return arr;
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