/**
 * [BOJ] 11812 - K진 트리
 * - 제출 날짜: 2026년 2월 14일
 * - 결과: 맞았습니다!!
 * - 메모리: 15452 KB
 * - 시간: 192 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        long n = readLong();
        long k = readLong();
        long q = readLong();

        System.out.print(solve(k, q));
    }

    private static String solve(long k, long q) throws IOException {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < q; i++) {
            long n1 = readLong();
            long n2 = readLong();
            sb.append(k == 1 ? Math.abs(n1 - n2) : calculateDistance(k, n1, n2)).append('\n');
        }
        return sb.toString();
    }

    private static long calculateDistance(long k, long n1, long n2) {
        long distance = 0;
        while(n1 != n2) {
            if(n1 > n2) {
                n1 = (n1 + (k - 2)) / k;
            }
            else {
                n2 = (n2 + (k - 2)) / k;
            }
            distance++;
        }
        return distance;
    }

    private static long readLong() throws IOException {
        while(c <= ' ') c = System.in.read();
        long n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c - '0');
            c = System.in.read();
        }
        return n;
    }
}
