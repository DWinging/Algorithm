/**
 * [BOJ] 1941 - 소문난 칠공주
 * - 제출 날짜: 2026년 4월 12일
 * - 결과: 맞았습니다!!
 * - 메모리: 11720 KB
 * - 시간: 80 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

//Gemini 조합 풀이
public class Main {
    static int[] adj = new int[25];
    static int sMask = 0;
    static int res = 0;

    public static void main(String[] args) throws IOException {
        int c = System.in.read();
        for (int i = 0; i < 25; i++) {
            while (c <= ' ') c = System.in.read();
            if (c == 'S') sMask |= (1 << i);
            c = System.in.read();
            int bit = 0;
            if (i >= 5) bit |= (1 << (i - 5));
            if (i < 20) bit |= (1 << (i + 5));
            if (i % 5 != 0) bit |= (1 << (i - 1));
            if (i % 5 != 4) bit |= (1 << (i + 1));
            adj[i] = bit;
        }
        comb(0, 0, 0, 0);
        System.out.println(res);
    }

    private static void comb(int idx, int cnt, int sCnt, int mask) {
        if (sCnt + (7 - cnt) < 4) return;
        if (cnt == 7) {
            if (isConnected(mask)) res++;
            return;
        }
        for (int i = idx; i <= 18 + cnt; i++) {
            comb(i + 1, cnt + 1, sCnt + ((sMask >> i) & 1), mask | (1 << i));
        }
    }

    private static boolean isConnected(int mask) {
        int q = 1 << Integer.numberOfTrailingZeros(mask);
        int v = q;
        while (q > 0) {
            int curr = Integer.numberOfTrailingZeros(q);
            q &= q - 1;
            int next = adj[curr] & mask & ~v;
            v |= next;
            q |= next;
        }
        return v == mask;
    }
}