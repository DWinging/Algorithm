/**
 * [BOJ] 16432 - 떡장수와 호랑이
 * - 제출 날짜: 2026년 4월 15일
 * - 결과: 맞았습니다!!
 * - 메모리: 12136 KB
 * - 시간: 72 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    final static int BIT_SHIFT = 4;
    static int[] arr, res; 
    static int c;
    static boolean flag = false;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        inputArray(n);

        res = new int[n];
        dfs(0, 0, n);
        System.out.print(flag ? buildString() : -1);
    }

    private static void inputArray(int n) throws IOException {
        arr = new int[n << BIT_SHIFT | 10];        
        for(int i = 0; i < n; i++) {
            int m = readInt();
            for(int j = 0; j < m; j++) {
                arr[i << BIT_SHIFT | j] = readInt();
            }
            arr[i << BIT_SHIFT | m] = -1;
        }
    }

    private static void dfs(int day, int pre, int n) {
        if(day == n) {
            flag = true;
            return;
        }
        
        int idx = 0;
        while(arr[day << BIT_SHIFT | idx] > -1 && !flag) {
            int cur = day << BIT_SHIFT | idx++;
            int val = arr[cur];
            if(val == 0 || val == pre) continue;
            
            dfs(day + 1, val, n);
            res[day] = val;
            arr[cur] = 0;            
        }
    }

    private static String buildString() {
        StringBuilder sb = new StringBuilder();
        for(int i : res) {
            sb.append(i).append('\n');
        }
        return sb.toString();
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