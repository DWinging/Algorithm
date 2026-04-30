/**
 * [BOJ] 20364 - 부동산 다툼
 * - 제출 날짜: 2026년 2월 3일
 * - 결과: 맞았습니다!!
 * - 메모리: 24944 KB
 * - 시간: 228 ms
 */

import java.util.*;
import java.io.IOException ;

class Main {

    static boolean[] tree;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder(1 << 13);
        int n = readInt();
        int m = readInt();

        tree = new boolean[n + 1];
        for(int i = 0; i < m; i++) {
            int idx = readInt();
            sb.append(search(idx)).append('\n');
        }
        System.out.println(sb);
    }

    private static int search(int idx) {
        int n = idx;
        int node = 0;
        while(n > 1) {
            if(tree[n]) node = n;
            n = n / 2;
        }

        if(node == 0) tree[idx] = true;
        return node;
    }

    private static int readInt() throws IOException {
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