package BaekJoon;

import java.io.*;

public class BaekJoon_1941 {

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
        for (int y = 0; y < MAX_RANGE; y++) {
            while (c <= ' ') c = System.in.read();
            int ny = y * MAX_RANGE;
            for (int x = 0; x < MAX_RANGE; x++) {
                map[ny + x] = c == 'S' ? 1 : 0;
                c = System.in.read();
            }
        }
    }

    private static void solve() {
        for (int i = 0; i < SIZE; i++) {
            if (map[i] == 1) {
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

        int temp = mask;
        while (temp > 0) {
            int b = Integer.numberOfTrailingZeros(temp);
            int y = b / MAX_RANGE;
            int x = b % MAX_RANGE;

            for (int[] d : DIRE) {
                int ny = y + d[0];
                int nx = x + d[1];
                int next = ny * MAX_RANGE + nx;

                if (ny >= 0 && ny < MAX_RANGE && nx >= 0 && nx < MAX_RANGE) {
                    int nextBit = (1 << next);
                    if ((mask & nextBit) == 0) {
                        int nextMask = mask | nextBit;
                        if (!selected[nextMask]) {
                            selected[nextMask] = true;
                            dfs(nextMask, sCnt + map[next], cnt + 1);
                        }
                    }
                }
            }
            temp &= (temp - 1);
        }
    }
}