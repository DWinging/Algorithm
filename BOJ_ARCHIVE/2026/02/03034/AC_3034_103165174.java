/**
 * [BOJ] 3034 - 앵그리 창영
 * - 제출 날짜: 2026년 2월 21일
 * - 결과: 맞았습니다!!
 * - 메모리: 11360 KB
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
        int n = readInt(), w = readInt(), h = readInt();
        double d = Math.sqrt(w * w + h * h);
        while(n-- > 0) sb.append(readInt() <= d ? "DA" : "NE").append('\n');
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