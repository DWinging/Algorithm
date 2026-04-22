/**
 * [BOJ] 14939 - 불 끄기
 * - 제출 날짜: 2026년 2월 5일
 * - 결과: 맞았습니다!!
 * - 메모리: 11700 KB
 * - 시간: 92 ms
 */

import java.util.*;
import java.io.*;

import java.io.IOException;

class Main {

    final static int[][] DICT = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    final static int SIZE = 10;
    
    public static void main(String[] args) throws IOException {
        int[] arr = inputArray();
        System.out.println(solve(arr));
    }

    private static int[] inputArray() throws IOException {
        int[] arr = new int[SIZE];
        int c = System.in.read();
        for(int i = 0; i < SIZE; i++) {
            while(c != 'O' && c != '#') c = System.in.read();
            for(int j = 0; j < SIZE; j++) {
                if(c == 'O') arr[i] |= (1 << j);
                c = System.in.read();
            }
        }
        return arr;
    }

    private static int solve(int[] origin) {
        int[] arr = new int[SIZE];
        int cnt = SIZE * SIZE + 1;
        for(int i = 0; i < (1 << SIZE); i++) {
            arrayCopy(origin, arr);
            int temp = 0;
            for(int j = 0; j < SIZE; j++) {
                if((i & (1 << j)) != 0) {
                    toggleSwitch(arr, 0, j);
                    temp++;
                }                
            }
            temp += selectSwitch(arr);
            if(checkAllMute(arr)) {
                cnt = Math.min(temp, cnt);    
            }            
        }
        return cnt == (SIZE * SIZE + 1) ? -1 : cnt;
    }

    private static void arrayCopy(int[] origin, int[] arr) {
        for(int i = 0; i < SIZE; i++) {
            arr[i] = origin[i];
        }
    }

    private static int selectSwitch(int[] arr) {
        int cnt = 0;
        for(int y = 1; y < SIZE; y++) {
            for(int x = 0; x < SIZE; x++) {
                if((arr[y-1] & (1 << x)) != 0) {
                    toggleSwitch(arr, y, x);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void toggleSwitch(int[] arr, int y, int x) {
        arr[y] ^= (1 << x);
        for(int[] d : DICT) {
            int ny = y + d[0];
            int nx = x + d[1];
            if(check(ny, nx)) {
                arr[ny] ^= (1 << nx);
            }
        }
    }

    private static boolean checkAllMute(int[] arr) {
        for(int i : arr) {
            if(i != 0) return false;
        }
        return true;
    }
    
    private static boolean check(int y, int x) {
        return y >= 0 && y < SIZE && x >= 0 && x < SIZE;
    }
}