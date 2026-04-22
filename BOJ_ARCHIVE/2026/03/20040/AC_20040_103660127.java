/**
 * [BOJ] 20040 - 사이클 게임
 * - 제출 날짜: 2026년 3월 9일
 * - 결과: 맞았습니다!!
 * - 메모리: 19672 KB
 * - 시간: 224 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    static int[] parents;
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();
        init(n);
        System.out.println(solve(n, m));
    }

    private static void init(int n) {
        parents = new int[n];
        for(int i = 0; i < n; i++) parents[i] = i;
    }

    private static int solve(int n, int m) throws IOException {
        for(int t = 1; t <= m; t++) {
            int a = find(readInt());
            int b = find(readInt());

            if(a == b) return t;
            parents[b] = a;
        }
        return 0;
    }

    private static int find(int p) {
        if(parents[p] == p) return p;
        return parents[p] = find(parents[p]);
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}