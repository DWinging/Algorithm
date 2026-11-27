import java.util.*;
import java.io.*;

class Main {

    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();

        int n = readInt();
        int c = readInt();

        long[][] paints = new long[n + 1][2];

        for(int i = 0; i < n; i++) {
            paints[i][0] = readInt();
            paints[i][1] = readInt();
        }
        
        Arrays.sort(paints, (a, b) -> {
            if(a[0] == b[0]) return Long.compare(a[1], b[1]);
            else return Long.compare(a[0], b[0]);
        });

        long[][] dp = new long[n + 1][2];
        System.out.println(solve(paints, dp, n, c));
    }

    private static long solve(long[][] paints, long[][] dp, int n, int c) {
        for(int i = 1; i <= n; i++) {
            int idx = binarySearch(paints, i, c);
            dp[i][0] = Math.max(dp[idx][0], dp[idx][1]) + paints[i][1];
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1]);
        }

        return Math.max(dp[n][0], dp[n][1]);
    }

    private static int binarySearch(long[][] paints, int idx, int c) {
        long target = paints[idx][0];
        int left = 1, right = idx;

        while(left < right) {
            int mid = (left + right) >> 1;
            long h = target - paints[mid][0];

            if(c <= h) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left - 1;
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