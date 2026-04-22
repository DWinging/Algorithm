/**
 * [BOJ] 2206 - 벽 부수고 이동하기
 * - 제출 날짜: 2026년 4월 7일
 * - 결과: 맞았습니다!!
 * - 메모리: 31924 KB
 * - 시간: 216 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

    final static int[] DIST = {(1 << 10), -(1 << 10), 1, -1};
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();

        int[] map = new int[n << 10 | m];
        for(int y = 0; y < n; y++) {
            while(c <= ' ') c = System.in.read();
            for(int x = 0; x < m; x++) {
                map[y << 10 | x] = c & 15;
                c = System.in.read();
            }
        }

        System.out.println(solve(map, n, m));
    }

    private static int solve(int[] map, int n, int m) {
        int[] visited = new int[(n << 11) | (m << 1) | 1];
        visited[0] = 1;

        int[] deque = new int[(n * m) << 1];
        int head = 0, tail = 1;
        while(head < tail) {
            int cur = deque[head++];
            int xy = cur >> 1;
            int val = cur & 1;
            int time = visited[cur];

            if(xy == (((n - 1) << 10) | (m - 1))) return time;

            for(int d : DIST) {
                int next = xy + d;
                if(check(next, n, m) && val + map[next] < 2) {
                    next = next << 1 | (val + map[next]);
                    if(visited[next] == 0) {
                        deque[tail++] = next;
                        visited[next] = time + 1;
                    }
                }
            }
        }

        return -1;
    }

    private static boolean check(int cur, int n, int m) {
        int y = cur >> 10;
        int x = cur & ((1 << 10) - 1);
        return y >= 0 && y < n && x < m;
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}
