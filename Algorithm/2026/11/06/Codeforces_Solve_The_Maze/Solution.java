import java.util.*;
import java.io.*;

class Main {

    static int[][] dist = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    static int c;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();

        int t = readInt();
        while(t-- > 0) {
            int n = readInt();
            int m = readInt();

            char[][] map = new char[n][m];
            inputArray(map, n, m);
            int goodPerson = countGoodPerson(map, n, m);
            buildWall(map, n, m);

            boolean res = bfs(map, n, m, goodPerson);
            sb.append(res ? "YES" : "NO").append('\n');
        }
        System.out.println(sb);
    }

    private static void inputArray(char[][] map, int n, int m) throws IOException {
        for(int i = 0; i < n; i++) {
            while(c <= ' ') c = System.in.read();
            for(int j = 0; j < m; j++) {
                map[i][j] = (char) c;
                c = System.in.read();
            }
        }
    }

    private static int countGoodPerson(char[][] map, int n, int m) {
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == 'G') cnt++;
            }
        }
        return cnt;
    }

    private static void buildWall(char[][] map, int n, int m) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == 'B') {
                    for(int[] d : dist) {
                        int y = i + d[0];
                        int x = j + d[1];

                        if(check(y, x, n, m) && map[y][x] != 'B') {
                            map[y][x] = '#';
                        }
                    }
                }
            }
        }
    }

    private static boolean bfs(char[][] map, int n, int m, int goodPerson) {
        Queue<int[]> que = new ArrayDeque<>();
        if(map[n - 1][m - 1] == '.') {
            que.add(new int[]{n - 1, m - 1});
        } 

        boolean[][] visited = new boolean[n][m];
        visited[n - 1][m - 1] = true;

        int cnt = 0;

        while(!que.isEmpty()) {
            int[] cur = que.poll();
            int cy = cur[0];
            int cx = cur[1];

            for(int[] d : dist) {
                int ny = cy + d[0];
                int nx = cx + d[1];

                if(check(ny, nx, n, m) && !visited[ny][nx] && map[ny][nx] != '#') {
                    if(map[ny][nx] == 'G') cnt++;
                    que.add(new int[]{ ny, nx });
                    visited[ny][nx] = true;
                }
            }
        }

        return cnt == goodPerson;
    }

    private static boolean check(int y, int x, int n, int m) {
        return y >= 0 && y < n && x >= 0 &&  x < m;
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