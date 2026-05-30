import java.io.*;

class Solution {

    static final int MAX_SIZE = 200_000;

    static int[] stack = new int[MAX_SIZE];
    static int c;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int t = readInt();        

        while(t-- > 0) {
            int n = readInt();
            while(c <= ' ') c = System.in.read();

            int top = -1;            
            while(n-- > 0) {
                stack[++top] = c;
                c = System.in.read();

                if(top >= 2 && stack[top] == 'x' && stack[top - 1] == 'o' && stack[top-2] == 'f') {
                    top -= 3;
                }
            }

            sb.append(top + 1).append('\n');
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