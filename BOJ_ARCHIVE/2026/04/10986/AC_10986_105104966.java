/**
 * [BOJ] 10986 - 나머지 합
 * - 제출 날짜: 2026년 4월 20일
 * - 결과: 맞았습니다!!
 * - 메모리: 11996 KB
 * - 시간: 276 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {
    
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();

        System.out.println(solve(n, m));
    }

    private static long solve(int n, int m) throws IOException {
        long[] cnts = new long[m];

        long sum = 0, totalCnt = 0;
        for(int i = 0; i < n; i++) {
            sum += readInt();

            int remainder = (int) (sum % m);
            if(remainder == 0) totalCnt++;

            totalCnt += cnts[remainder];
            cnts[remainder]++;
        }

        return totalCnt;
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