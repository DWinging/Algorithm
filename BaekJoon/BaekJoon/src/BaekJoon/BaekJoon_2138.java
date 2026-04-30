package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_2138 {

    static int c;
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        boolean[] state = inputState(n);
        boolean[] target = inputState(n);

        int cnt1 = solve(state, target, n, false);
        int cnt2 = solve(state, target, n, true);

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

    private static int solve(boolean[] origin, boolean[] target, int n, boolean first) {
        boolean[] state = Arrays.copyOf(origin, n);
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
