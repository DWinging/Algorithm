import java.util.*;
import java.io.*;

class Solution {

    static final int MAX_RANGE = 100_000;
    static int[][] arr = new int[MAX_RANGE][2];
    static int[][] sum = new int[MAX_RANGE][2];

    static int c;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int T = readInt();

        for(int t = 1; t <= T; t++) {
            int n = readInt();

            for(int i = 0; i < n; i++) {
                int a = readInt();
                int b = readInt();
                
                arr[i][0] = a;
                arr[i][1] = b;
                sum[i][0] = a + b;
                sum[i][1] = i;
            }

            Arrays.sort(sum, 0, n, (a, b) -> b[0] - a[0]);

            long res = 0;
            for(int i = 0; i < n; i++) {
                int idx = sum[i][1];
                if((i & 1) == 0) res += arr[idx][0];
                else res -= arr[idx][1];
            }

            sb.append('#').append(t).append(' ').append(res).append('\n');
        }
        System.out.print(sb);
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