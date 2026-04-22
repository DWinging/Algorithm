/**
 * [BOJ] 15552 - 빠른 A+B
 * - 제출 날짜: 2026년 1월 28일
 * - 결과: 맞았습니다!!
 * - 메모리: 118640 KB
 * - 시간: 464 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = readInt();
        while(t-- > 0) {
            bw.write((readInt() + readInt()) + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static int readInt() throws IOException {
        int c = System.in.read();
        while(c <= ' ') {
            c = System.in.read();
        }

        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n * 10) + (c - '0');
            c = System.in.read();
        }
        return n;
    }
}