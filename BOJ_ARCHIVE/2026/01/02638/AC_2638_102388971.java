/**
 * [BOJ] 2638 - 치즈
 * - 제출 날짜: 2026년 1월 28일
 * - 결과: 맞았습니다!!
 * - 메모리: 12056 KB
 * - 시간: 80 ms
 */

import java.util.*;
import java.io.*;

class Main {

    final static int[][] DICT = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int[][] visited;
    static int[] dequeY;
    static int[] dequeX;
    static int[] cheeseY;
    static int[] cheeseX;
    static int head = 0, tail = 0;
    
    public static void main(String[] args) throws IOException{
        int n = readInt();
        int m = readInt();
        int[][] arr = new int[n][m];
        
        visited = new int[n][m];
        cheeseY = new int[n * m];
        cheeseX = new int[n * m];
        dequeY = new int[n * m];
        dequeX = new int[n * m];
        
        int cnt = inputArray(arr, n, m);

        System.out.println(solve(n, m, cnt, arr));
    }

    private static int inputArray(int[][] arr, int n, int m) throws IOException {
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                arr[i][j] = readInt();
                if(arr[i][j] == 1) cnt++;
            }
        }
        return cnt;
    }

    private static int solve(int n, int m, int cnt, int[][] arr) {
        int time = 0;
        int meltCnt = 0;
        visited[0][0] = -1;
        dequeY[tail] = 0;
        dequeX[tail] = 0;
        tail++;
        while(cnt > 0) {
            meltCnt = bfs(n, m, arr);
            head = 0;
            tail = 0;
            cnt -= meltCnt;
            for(int i = 0; i < meltCnt; i++) {
                int y = cheeseY[i];
                int x = cheeseX[i];
                arr[y][x] = 0;
                visited[y][x] = -1;
                
                dequeY[tail] = y;
                dequeX[tail] = x;
                tail++;
            }
            time++;
        }
        return time;
    }

    private static int bfs(int n, int m, int[][] arr) {
        int cnt = 0;
        while(head < tail) {
            int y = dequeY[head];
            int x = dequeX[head];
            head++;
            
            for(int[] d : DICT) {
                int ny = d[0] + y;
                int nx = d[1] + x;
                
                if(!check(ny, nx, n, m)) continue;
                
                if(arr[ny][nx] == 0 && visited[ny][nx] == 0) {
                    dequeY[tail] = ny;
                    dequeX[tail] = nx;
                    visited[ny][nx] = -1;
                    tail++;
                }
                else if(arr[ny][nx] == 1) {
                    visited[ny][nx]++;
                    if(visited[ny][nx] == 2) {
                        cheeseY[cnt] = ny;
                        cheeseX[cnt] = nx;
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }

    private static boolean check(int y, int x, int n, int m) {
        return y >= 0 && y < n && x >= 0 && x < m;
    }

    private static int readInt() throws IOException {
        int c = System.in.read();
        while(c <= ' ') {
            c = System.in.read();
        }

        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n * 10) + (c - '0');
            c = System.in.read();
        }
        return n;
    }
}