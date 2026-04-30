/**
 * [BOJ] 35508 - Good Bye, 토마토!
 * - 제출 날짜: 2026년 4월 26일
 * - 결과: 시간 초과
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    static int[][] arr;
    static int c, res = 0;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int d = readInt();
        inputArray(n);
        solve(n, d);
        System.out.println(res);
    }

    private static void inputArray(int n) throws IOException {
        arr = new int[n][3];
        for(int i = 0; i < n; i++) {
            arr[i][0] = readInt();
            arr[i][1] = readInt();
            arr[i][2] = readInt();
        }
    }

    private static void solve(int n, int d) {
        for(int i = 0; i < n; i++) {
            solve(i + 1, arr[i][0], arr[i][1], arr[i][2], n, d);
        }
    }

    private static void solve(int i, int t, int a, int b, int n, int d) {
        if(t > d) return;
        
        if(a + b > res) res = a + b;
        for(int next = i; next < n; next++) {
            solve(next + 1, t + arr[next][0], Math.max(a, arr[next][1]), Math.max(b, arr[next][2]), n, d);
        }
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