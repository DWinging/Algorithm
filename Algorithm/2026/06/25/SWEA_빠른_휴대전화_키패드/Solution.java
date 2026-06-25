import java.io.*;

class Solution {

    final static int[] CH = {2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 9, 9, 9, 9};

    static int c;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int T = readInt();

        for(int t = 1; t <= T; t++) {
            int s = readInt();
            int n = readInt();
            int res = 0;

            while(n-- > 0) {
                int val = inputValue();
                if(s == val) res++;
            }

            sb.append('#').append(t).append(' ').append(res).append('\n');
        }
        System.out.print(sb);
    }

    private static int inputValue() throws IOException {
        while(c <= ' ') c = System.in.read();
        int res = 0;

        while(c >= 'a' && c <= 'z') {
            res = (res << 3) + (res << 1) + CH[(c - 'a')];
            c = System.in.read();
        }
        return res;
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