/**
 * [BOJ] 1941 - 소문난 칠공주
 * - 제출 날짜: 2026년 4월 12일
 * - 결과: 맞았습니다!!
 * - 메모리: 44680 KB
 * - 시간: 92 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

    final static int[][] DIRE = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    final static int SIZE = 25;
    final static int MAX_RANGE = 5;

    static int[] map = new int[SIZE];
    static boolean[] selected = new boolean[1 << SIZE];
    static int res = 0;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        System.out.println(res);
    }

    private static void input() throws IOException {
        int c = System.in.read();
        for(int y = 0; y < MAX_RANGE; y++) {
            while(c <= ' ') c = System.in.read();
            int ny = y * MAX_RANGE;
            for(int x = 0; x < MAX_RANGE; x++) {
                map[ny + x] = c == 'Y' ? 0 : 1;
                c = System.in.read();
            }
        }
    }

    private static void solve() {
        for(int i = 0; i < SIZE; i++) {
            if(map[i] == 1) {
                int bit = 1 << i;
                selected[bit] = true;
                dfs(bit, 1, 1);
            }
        }
    }

    private static void dfs(int mask, int sCnt, int cnt) {
        if (4 > sCnt + (7 - cnt)) return;
        if (cnt == 7) {
            res++;
            return;
        }

        for(int b = 0; b < SIZE; b++) {
            if((mask & (1 << b)) == 0) continue;

            int y = b / MAX_RANGE;
            int x = b % MAX_RANGE;

            for(int[] d : DIRE) {
                int ny = y + d[0];
                int nx = x + d[1];
                int next = ny * MAX_RANGE + nx;
                if(check(ny, nx) && (mask & (1 << next)) == 0) {
                    int nextMask = mask | (1 << next);
                    if(!selected[nextMask]) {
                        selected[nextMask] = true;
                        dfs(nextMask, sCnt + map[next], cnt + 1);
                    }
                }
            }
        }
    }

    private static boolean check(int y, int x) {
        return y >= 0 && y < MAX_RANGE && x >= 0 && x < MAX_RANGE;
    }
}