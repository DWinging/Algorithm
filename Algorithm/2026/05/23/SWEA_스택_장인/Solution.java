import java.io.*;

class Solution {

    static final int MAX_SIZE = 100_000;
    
    static int[] stack = new int[MAX_SIZE];
    static int c;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int T = readInt();

        for(int t = 1; t <= T; t++) {
            int n = readInt();
            sb.append('#').append(t).append(' ').append(solve(n)).append('\n');
        }
        System.out.println(sb);
    }

    private static String solve(int n) throws IOException {
        StringBuilder sb = new StringBuilder();

        boolean flag = true;
        int top = -1, idx = 1;

        while(n-- > 0) {
            int target = readInt();

            if(!flag) continue;
            while(idx <= target) {
                stack[++top] = idx++;
                sb.append('+');
            }
            
            flag = target == stack[top];
            sb.append('-');
            top--;
        }

        return flag ? sb.toString() : "NO";
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