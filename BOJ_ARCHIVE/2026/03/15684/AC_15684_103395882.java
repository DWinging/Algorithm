/**
 * [BOJ] 15684 - 사다리 조작
 * - 제출 날짜: 2026년 3월 1일
 * - 결과: 맞았습니다!!
 * - 메모리: 17376 KB
 * - 시간: 292 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

    static int[][] labbers;
    static int c, cnt = 4, labber = -1;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();
        int h = readInt();
        inputLabbers(n, m, h);

        backtracking(n, h, 1, 1, 0, -(m + 2));
        System.out.println(cnt == 4 ? -1 : cnt);
    }

    private static void inputLabbers(int n, int m, int h) throws IOException {
        labbers = new int[n + 2][h + 2];
        for(int i = 1; i <= n; i++) {
            labbers[i][h + 1] = i;
        }

        int idx = -1;
        while(m-- > 0) {
            int x = readInt();
            int y = readInt();
            labbers[y][x] = idx;
            labbers[y + 1][x] = idx;
            idx--;
        }
    }

    private static void backtracking(int n, int h, int y, int x, int stack, int idx) {
        if(stack > 3 || stack > cnt) return;
        if(checkRoute(n)) {
            cnt = stack;
            return;
        }
        if(stack == 3) return;

        for(int i = y; i < n; i++) {
            int beginX = (i == y) ? x : 1;
            for(int j = beginX; j <= h; j++) {
                if(labbers[i][j] == 0 && labbers[i + 1][j] == 0) {
                    labbers[i][j] = idx;
                    labbers[i + 1][j] = idx;

                    backtracking(n, h, i, j + 1, stack + 1, idx - 1);

                    labbers[i][j] = 0;
                    labbers[i + 1][j] = 0;
                }
            }
        }
    }

    private static boolean checkRoute(int n) {
        for(int i = 1; i <= n; i++) {
            if(!dfs(i)) return false;
        }
        return true;
    }

    private static boolean dfs(int start) {
        int line = start, x = 1;
        while(labbers[line][x] <= 0) {
            while(labbers[line][x] == 0) {
                x++;
            }
            if(labbers[line][x] < 0) {
                line += labbers[line + 1][x] == labbers[line][x] ? 1 : -1;
                x++;
            }
        }
        return labbers[line][x] == start;
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
