/**
 * [BOJ] 18311 - 왕복
 * - 제출 날짜: 2026년 3월 26일
 * - 결과: 맞았습니다!!
 * - 메모리: 12272 KB
 * - 시간: 104 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    static int c, arr[];
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        long k = readLong();
        int total = inputArray(n);
        System.out.println(solve(n, k, total));
    }

    private static int inputArray(int n) throws IOException {
        arr = new int[n];
        int sum = 0;
        for(int i = 0; i < n; i++) {
            int temp = readInt();
            arr[i] = temp;
            sum += temp;
        }
        return sum;
    }

    private static int solve(int n, long k, int total) {
        long q = k / total;
        long r = k % total;

        if((q ^ 1) == 0) {
            for(int i = n - 1; i >= 0; i--) {
                if(r < arr[i]) return i + 1;
                r -= arr[i];
            }
        } else {
            for(int i = 0; i < n; i++) {
                if(r < arr[i]) return i + 1;
                r -= arr[i];
            }
        }
        return -1;
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

    private static long readLong() throws IOException {
        while(c <= ' ') c = System.in.read();
        long n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}