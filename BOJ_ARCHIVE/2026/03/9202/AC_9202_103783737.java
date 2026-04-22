/**
 * [BOJ] 9202 - Boggle
 * - 제출 날짜: 2026년 3월 12일
 * - 결과: 맞았습니다!!
 * - 메모리: 343308 KB
 * - 시간: 1084 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

    static class Trie {
        int[] arr = new int[26];
        boolean isEnd;
        int hit = -1;

        public Trie() {
        }
    }

    final static int[][] DIST = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
    static Trie[] trie;
    static int[][] grid = new int[4][4];
    static boolean[][] visited = new boolean[4][4];
    static char[] res = new char[8];
    static int c, score, foundCount;
    static String longestWord;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        init();
        System.out.print(solve());
    }

    private static void init() throws IOException {
        int n = readInt();
        trie = new Trie[n * 8 + 1];
        trie[0] = new Trie();
        int nodeCnt = 1;

        for (int i = 0; i < n; i++) {
            int pointer = 0;
            while (c <= ' ') c = System.in.read();
            while (c > ' ') {
                int val = c - 'A';
                if (trie[pointer].arr[val] == 0) {
                    trie[pointer].arr[val] = nodeCnt;
                    trie[nodeCnt++] = new Trie();
                }
                pointer = trie[pointer].arr[val];
                c = System.in.read();
            }
            trie[pointer].isEnd = true;
        }
    }

    private static String solve() throws IOException {
        StringBuilder sb = new StringBuilder();
        int m = readInt();

        for (int i = 0; i < m; i++) {
            inputGrid();
            score = 0;
            foundCount = 0;
            longestWord = "";

            for (int y = 0; y < 4; y++) {
                for (int x = 0; x < 4; x++) {
                    dfs(y, x, 0, 0, i);
                }
            }

            sb.append(score).append(' ')
              .append(longestWord).append(' ')
              .append(foundCount).append('\n');
        }
        return sb.toString();
    }

    private static void inputGrid() throws IOException {
        for (int i = 0; i < 4; i++) {
            while (c <= ' ') c = System.in.read();
            for (int j = 0; j < 4; j++) {
                grid[i][j] = c - 'A';
                c = System.in.read();
            }
        }
    }

    private static void dfs(int y, int x, int cnt, int pointer, int boardIdx) {
        int nextPointer = trie[pointer].arr[grid[y][x]];
        if (nextPointer == 0) return;

        res[cnt++] = (char) (grid[y][x] + 'A');
        visited[y][x] = true;

        Trie curr = trie[nextPointer];
        if (curr.isEnd && curr.hit != boardIdx) {
            curr.hit = boardIdx;
            foundCount++;
            score += getScore(cnt);

            String currentStr = new String(res, 0, cnt);
            if (cnt > longestWord.length()) {
                longestWord = currentStr;
            } else if (cnt == longestWord.length()) {
                if (currentStr.compareTo(longestWord) < 0) {
                    longestWord = currentStr;
                }
            }
        }

        if (cnt < 8) {
            for (int[] d : DIST) {
                int ny = y + d[0];
                int nx = x + d[1];
                if (check(ny, nx) && !visited[ny][nx]) {
                    dfs(ny, nx, cnt, nextPointer, boardIdx);
                }
            }
        }

        visited[y][x] = false;
    }

    private static boolean check(int y, int x) {
        return y >= 0 && y < 4 && x >= 0 && x < 4;
    }

    private static int getScore(int n) {
        if (n <= 2) return 0;
        if (n <= 4) return 1;
        if (n == 5) return 2;
        if (n == 6) return 3;
        if (n == 7) return 5;
        return 11;
    }

    private static int readInt() throws IOException {
        while (c <= ' ') c = System.in.read();
        int n = 0;
        while (c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}