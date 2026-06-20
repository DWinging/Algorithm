import java.io.*;

class Solution {

    static final int MAX_SIZE = 2_000;
    
    static int[][] arr = new int[MAX_SIZE + 1][MAX_SIZE + 1];
    static long[][] dp = new long[MAX_SIZE + 1][MAX_SIZE + 1];
    static char[] answer = new char[MAX_SIZE * 2];
    static int c;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int T = readInt();

        for(int t = 1; t <= T; t++) {
            int n = readInt();
            int m = readInt();

            for(int i = 1; i <= n; i++) {
                while(c <= ' ') c = System.in.read();
                for(int j = 1; j <= m; j++) {
                    arr[i][j] = c - 'a' + 1;
                    c = System.in.read();
                }
            }
            
            long res = solve(n, m, 100);
            sb.append('#').append(t).append(' ').append(buildString(res, n + m - 1, 100)).append('\n');
        }
        System.out.print(sb);
    }

    private static long solve(int n, int m, int mod) {
        dp[0][0] = arr[0][0];

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(i == 1) dp[i][j] = dp[i][j - 1] * mod + arr[i][j];
                else if(j == 1) dp[i][j] = dp[i - 1][j] * mod + arr[i][j];
                else dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) * mod + arr[i][j];
            }
        }
        return dp[n][m];
    }

    private static String buildString(long res, int len, int mod) {
        StringBuilder sb = new StringBuilder();
        for(int i = len - 1; i >= 0; i--) {
            answer[i] = (char) (res % mod + 'a' - 1);
            res /= mod;
        }

        for(int i = 0; i < len; i++) {
            sb.append(answer[i]);
        }
        return sb.toString();
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