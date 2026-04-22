/**
 * [BOJ] 1600 - 말이 되고픈 원숭이
 * - 제출 날짜: 2026년 2월 12일
 * - 결과: 맞았습니다!!
 * - 메모리: 27412 KB
 * - 시간: 216 ms
 */

import java.io.*;
import java.util.Arrays;

class Main {

    final static int BIT_SHIFT = 8;
    final static int[][] DICT = {
        {1, 2}, {-1, 2}, {1, -2}, {-1, -2}, {2, 1}, {-2, 1}, {2, -1}, {-2, -1},
        {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    static int[] arr;
    static int[] dequeXY, dequeK, dequeM, visited;
    static int c, k, row, col, head = 0, tail = 0;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        k = readInt();
        col = readInt();
        row = readInt();
        arr = new int[(row << BIT_SHIFT) | col];
        
        inputArray();
        int[] visited = new int[(row << BIT_SHIFT) | col];

        System.out.println(bfs());
    }

    private static void inputArray() throws IOException {
        for(int y = 0; y < row; y++) {
            for(int x = 0; x < col; x++) {
                arr[(y << BIT_SHIFT) | x] = readInt();
            }
        }
    }

    private static int bfs() {
        visited = new int[(row << BIT_SHIFT) | col];
        Arrays.fill(visited, -1);
        visited[0] = k + 1;
        
        dequeXY = new int[row * col * (k + 1)];
        dequeK = new int[row * col * (k + 1)];
        dequeM = new int[row * col * (k + 1)];
    
        dequeXY[tail] = 0;
        dequeK[tail] = k;
        dequeM[tail] = 0;
        tail++;

        while(head < tail) {
            int cur = dequeXY[head];
            int nk = dequeK[head];
            int nm = dequeM[head];
            head++;

            int y = cur >> BIT_SHIFT;
            int x = cur & ((1 << BIT_SHIFT) - 1);

            if(y == row - 1 && x == col - 1) {
                return nm;
            }

            move(y, x, 8, 12, nk, nm);
            if(nk > 0) {
                move(y, x, 0, 8, nk - 1, nm);
            }
        }
        
        return -1;
    }

    private static void move(int y, int x, int s, int e, int w, int m) {
        for(int i = s; i < e; i++) {
            int ny = y + DICT[i][0];
            int nx = x + DICT[i][1];
            int cur = (ny << BIT_SHIFT) | nx;
            if(check(ny, nx) && arr[cur] == 0 && visited[cur] < w) {
                visited[cur] = w;
                dequeXY[tail] = cur;
                dequeK[tail] = w;
                dequeM[tail] = m + 1;
                tail++;
            }
        }
    }

    private static boolean check(int y, int x) {
        return y >= 0 && y < row && x >= 0 && x < col;
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c - '0');
            c = System.in.read();
        }
        return n;
    }
}