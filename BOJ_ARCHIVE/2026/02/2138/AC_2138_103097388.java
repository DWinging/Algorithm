/**
 * [BOJ] 2138 - 전구와 스위치
 * - 제출 날짜: 2026년 2월 19일
 * - 결과: 맞았습니다!!
 * - 메모리: 12116 KB
 * - 시간: 92 ms
 */

import java.io.*;
import java.util.*;

public class Main {

    static int c;
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        boolean[] state = inputState(n);
        boolean[] target = inputState(n);
        boolean[] state2 = Arrays.copyOf(state, n);

        int cnt1 = solve(state, target, n, false);
        int cnt2 = solve(state2, target, n, true);

        System.out.println(cnt1 != -1 && cnt2 != -1 ? Math.min(cnt1, cnt2) : Math.max(cnt1, cnt2));
    }

    private static boolean[] inputState(int n) throws IOException {
        while(c <= ' ') c = System.in.read();
        boolean[] state = new boolean[n];
        for(int i = 0; i < n; i++) {
            state[i] = c == '1';
            c = System.in.read();
        }
        return state;
    }

    private static int solve(boolean[] state, boolean[] target, int n, boolean first) {
        int cnt = 0;

        if(first) {
            state[0] = !state[0];
            state[1] = !state[1];
            cnt++;
        }

        for(int i = 1; i < n; i++) {
            if(state[i-1] != target[i-1]) {
                state[i-1] = !state[i-1];
                state[i] = !state[i];
                if(i + 1 < n) state[i+1] = !state[i+1];
                cnt++;
            }
        }

        return state[n-1] == target[n-1] ? cnt : -1;
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
