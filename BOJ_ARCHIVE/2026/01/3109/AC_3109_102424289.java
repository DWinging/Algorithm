/**
 * [BOJ] 3109 - 빵집
 * - 제출 날짜: 2026년 1월 29일
 * - 결과: 맞았습니다!!
 * - 메모리: 22076 KB
 * - 시간: 252 ms
 */

import java.util.*;
import java.io.*;

import java.io.IOException;

class Main {

    final static int[][] DICT = {{1, 1}, {0, 1}, {-1, 1}};
    final static int MAX_RANGE = 10_005;
    
    static int[] stack;
    static int n, m;
    static char[][] area;
    static char c = ' ';
    
    public static void main(String[] args) throws IOException {
        n = readInt();
        m = readInt();
        
        stack = new int[m * 3 + 5];
        area = new char[n][m];
        read();

        int cnt = 0;
        for(int i = 0; i < n; i++) {
            if(dfs(i, 0)) cnt++;
        }
        System.out.println(cnt);
    }

    private static int readInt() throws IOException {
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

    private static void read() throws IOException {
        for(int i = 0; i < n; i++) {
            while(c <= ' ') c = (char) System.in.read();
            for(int j = 0; j < m; j++) {
                area[i][j] = c;
                c = (char) System.in.read();
            }
        }
    }

    private static boolean dfs(int y, int idx) {
        int top = 0;
        stack[top] = y * m + 0;
        while(top >= 0) {
            int cur = stack[top--];
            int cy = cur / m;
            int cx = cur % m;
            area[cy][cx] = 'x';
            if(cx == m-1) return true;
            
            for(int[] d : DICT) {
                int ny = cy + d[0];
                int nx = cx + d[1];
                if(check(ny, nx) && area[ny][nx] == '.') {
                    int nc = ny * m + nx;
                    stack[++top] = nc;
                }
            }
        }
        return false;
    }

    private static boolean check(int y, int x) {
        return y >= 0 && y < n && x >= 0 && x < m;
    }
}