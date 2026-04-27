/**
 * [BOJ] 18185 - 라면 사기 (Small)
 * - 제출 날짜: 2026년 4월 22일
 * - 결과: 맞았습니다!!
 * - 메모리: 11912 KB
 * - 시간: 76 ms
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
        arr = new int[n + 2];
        for(int i = 0; i < n; i++) arr[i] = readInt();

        System.out.println(solve(n));
    }

    private static int solve(int n) throws IOException {
        int total = 0;
        for(int i = 0; i < n; i++) {
            if(arr[i] == 0) continue;

            if(arr[i + 1] > arr[i + 2]) {
                int cnt = arr[i] < arr[i + 1] - arr[i + 2] ? arr[i] : arr[i + 1] - arr[i + 2];
                arr[i] -= cnt;
                arr[i + 1] -= cnt;
                total += cnt * 5;
            }

            total += purchase(i, i + 2, 7);
            total += purchase(i, i + 1, 5);
            total += arr[i] * 3;
        }

        return total;
    }

    private static int purchase(int idx, int end, int w) {
        int cost = arr[idx];
        for(int i = idx + 1; i <= end; i++) {
            if(cost > arr[i]) cost = arr[i];
        }

        for(int i = idx; i <= end; i++) arr[i] -= cost;

        return cost * w;
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