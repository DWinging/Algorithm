/**
 * [BOJ] 2042 - 구간 합 구하기
 * - 제출 날짜: 2026년 2월 24일
 * - 결과: 런타임 에러 (ArrayIndexOutOfBounds)
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    static long[] tree;
    
    public static void main(String[] args) throws IOException {
        int n = readInt();
        int m = readInt() + readInt();
        settingTree(n);
        System.out.print(solve(n, m));
    }

    private static void settingTree(int n) throws IOException {
        tree = new long[n * 4];
        for(int i = 0; i < n; i++) tree[n + i] = readLong();
        for(int i = n - 1; i > 0; i--) tree[i] = tree[i * 2] + tree[i * 2 + 1];
    }

    private static String solve(int n, int m) throws IOException {
        StringBuilder sb = new StringBuilder();
        while(m-- > 0){
            int command = readInt();
            if(command == 1) {
                int a = readInt();
                long b = readLong();
                changeNode(n, a-1, b);
            }
            else {
                int a = readInt(), b = readInt();
                sb.append(getSum(n, a-1, b-1)).append('\n');
            }
        }
        return sb.toString();
    }

    private static void changeNode(int n, int a, long b) {
        int idx = n + a;
        long diff = b - tree[idx];
        tree[idx] = b;
        while(idx > 1) {
            idx /= 2;
            tree[idx] += diff;
        }
    }

    private static long getSum(int n, int left, int right) {
        long sum = 0;
        left += n;
        right += n;

        while(left <= right) {
            if(left % 2 == 1) sum += tree[left++];
            if(right % 2 == 0) sum += tree[right--];

            left /= 2;
            right /= 2;
        }
        return sum;
    }

    private static int readInt() throws IOException {
        int c = System.in.read(), n = 0;
        while(c <= ' ') c = System.in.read();
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c - '0');
            c = System.in.read();
        }
        return n;
    }

    private static long readLong() throws IOException {
        int c = System.in.read();
        long n = 0;
        while(c <= ' ') c = System.in.read();
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c - '0');
            c = System.in.read();
        }
        return n;
    }
}