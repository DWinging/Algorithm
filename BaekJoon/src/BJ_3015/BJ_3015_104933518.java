package BJ_3015;

import java.io.*;
/**
 * 2026년 4월 11일 풀이
 * BaekJoon_3015 오아시스 재결합
 * 메모리 15880 KB
 * 시간 180 ms
 */
public class BJ_3015_104933518 {

    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        System.out.println(solve(n));
    }

    private static long solve(int n) throws IOException {
        int[] stack = new int[n];
        int[] cnt = new int[n];
        long comb = 0; int top = -1;
        while(n-- > 0) {
            int val = readInt();
            int temp = 1;
            while(top > -1) {
                if(val > stack[top]) {
                    comb += cnt[top];
                    top--;
                } else if(val == stack[top]) {
                    comb += cnt[top];
                    temp += cnt[top];
                    top--;
                } else {
                    comb++;
                    break;
                }
            }

            stack[++top] = val;
            cnt[top] = temp;
        }
        return comb;
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

