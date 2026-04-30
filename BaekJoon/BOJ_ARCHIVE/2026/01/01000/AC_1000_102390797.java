/**
 * [BOJ] 1000 - A+B
 * - 제출 날짜: 2026년 1월 28일
 * - 결과: 맞았습니다!!
 * - 메모리: 11420 KB
 * - 시간: 56 ms
 */

import java.util.*;
import java.io.*;

import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(readByte() + readByte());
    }
    
    private static byte readByte() throws IOException {
        int c = System.in.read();
        while(c <= ' ') {
            c = System.in.read();
        }
        return (byte) (c - '0');
    }
}