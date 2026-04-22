/**
 * [BOJ] 23563 - 벽 타기
 * - 제출 날짜: 2026년 2월 3일
 * - 결과: 맞았습니다!!
 * - 메모리: 15040 KB
 * - 시간: 160 ms
 */

import java.util.*;
import java.io.IOException ;

class Main {

    final static int[][] DICT = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[] visited;
    static boolean[] wall;
    static int n, m, maxSize;
    static char c;    
    
    public static void main(String[] args) throws IOException {
        n = readInt();
        m = readInt();

        maxSize = n * m;
        visited = new int[maxSize];
        wall = new boolean[maxSize];
        Arrays.fill(visited, maxSize + 1);

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
        int s = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(matrix[i][j] == '#') {
                    checkWall(matrix, i, j);
                }
                if(matrix[i][j] == 'S') {
                    s = i * m + j;
                    visited[s] = 0;
                }
            }
        }
        return s;
    }

    private static int bfs(char[][] matrix, int s) {
        int[] deque = new int[maxSize];
        int head = 0, tail = 0, count = 0;
        deque[tail] = s;
        tail = (tail + 1) % maxSize;
        count++;

        while(count > 0) {
            int cur = deque[head];
            head = (head + 1) % maxSize;
            int y = cur / m;
            int x = cur % m;
            int t = visited[cur];
            boolean w = wall[cur];
            count--;

            if(matrix[y][x] == 'E') return t;

            for(int[] d : DICT) {
                int ny = y + d[0];
                int nx = x + d[1];
                if(matrix[ny][nx] == '#') continue;
                int ns = ny * m + nx;
                int cost = (w && wall[ns]) ? 0 : 1;
                int nextTime = cost + t;
                
                if(visited[ns] > nextTime) {
                    visited[ns] = nextTime;
                    if(cost == 0) {
                        head = (head - 1 + maxSize) % maxSize;
                        deque[head] = ns;
                    }
                    else {
                        deque[tail] = ns;
                        tail = (tail + 1) % maxSize;
                    }
                    count++;
                }
            }
        }
        return -1;
    }    

    private static void checkWall(char[][] matrix, int y, int x) {
        for(int[] d : DICT) {
            int ny = y + d[0];
            int nx = x + d[1];
            if(check(ny, nx)) wall[ny * m + nx] = true;
        }
    }

    private static boolean check(int y, int x) {
        return y >= 0 && y < n && x >= 0 && x < m;
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