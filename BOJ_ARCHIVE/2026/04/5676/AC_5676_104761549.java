/**
 * [BOJ] 5676 - 음주 코딩
 * - 제출 날짜: 2026년 4월 6일
 * - 결과: 맞았습니다!!
 * - 메모리: 17336 KB
 * - 시간: 248 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {
    static int n, k, offset, c;
    static int[] tree = new int[1 << 18];

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (checkEOF()) {
            n = readInt();
            k = readInt();

            offset = 1;
            while (offset < n) offset <<= 1;
            
            for (int i = 1; i < (offset << 1); i++) tree[i] = 1;

            for (int i = 0; i < n; i++) {
                tree[offset + i] = formattingValue(readInt());
            }

            for (int i = offset - 1; i > 0; i--) {
                tree[i] = tree[i << 1] * tree[i << 1 | 1];
            }

            while (k-- > 0) {
                char comm = readCommand();
                if (comm == 'C') {
                    update(readInt(), formattingValue(readInt()));
                } else {
                    int res = query(readInt(), readInt());
                    sb.append(res > 0 ? '+' : (res < 0 ? '-' : '0'));
                }
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    private static void update(int idx, int val) {
        idx += (offset - 1);
        if (tree[idx] == val) return;
        
        tree[idx] = val;
        while (idx > 1) {
            idx >>= 1;
            int nextVal = tree[idx << 1] * tree[idx << 1 | 1];
            if (tree[idx] == nextVal) break;
            tree[idx] = nextVal;
        }
    }

    private static int query(int l, int r) {
        l += (offset - 1);
        r += (offset - 1);
        int res = 1;
        while (l <= r) {
            if ((l & 1) == 1) res *= tree[l++];
            if ((r & 1) == 0) res *= tree[r--];
            l >>= 1;
            r >>= 1;
        }
        return res;
    }

    private static int formattingValue(int val) {
        return (val > 0) ? 1 : (val < 0 ? -1 : 0);
    }

    private static boolean checkEOF() throws IOException {
        while (c <= ' ') {
            if (c == -1) return false;
            c = System.in.read();
        }
        return true;
    }

    private static char readCommand() throws IOException {
        while (c <= ' ') c = System.in.read();
        char comm = (char) c;
        c = System.in.read();
        return comm;
    }

    private static int readInt() throws IOException {
        while (c <= ' ') c = System.in.read();
        boolean flag = (c == '-');
        if (flag) c = System.in.read();
        int n = 0;
        while (c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return flag ? -n : n;
    }
}