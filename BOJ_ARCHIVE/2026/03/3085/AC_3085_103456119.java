/**
 * [BOJ] 3085 - 사탕 게임
 * - 제출 날짜: 2026년 3월 3일
 * - 결과: 맞았습니다!!
 * - 메모리: 12064 KB
 * - 시간: 112 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    static char[][] area;
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        area = new char[n][n];
        for(int i = 0; i < n; i++) {
            while(c <= ' ') c = System.in.read();
            for(int j = 0; j < n; j++) {
                area[i][j] = (char) c;
                c = System.in.read();
            }
        }

        int cnt = countAll(n);
        for(int y = 0; y < n; y++) {           
            for(int x = 0; x < n - 1; x++) {
                if(area[y][x] != area[y][x + 1]) {
                    swap(y, x, y, x + 1);
                    cnt = Math.max(cnt, countAll(n));
                    swap(y, x, y, x + 1);
                }
            }            
        }

        for(int x = 0; x < n; x++) {
            for(int y = 0; y < n - 1; y++) {
                if(area[y][x] != area[y + 1][x]) {
                    swap(y, x, y + 1, x);
                    cnt = Math.max(cnt, countAll(n));
                    swap(y, x, y + 1, x);
                }
            }            
        }

        System.out.println(cnt);
    }

    private static void swap(int y, int x, int y2, int x2) {
        char temp = area[y][x];
        area[y][x] = area[y2][x2];
        area[y2][x2] = temp;
    }

    private static int countAll(int n) {
        int total = 0;
        for(int y = 0; y < n; y++) {
            int x = 0, cnt = 0;
            char temp = area[y][x];
            while(x < n) {
                if(temp != area[y][x]) {
                    temp = area[y][x];
                    total = Math.max(total, cnt);
                    cnt = 0;
                }
                cnt++;
                x++;
            }
            total = Math.max(total, cnt);
        }

        for(int x = 0; x < n; x++) {
            int y = 0, cnt = 0;
            char temp = area[y][x];
            while(y < n) {
                if(temp != area[y][x]) {
                    temp = area[y][x];
                    total = Math.max(total, cnt);
                    cnt = 0;
                }
                cnt++;
                y++;
            }
            total = Math.max(total, cnt);
        }
        return total;
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