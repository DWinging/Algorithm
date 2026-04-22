/**
 * [BOJ] 4195 - 친구 네트워크
 * - 제출 날짜: 2026년 2월 27일
 * - 결과: 맞았습니다!!
 * - 메모리: 20752 KB
 * - 시간: 180 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    static final int TABLE_SIZE = 1 << 18;
    static final int MASK = TABLE_SIZE - 1;

    static long[] keys = new long[TABLE_SIZE];
    static boolean[] occupied = new boolean[TABLE_SIZE];
    static int[] ids = new int[TABLE_SIZE];

    static int[] parents = new int[200_005];
    static int[] connect = new int[200_005];

    static StringBuilder sb = new StringBuilder();
    static int nodeCount = 0, c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int t = readInt();
        while(t-- > 0) {
            int n = readInt();
            init(n);
            solve(n);
        }
        System.out.print(sb);
    }

    private static void init(int n) throws IOException {
        for(int i = 0; i < n * 2; i++) {
            parents[i] = i;
            connect[i] = 1;
        }

        for(int i = 0; i < TABLE_SIZE; i++) {
            occupied[i] = false;
        }
        nodeCount = 0;
    }

    private static int find(int p) {
        if(parents[p] == p) return p;
        return parents[p] = find(parents[p]);
    }

    private static void union(int p1, int p2) {
        connect[p1] += connect[p2];
        parents[p2] = p1;
    }

    private static void solve(int n) throws IOException {
        while(n-- > 0) {
            int p1 = find(getID(getDoubleHash()));
            int p2 = find(getID(getDoubleHash()));
            if(p1 != p2) union(p1, p2);
            sb.append(connect[p1]).append('\n');    
        }        
    }

    public static long getDoubleHash() throws IOException {
        long h1 = 5381;
        long h2 = 0;

        while(c <= ' ') c = System.in.read();
        while(c > ' ') {
            h1 = ((h1 << 5) + h1) + c;
            h2 = h2 * 31 + c;
            c = System.in.read();
        }
        return (h1 << 32) | (h2 & (1L << 32) - 1);
    }

    public static int getID(long hashKey) {
        int h = (int) (hashKey & MASK);

        while(occupied[h]) {
            if(keys[h] == hashKey) return ids[h];
            h = (h + 1) & MASK;
        }
        
        occupied[h] = true;
        keys[h] = hashKey;
        ids[h] = nodeCount++;
        return ids[h];
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