/**
 * [BOJ] 23563 - 벽 타기
 * - 제출 날짜: 2026년 2월 3일
 * - 결과: 틀렸습니다
 */

import java.util.*;
import java.io.IOException ;

class Main {

    final static int[][] DICT = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][] visited;
    static int n, m, maxSize;
    static char c;    
    
    public static void main(String[] args) throws IOException {
        n = readInt();
        m = readInt();

        maxSize = n * m;
        visited = new int[maxSize][2];

        char[][] matrix = inputMatrix();
        int s = findStart(matrix);
        System.out.println(bfs(matrix, s));
    }

    private static char[][] inputMatrix() throws IOException {
        char[][] matrix = new char[n][m];
        for(int i = 0; i < n; i++) {
            while(c <= ' ') c = (char) System.in.read();        
            for(int j = 0; j < m; j++) {
                matrix[i][j] = c;
                c = (char) System.in.read();
            }
        }
        return matrix;
    }

    private static int findStart(char[][] matrix) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(matrix[i][j] == 'S') {
                    int s = i * m + j;
                    visited[s][0] = 0;
                    visited[s][1] = checkWall(matrix, i, j) ? 1 : 2;
                    return s;
                }
            }
        }
        return -1;
    }

    private static int bfs(char[][] matrix, int s) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        pq.add(new int[]{s, 0});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int y = cur[0] / m;
            int x = cur[0] % m;
            int t = cur[1];
            int w = visited[cur[0]][1];

            if(matrix[y][x] == 'E') return t;

            if(t > visited[s][1]) continue;

            for(int[] d : DICT) {
                int ny = y + d[0];
                int nx = x + d[1];
                int ns = ny * m + nx;
                if(matrix[ny][nx] != '#') {
                    boolean nw = checkWall(matrix, ny, nx);
                    if(w == 1 && nw) {
                        if(visited[ns][1] == 0 || visited[ns][0] > t) {
                            visited[ns][0] = t;
                            pq.add(new int[]{ns, t});
                        }    
                    }
                    else {
                        if(visited[ns][1] == 0 || visited[ns][0] > t + 1) {
                            visited[ns][0] = t + 1;
                            pq.add(new int[]{ns, t + 1});
                        }
                    }
                    visited[ns][1] = nw ? 1 : 2;                    
                }
            }
        }
        return -1;
    }    

    private static boolean checkWall(char[][] matrix, int y, int x) {
        for(int[] d : DICT) {
            int ny = y + d[0];
            int nx = x + d[1];
            if(matrix[ny][nx] == '#') return true;
        }
        return false;
    }

    private static int readInt() throws IOException {
        c = (char) System.in.read();

        while(c <= ' ') {
            c = (char) System.in.read();
        }

        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n * 10) + (c - '0');
            c = (char) System.in.read();
        }
        return n;
    }
}