/**
 * [BOJ] 21925 - 짝수 팰린드롬
 * - 제출 날짜: 2026년 2월 23일
 * - 결과: 맞았습니다!!
 * - 메모리: 11836 KB
 * - 시간: 68 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

    static int[] que;
    static int c, N;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }

    private static void init() throws IOException {
        c = System.in.read();
        que = new int[N = readInt()];
    }

    private static int solve() throws IOException {
        int head = 0, tail = -1, cnt = 0;
        for(int i = 0; i < N; i += 2) {
            que[++tail] = readInt();
            que[++tail] = readInt();
            if(que[head] == que[tail] && isPalindrome(head, tail)) {
                head = tail + 1;
                cnt++;
            }
        }
        return head <= tail ? - 1 : cnt;
    }

    private static boolean isPalindrome(int head, int tail) {
        for(int h = head, t = tail; h < t; h++, t--) if(que[h] != que[t]) return false;
        return true;
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
