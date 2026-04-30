package BaekJoon;

import java.io.*;

public class BaekJoon_15684 {

    static int[][] labbers;
    static boolean flag = false;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();
        int h = readInt();
        inputLabbers(n, m, h);

        System.out.println(solve(n, h));
    }

    private static void inputLabbers(int n, int m, int h) throws IOException {
        labbers = new int[n + 2][h + 2];
        for(int i = 1; i <= n; i++) {
            labbers[i][h + 1] = i;
        }

        while(m-- > 0) {
            int x = readInt();
            int y = readInt();
            labbers[y][x] = -1;
            labbers[y + 1][x] = -2;
        }
    }

    private static int solve(int n, int h) {
        for(int i = 0; i <= 3; i++) {
            flag = false;
            backtracking(n, h, 1, 1, 0, i);
            if(flag) return i;
        }
        return -1;
    }

    private static void backtracking(int n, int h, int y, int x, int stack, int max) {
        if(flag) return;
        if(stack == max) {
            flag = checkRoute(n);
            return;
        }

        for(int i = y; i < n; i++) {
            int beginX = (i == y) ? x : 1;
            for(int j = beginX; j <= h; j++) {
                if(flag) return;
                if(labbers[i][j] == 0 && labbers[i + 1][j] == 0) {
                    labbers[i][j] = -1; labbers[i + 1][j] = -2;
                    backtracking(n, h, i, j + 1, stack + 1, max);
                    labbers[i][j] = labbers[i + 1][j] = 0;
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
            while(labbers[line][x] == 0) x++;
            if(labbers[line][x] < 0) {
                line += labbers[line][x] == -1 ? 1 : -1;
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
