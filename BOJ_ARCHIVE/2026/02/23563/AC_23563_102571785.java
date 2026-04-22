/**
 * [BOJ] 23563 - 벽 타기
 * - 제출 날짜: 2026년 2월 3일
 * - 결과: 맞았습니다!!
 * - 메모리: 24472 KB
 * - 시간: 308 ms
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
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (visited[a] - visited[b]));
        pq.add(s);

        while(!pq.isEmpty()) {
            int cur = pq.poll();
            int y = cur / m;
            int x = cur % m;
            int t = visited[cur];
            boolean w = wall[cur];

            if(matrix[y][x] == 'E') return t;

            for(int[] d : DICT) {
                int ny = y + d[0];
                int nx = x + d[1];
                int ns = ny * m + nx;
                if(matrix[ny][nx] != '#') {
                    int nextTime = t + ((w && wall[ns]) ? 0 : 1);
                    if(visited[ns] > nextTime) {
                        visited[ns] = nextTime;
                        pq.add(ns);
                    }
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