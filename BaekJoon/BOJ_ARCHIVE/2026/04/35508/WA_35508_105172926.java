/**
 * [BOJ] 35508 - Good Bye, 토마토!
 * - 제출 날짜: 2026년 4월 26일
 * - 결과: 틀렸습니다
 */

import java.util.*;
import java.io.*;

class Main {

    static int[][] arr;
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int d = readInt();
        inputArray(n);
        System.out.print(solve(n, d));
    }

    private static void inputArray(int n) throws IOException {
        arr = new int[n][3];
        for(int i = 0; i < n; i++) {
            arr[i][0] = readInt();
            arr[i][1] = readInt();
            arr[i][2] = readInt();
        }

        Arrays.sort(arr, (l1, l2) -> {return l1[0] - l2[0];});
    } 

    private static int solve(int n, int d) {
        int maxA = 0, maxB = 0, res = 0, left = 0;

        for(int right = n - 1; right >= 0; right--) {
            int t = arr[right][0];
            res = Math.max(res, arr[right][1] + arr[right][2]);
            while(left < right && t + arr[left][0] <= d) {
                maxA = Math.max(maxA, arr[left][1]);
                maxB = Math.max(maxB, arr[left][2]);
                res = Math.max(res, maxA + maxB);
                left++;                
            }

            if(t + arr[left][0] <= d) {
                int tempA = Math.max(maxA, arr[right][1]);
                int tempB = Math.max(maxB, arr[right][2]);
                res = Math.max(res, tempA + tempB);
            }
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