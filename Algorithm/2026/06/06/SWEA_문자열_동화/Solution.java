import java.io.*;

class Solution {

    static int[][] words = new int[3][1_000];
    
    static int c;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int T = readInt();

        for(int t = 1; t <= T; t++) {
            int n = readInt();
            for(int i = 0; i < 3; i++) readChar(n, i);
            sb.append('#').append(t).append(' ').append(solve(n)).append('\n');
        }
        System.out.print(sb);
    }

    private static int solve(int n) {
        int res = 0;        
        for(int i = 0; i < n; i++) {
            int wA = words[0][i];
            int wB = words[1][i];
            int wC = words[2][i];

            if(wA == wB) {
                if(wA != wC) res++;
            } else {
                res += (wA == wC || wB == wC) ? 1 : 2;  
            }
        }
        return res;
    }

    private static void readChar(int n, int idx) throws IOException {
        while(c <= ' ') c = System.in.read();
        for(int i = 0; i < n; i++) {
            words[idx][i] = c;
            c = System.in.read();
        }
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