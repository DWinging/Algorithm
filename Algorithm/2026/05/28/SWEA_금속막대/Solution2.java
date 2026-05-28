import java.io.*;

class Solution {
    
    static final int MAX_SIZE = 50;

    static int[] pointer = new int[MAX_SIZE + 1];
    static int[] is_parents = new int[MAX_SIZE + 1];
    static int[] front = new int[MAX_SIZE + 1];
    static int c;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int T = readInt();

        for(int t = 1; t <= T; t++) {
            int n = readInt();
            inputScrews(n, t);
            int s = getStart(n, t);
            sb.append('#').append(t).append(solve(n, s)).append('\n');
        }
        System.out.print(sb);
    }

    private static void inputScrews(int n, int mark) throws IOException {
        for(int i = 1; i <= n; i++) {
            int f = readInt();
            int b = readInt();
            pointer[f] = b;
            is_parents[b] = mark;
            front[i] = f;
        }
    }

    private static int getStart(int n, int mark) {
        for(int i = 1; i <= n; i++) {
            if(is_parents[front[i]] != mark) return front[i];
        }        
        return -1;
    }

    private static String solve(int n, int s) {
        StringBuilder sb = new StringBuilder();
        int idx = s;
        while(n-- > 0) {
            sb.append(' ').append(idx).append(' ').append(pointer[idx]);
            idx = pointer[idx];
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