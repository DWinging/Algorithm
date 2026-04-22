/**
 * [BOJ] 1446 - 지름길
 * - 제출 날짜: 2026년 4월 17일
 * - 결과: 맞았습니다!!
 * - 메모리: 11584 KB
 * - 시간: 68 ms
 */

import java.util.*;
import java.io.*;

class Main {
    private static class Shortcut {
        int start, dist;
        public Shortcut(int start, int dist) {
            this.start = start;
            this.dist = dist;
        }
    }

    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int d = readInt();

        List<Shortcut>[] shortcuts = new ArrayList[d + 1];
        for (int i = 0; i <= d; i++) {
            shortcuts[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            int s = readInt();
            int e = readInt();
            int dist = readInt();
            if (e <= d && (e - s) > dist) {
                shortcuts[e].add(new Shortcut(s, dist));
            }
        }

        int[] dp = new int[d + 1];
        Arrays.fill(dp, 0);

        for (int i = 1; i <= d; i++) {
            dp[i] = dp[i - 1] + 1;

            for (Shortcut s : shortcuts[i]) {
                dp[i] = Math.min(dp[i], dp[s.start] + s.dist);
            }
        }

        System.out.println(dp[d]);
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