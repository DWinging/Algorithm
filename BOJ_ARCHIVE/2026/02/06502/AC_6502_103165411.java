/**
 * [BOJ] 6502 - 동혁 피자
 * - 제출 날짜: 2026년 2월 21일
 * - 결과: 맞았습니다!!
 * - 메모리: 11336 KB
 * - 시간: 64 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {
    static int c;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int r, idx = 1;
        while((r = readInt()) != 0) {
            int w = readInt(), l = readInt();
            sb.append("Pizza ").append(idx++);
            if(r * r * 4 >= w * w + l * l) sb.append(" fits on the table.");
            else sb.append(" does not fit on the table.");
            sb.append('\n');
        }
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