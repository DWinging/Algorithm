import java.io.*;

class Main {

    final static int INF = 200_000;

    static int[][] dp = new int[INF][2];
    static int[] arr = new int[INF];
    static int c;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();

        int t = readInt();

        while(t-- > 0) {
            int n = readInt();

            for(int i = 0; i < n; i++) {
                dp[i][0] = dp[i][1] = -1;
                arr[i] = readInt();
            }

            sb.append(solve(n)).append('\n');
        }

        System.out.println(sb);
    }

    private static int solve(int n) {
        for(int i = n - 1; i >= 0; i--) {
            for(int diff = 0; diff < 2; diff++) {
                int next = i + 1 < n ? dp[i + 1][diff ^ 1] : 0;

                int val = next + arr[i] * diff;

                if(i + 1 < n) {
                    int next2 = i + 2 < n ? dp[i + 2][diff ^ 1] : 0;

                    int val2 = next2 + (arr[i] + arr[i + 1]) * diff;
                    val = Math.min(val, val2);
                }

                dp[i][diff] = val;
            }
        }

        return dp[0][1];
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