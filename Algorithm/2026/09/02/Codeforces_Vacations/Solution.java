import java.io.*;

class Main {

    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();

        int n = readInt();
        int inf = n + 5;
        int[] arr = new int[n];
        int[][] dp = new int[3][n];

        for(int i = 0; i < n; i++) {
            arr[i] = readInt();

            for(int j = 0; j < 3; j++) {
                dp[j][i] = inf;
            }
        }

        dp[0][0] = 1;
        if(arr[0] == 1 || arr[0] == 2) {
            dp[arr[0]][0] = 0;
        } else if(arr[0] == 3){
            dp[1][0] = dp[2][0] = 0;
        }

        for(int i = 1; i < n; i++) {
            dp[0][i] = Math.min(dp[0][i - 1], Math.min(dp[1][i - 1], dp[2][i - 1])) + 1;
            if(arr[i] == 1) {
                dp[1][i] = Math.min(dp[0][i - 1], dp[2][i - 1]);
            } else if(arr[i] == 2) {
                dp[2][i] = Math.min(dp[0][i - 1], dp[1][i - 1]);
            } else if(arr[i] == 3) {
                dp[1][i] = Math.min(dp[0][i - 1], dp[2][i - 1]);
                dp[2][i] = Math.min(dp[0][i - 1], dp[1][i - 1]);
            }
        }

        System.out.println(Math.min(dp[0][n - 1], Math.min(dp[1][n - 1], dp[2][n - 1])));
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