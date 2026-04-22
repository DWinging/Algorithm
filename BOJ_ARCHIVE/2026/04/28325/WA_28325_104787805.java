/**
 * [BOJ] 28325 - 호숫가의 개미굴
 * - 제출 날짜: 2026년 4월 7일
 * - 결과: 100점
 * - 메모리: 12072 KB
 * - 시간: 144 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        System.out.println(solve(n));
    }

    private static long solve(int n) throws IOException {
        long antCount = 0L;
        long room = readLong();
        int firstCnt = 0;
        int currentCnt = 0;

        while (room == 0 && n > 1) {
            firstCnt++;
            n--;
            room = readLong();
        }

        if (room == 0) return (long)(firstCnt + 1) >> 1;

        antCount += room;
        n--;

        while (n-- > 0) {
            room = readLong();
            if (room > 0) {
                antCount += room;
                if (currentCnt > 0) {
                    antCount += (currentCnt + 1) >> 1;
                    currentCnt = 0;
                }
            } else {
                currentCnt++;
            }
        }

        return antCount + ((firstCnt + currentCnt + 1) >> 1);
    }

    private static int readInt() throws IOException {
        while (c <= ' ') c = System.in.read();
        int n = 0;
        while (c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }

    private static long readLong() throws IOException {
        while (c <= ' ') c = System.in.read();
        long n = 0;
        while (c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}