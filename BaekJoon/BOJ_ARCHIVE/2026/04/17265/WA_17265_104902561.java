/**
 * [BOJ] 17265 - 나의 인생에는 수학과 함께
 * - 제출 날짜: 2026년 4월 10일
 * - 결과: 틀렸습니다
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

    static int[][] map;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        inputArray(n);
        System.out.println(calMaxAndMin(n));
    }

    private static void inputArray(int n) throws IOException {
        map = new int[n][n];
        for(int y = 0; y < n; y++) {
            for(int x = 0; x < n; x++) {
                int val = inputValue(y, x);
                map[y][x] = val;
            }
        }
    }

    private static String calMaxAndMin(int n) {
        int[][] maxValue = new int[n][n];
        int[][] minValue = new int[n][n];
        for(int y = 0; y < n; y += 2) {
            for(int x = 0; x < n; x += 2) {
                if(y == 0 && x == 0) {
                    maxValue[y][x] = map[y][x];
                    minValue[y][x] = map[y][x];
                }
                else if(y == 0) {
                    int val = getValue(maxValue[y][x-2], map[y][x-1], map[y][x]);
                    maxValue[y][x] = val;
                    minValue[y][x] = val;
                } else if(x == 0){
                    int val = getValue(maxValue[y-2][x], map[y-1][x], map[y][x]);
                    maxValue[y][x] = val;
                    minValue[y][x] = val;
                } else {
                    int num = map[y][x];
                    maxValue[y][x] = getMaxValue(maxValue, y, x, num);
                    minValue[y][x] = getMinValue(minValue, y, x, num);
                }
            }
        }
        return maxValue[n-1][n-1] + " " + minValue[n-1][n-1];
    }

    private static int getValue(int num1, int opt, int num2) {
        if(opt == '*') return num1 * num2;
        else if(opt == '+') return num1 + num2;
        else return num1 - num2;
    }

    private static int getMaxValue(int[][] arr, int y, int x, int num) {
        int val1 = getValue(arr[y-2][x], map[y-1][x], num);
        int val2 = getValue(arr[y][x - 2], map[y][x - 1], num);
        return Math.max(val1, val2);
    }

    private static int getMinValue(int[][] arr, int y, int x, int num) {
        int val1 = getValue(arr[y-2][x], map[y-1][x], num);
        int val2 = getValue(arr[y][x - 2], map[y][x - 1], num);
        return Math.min(val1, val2);
    }

    private static int inputValue(int y, int x) throws IOException {
        while(c <= ' ') c = System.in.read();
        int val = ((y + x) & 1) == 0 ? (c & 15) : c;
        c = System.in.read();
        return val;
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
