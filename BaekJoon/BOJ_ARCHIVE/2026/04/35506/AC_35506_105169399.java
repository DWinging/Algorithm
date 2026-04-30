/**
 * [BOJ] 35506 - Good Bye, 별 찍기!
 * - 제출 날짜: 2026년 4월 26일
 * - 결과: 맞았습니다!!
 * - 메모리: 12916 KB
 * - 시간: 72 ms
 */

import java.util.*;
import java.io.*;

class Main {
    
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int row = n << 1;
        int col = (n << 2) + 2;

        boolean[][] res = new boolean[row][col];
        for(int i = (col >> 1) - 2, j = 0; j < row; i--, j++) {
            res[j][i] = true;
        }

        int left = (col >> 2) + (col >> 1) - 1;
        int right = left + 2;
        for(int i = 0; i < n; i++) {
            res[i][left] = true;
            res[i][right] = true;
            left--;
            right++;
        }

        for(int i = n; i < row; i++) {
            left++;
            right--;
            res[i][left] = true;
            res[i][right] = true;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                sb.append(res[i][j] ? '*' : ' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
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