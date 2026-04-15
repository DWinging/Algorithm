package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_14565 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        long a = Long.parseLong(st.nextToken());

        System.out.print(n - a + " ");
        System.out.println(gcd(a, n) == 1 ? solve(a, n) : -1);
    }

    private static long gcd(long a, long b) {
        if(a == 0) return b;
        return gcd(b % a, a);
    }

    private static long solve(long a, long b) {
        long r1 = b, r2 = a;
        long t1 = 0, t2 = 1;
        while(r2 > 0) {
            long q = r1 / r2;
            long r = r1 - r2 * q;
            long t = t1 - t2 * q;
            r1 = r2;
            r2 = r;
            t1 = t2;
            t2 = t;
        }

        if(r1 == 1) {
            return t1 <= 0 ? t1 + b : t1;
        }
        else {
            return -1;
        }
    }
}
