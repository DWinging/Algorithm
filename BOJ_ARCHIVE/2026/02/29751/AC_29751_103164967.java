/**
 * [BOJ] 29751 - 삼각형
 * - 제출 날짜: 2026년 2월 21일
 * - 결과: 맞았습니다!!
 * - 메모리: 11768 KB
 * - 시간: 72 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {
    static int c;
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        System.out.print(String.format("%.1f", (readInt() * readInt()) / 2.0));
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c - '0');
            c = System.in.read();
        }
        return n;
    }
}