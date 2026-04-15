package BaekJoon;

import java.io.*;

public class BaekJoon_19582 {

    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        System.out.println(solve(n));
    }

    private static String solve(int n) throws IOException {
        long total = 0;
        int max = 0;
        boolean give = false;
        while(n-- > 0) {
            int x = readInt();
            int p = readInt();

            if(x >= total) {
                total += p;
                max = Math.max(p, max);
            } else if(!give) {
                if(x >= total - max) {
                    total = total - max + Math.min(max, p);
                }
                give = true;
            } else {
                return "Zzz";
            }
        }
        return "Kkeo-eok";
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + ( n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}
