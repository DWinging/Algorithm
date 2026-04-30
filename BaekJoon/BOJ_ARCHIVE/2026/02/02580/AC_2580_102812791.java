/**
 * [BOJ] 2580 - 스도쿠
 * - 제출 날짜: 2026년 2월 10일
 * - 결과: 맞았습니다!!
 * - 메모리: 12804 KB
 * - 시간: 144 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    static int[] row = new int[9 * 10];
    static int[] col = new int[9 * 10];
    static int[] box = new int[9 * 10];
    static int[][] result = new int[9][9];
    static int[] stack = new int[9 * 9];

    static int c, total = 0;
    static boolean flag = false;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        for(int y = 0; y < 9; y++) {
            for(int x = 0; x < 9; x++) {
                int num = readInt();
                result[y][x] = num;
                if(num == 0) {
                    stack[total++] = (y << 4) | x;
                    continue;
                }
                row[y] |= (1 << num);
                col[x] |= (1 << num);
                box[(y / 3) * 3 + (x / 3)] |= (1 << num);
            }
        }

        backtracking(0);

        System.out.println(buildString());
    }

    private static void backtracking(int idx) {
        if(idx == total) {
            flag = true;
            return;
        }

        int cur = stack[idx];
        int y = cur >> 4;
        int x = cur & ((1 << 4) - 1);
        int area = (y / 3) * 3 + (x / 3);

        for(int i = 1; i <= 9; i++) {
            if(check(row[y], i) && check(col[x], i) && check(box[area], i)) {
                marking(y, x, area, i);
                result[y][x] = i;
                backtracking(idx + 1);
                if(flag)return;
                marking(y, x, area, i);
            }
        }
    }

    private static boolean check(int n1, int n2) {
        return (n1 & (1 << n2)) == 0;
    }

    private static void marking(int y, int x, int area, int num) {
        row[y] ^= (1 << num);
        col[x] ^= (1 << num);
        box[area] ^= (1 << num);
    }    

    private static String buildString() {
        StringBuilder sb = new StringBuilder();
        for(int y = 0; y < 9; y++) {
            for(int x = 0; x < 9; x++) {
                sb.append(result[y][x]).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
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