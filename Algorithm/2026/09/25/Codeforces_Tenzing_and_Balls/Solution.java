import java.io.*;

class Main {

    final static int INF = 200_000;
    static int c;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();

        int t = readInt();

        int[] arr = new int[INF + 1];
        int[] dp = new int[INF + 1];
        int[] best = new int[INF + 1];

        while(t-- > 0) {
            int n = readInt();
            
            for(int i = 1; i <= n; i++) {
                arr[i] = readInt();
                best[i] = INF + 1;
            }

            for(int i = 1; i <= n; i++) {
                int val = arr[i];
                dp[i] = dp[i - 1] + 1;

                if(best[val] != INF + 1) {
                    dp[i] = Math.min(dp[i], best[val]);
                } 

                best[val] = Math.min(dp[i - 1], best[val]);
            }
            
            sb.append(n - dp[n]).append('\n');
        }
        System.out.println(sb);
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