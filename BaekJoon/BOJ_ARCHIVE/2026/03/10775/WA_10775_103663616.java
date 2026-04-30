/**
 * [BOJ] 10775 - 공항
 * - 제출 날짜: 2026년 3월 9일
 * - 결과: 틀렸습니다
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    static int[] parents;
    static int c;
    
    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }

    private static void init() throws IOException {
        c = System.in.read();
        int n = readInt();
        parents = new int[n + 1];

        for(int i = 1; i <= n; i++) parents[i] = i;
    }

    private static int solve() throws IOException {
        int m = readInt();
        for(int i = 0; i < m; i++) {
            int p = find(readInt());
            if(p == 0) return i;
            union(p, p - 1);
        }
        return -1;
    }

    private static int find(int p) {
        if(parents[p] == p) return p;
        return parents[p] = find(parents[p]);
    }

    private static void union(int a, int b) {
        int pB = find(b);
        parents[a] = pB;
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