import java.io.*;

class Solution {

    static final int MAX_SIZE = 100_000;

    static int[] trains = new int[MAX_SIZE];
    static int[] lights = new int[MAX_SIZE];
    static int c;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int T = readInt();

        for(int t = 1; t <= T; t++) {
            int n = readInt();
            int k = readInt();

            for(int i = 0; i < n; i++) trains[i] = readInt();
            for(int i = 0; i < n; i++) lights[i] = readInt();

            sb.append('#').append(t).append(' ').append(solve(n, k)).append('\n');
        }
        System.out.print(sb);
    }

    private static int solve(int n, int k) {
        int cnt = 0, len = 0;

        cnt += (lights[0] ^ 1);
        lights[0] |= 1;

        cnt += (lights[n - 1] ^ 1);
        lights[n - 1] |= 1;

        for(int i = 0; i < n; i++) {
            if(lights[i] == 1) len = 0;
            else len += trains[i];

            if(len >= k) {
                lights[i] = 1;
                cnt++;
                len = 0;
            }
        }
    
        return cnt;
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