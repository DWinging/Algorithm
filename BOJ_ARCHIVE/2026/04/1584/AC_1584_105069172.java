/**
 * [BOJ] 1584 - 게임
 * - 제출 날짜: 2026년 4월 17일
 * - 결과: 맞았습니다!!
 * - 메모리: 14424 KB
 * - 시간: 156 ms
 */

import java.io.*;
import java.util.*;

class Main {
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int[][] arr = new int[501][501];
        int n = readInt();
        inputArea(arr, n, 1);

        int m = readInt();
        inputArea(arr, m, -1);

        System.out.println(bfs(arr, 0, 500));
    }

    private static void inputArea(int[][] arr, int n, int w) throws IOException {
        while (n-- > 0) {
            int x1 = readInt(), y1 = readInt();
            int x2 = readInt(), y2 = readInt();
            int sX = Math.min(x1, x2), eX = Math.max(x1, x2);
            int sY = Math.min(y1, y2), eY = Math.max(y1, y2);

            for (int x = sX; x <= eX; x++) {
                for (int y = sY; y <= eY; y++) {
                    arr[x][y] = w;
                }
            }
        }
    }

    private static int bfs(int[][] arr, int start, int end) {
        final int BIT = 9;
        final int MASK = (1 << BIT) - 1;
        int TOTAL = (end + 1) * (end + 1);
        int[] deque = new int[TOTAL];
        int head = 0, tail = 0;

        deque[tail++] = (0 << 18) | (start << BIT) | start;
        arr[start][start] = -1;

        int[][] dist = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (head != tail) {
            int cur = deque[head];
            head = (head + 1) % TOTAL;

            int cx = cur & MASK;
            int cy = (cur >> BIT) & MASK;
            int t = cur >> 18;

            if (cy == end && cx == end) return t;

            for (int[] d : dist) {
                int ny = cy + d[0];
                int nx = cx + d[1];

                if (ny >= 0 && ny <= end && nx >= 0 && nx <= end) {
                    if (arr[ny][nx] == -1) continue;

                    if (arr[ny][nx] == 0) {
                        int next = (t << 18) | (ny << BIT) | nx;
                        head = (head - 1 + TOTAL) % TOTAL;
                        deque[head] = next;
                    } else {
                        int next = ((t + 1) << 18) | (ny << BIT) | nx;
                        deque[tail] = next;
                        tail = (tail + 1) % TOTAL;
                    }
                    arr[ny][nx] = -1;
                }
            }
        }
        return -1;
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