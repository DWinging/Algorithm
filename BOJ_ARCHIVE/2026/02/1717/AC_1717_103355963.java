/**
 * [BOJ] 1717 - 집합의 표현
 * - 제출 날짜: 2026년 2월 27일
 * - 결과: 맞았습니다!!
 * - 메모리: 17736 KB
 * - 시간: 148 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    static int c, parents[];
    
    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }

    private static void init() throws IOException {
        c = System.in.read();
        int n =readInt();
        parents = new int[n + 1];
        for(int i = 0; i <= n; i++) parents[i] = i;
    }

    private static String solve() throws IOException {
        StringBuilder sb = new StringBuilder();
        int m = readInt();
        while(m-- > 0) {
            int command = readInt();
            int a = find(readInt());
            int b = find(readInt());

            if(command == 0) union(a, b);
            else sb.append(a == b ? "YES" : "NO").append('\n');
        }
        return sb.toString();
    }

    private static int find(int n) {
        if(parents[n] == n) return n;
        return parents[n] = find(parents[n]);
    }

    private static void union(int a, int b) {
        parents[b] = a;
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