package BaekJoon;

import java.io.*;

public class BaekJoon_3015 {

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
                if(val < stack[top]) {
                    comb++;
                    break;
                }
                else if(val == stack[top])
                    temp += cnt[top];

                comb += cnt[top--];
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
