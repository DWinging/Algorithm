/**
 * [BOJ] 1000 - A+B
 * - 제출 날짜: 2026년 1월 28일
 * - 결과: 맞았습니다!!
 * - 메모리: 11432 KB
 * - 시간: 60 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        byte n1 = (byte) (System.in.read() - '0');
        int c = System.in.read();
        while(c <= ' ') c = System.in.read();
        byte n2 = (byte) (c - '0');
        System.out.println(n1 + n2);
    }
}