/**
 * [BOJ] 35508 - Good Bye, 토마토!
 * - 제출 날짜: 2026년 4월 26일
 * - 결과: 맞았습니다!!
 * - 메모리: 34552 KB
 * - 시간: 816 ms
 */

import java.io.*;
import java.util.*;

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

        Arrays.sort(arr, (l1, l2) -> {return Integer.compare(l1[0], l2[0]);});
    }

    private static void solve(int n, int d) {
        int[] maxA = new int[n];
        int[] maxB = new int[n];
        maxA[0] = arr[0][1];
        maxB[0] = arr[0][2];
        res = maxA[0] + maxB[0];
    
        for (int i = 1; i < n; i++) {
            maxA[i] = Math.max(maxA[i - 1], arr[i][1]);
            maxB[i] = Math.max(maxB[i - 1], arr[i][2]);
            if (arr[i][0] <= d) res = Math.max(res, arr[i][1] + arr[i][2]);
        }
    
        int left = 0;
        for (int right = n - 1; right > 0; right--) {
            while (left < right && arr[left][0] + arr[right][0] <= d) {
                left++;
            }
            
            int partnerIdx = Math.min(left - 1, right - 1);
            
            if (partnerIdx >= 0) {
                res = Math.max(res, arr[right][1] + maxB[partnerIdx]);
                res = Math.max(res, arr[right][2] + maxA[partnerIdx]);
            }
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