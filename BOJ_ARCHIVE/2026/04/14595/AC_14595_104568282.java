/**
 * [BOJ] 14595 - 동방 프로젝트 (Large)
 * - 제출 날짜: 2026년 4월 1일
 * - 결과: 맞았습니다!!
 * - 메모리: 15512 KB
 * - 시간: 80 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    static final InputStream in = System.in;
    static final byte[] buffer = new byte[1 << 16]; // 64KB
    static int ptr = 0, len = 0;

    static int[] room;

    public static void main(String[] args) throws Exception {
        int n = nextInt();
        room = new int[n + 1];

        int m = nextInt();
        while (m-- > 0) {
            int x = nextInt();
            int y = nextInt();
            room[x]++;
            room[y]--;
        }
        System.out.print(countRoom(n));
    }

    private static int countRoom(int n) {
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            room[i] += room[i - 1];
            if (room[i] == 0) cnt++;
        }
        return cnt;
    }

    static int read() throws IOException {
        if (ptr >= len) {
            len = in.read(buffer);
            ptr = 0;
            if (len <= 0) return -1;
        }
        return buffer[ptr++];
    }

    static int nextInt() throws IOException {
        int c, n = 0;
        while ((c = read()) <= ' ') ;
        do {
            n = (n << 3) + (n << 1) + (c & 15);
        } while ((c = read()) >= '0');
        return n;
    }
}