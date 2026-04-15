package BaekJoon;

import java.io.*;
import java.math.BigInteger;

public class BaekJoon_13976 {
    public static void main(String[] args) throws IOException {
        int c = System.in.read();
        long n = 0;
        while(c >= '0' && c <= '9') {
            n = (n * 10) + (c - '0');
            c = System.in.read();
        }

        System.out.println(solve(n));
    }

    private static long solve(long n) {
        if(n % 2 == 1) return 0;
        int mod = 1_000_000_007;
        long sum = 28;
        long pre = 11;

        if(n == 2) return 3;
        if(n == 4) return 11;

        long now;
        for(long i = 6; i <= n; i+= 2) {
            now = ((sum + pre) % mod + 2) % mod;
            sum = (sum + (now * 2) % mod) % mod;
            pre = now;
        }
        return pre;
    }
}
