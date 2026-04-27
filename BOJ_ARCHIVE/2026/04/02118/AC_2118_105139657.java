/**
 * [BOJ] 2118 - 두 개의 탑
 * - 제출 날짜: 2026년 4월 24일
 * - 결과: 맞았습니다!!
 * - 메모리: 12044 KB
 * - 시간: 84 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    static int[] arr;
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();

        int total = inputArray(n);
        System.out.println(solve(n, total));
    }

    private static int inputArray(int n) throws IOException {
        arr = new int[n];
        int total = 0;
        for(int i = 0; i < n; i++) {
            int val = readInt();
            total += val;
            arr[i] = val;
        }
        return total;
    }

    private static int solve(int n, int total) {
        int left = 0, right = 0, res = 0;
        int clockwise = 0, counterClockwise = total;

        while(left < n) {
            while(right < n && clockwise < counterClockwise) {
                if(res < clockwise) res = clockwise;
                clockwise += arr[right];
                counterClockwise -= arr[right];
                right++;
            }

            if(clockwise >= counterClockwise && res < counterClockwise) 
                res = counterClockwise;

            clockwise -= arr[left];
            counterClockwise += arr[left];
            left++;
        }
        return res;
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