import java.io.*;
import java.util.*;

class Solution {

    static final int MAX_RANGE = 100_000;

    static int[] arr = new int[MAX_RANGE];
    static int c;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int T = readInt();

        for(int t = 1; t <= T; t++) {
            int n = readInt();
            int total = 0;
            for(int i = 0; i < n; i++) {
                int val = readInt();
                arr[i] = val;
                total += val;
            }

            int res = solve(n, total);
            sb.append('#').append(t).append(' ').append(res).append('\n');
        }
        System.out.print(sb);
    }

    private static int solve(int n, int total) {
        Arrays.sort(arr, 0, n);
        for(int i = n - 3; i >= 0; i -= 3) {
            total -= arr[i];
        }
        return total;
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