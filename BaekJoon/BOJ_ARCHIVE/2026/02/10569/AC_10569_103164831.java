/**
 * [BOJ] 10569 - 다면체
 * - 제출 날짜: 2026년 2월 21일
 * - 결과: 맞았습니다!!
 * - 메모리: 11416 KB
 * - 시간: 56 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {
    static int c;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int n = readInt();
        while(n-- > 0) sb.append(2 - readInt() + readInt()).append('\n');
        System.out.print(sb);
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