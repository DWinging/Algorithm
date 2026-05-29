import java.io.*;
class Solution {
    
    static final int MAX_SIZE = 200_000;
    
    static int[] buffer = new int[MAX_SIZE];
    static int c;
    
	public static void main(String args[]) throws IOException {
        StringBuilder sb = new StringBuilder();
		c = System.in.read();
        int T = readInt();
        
        for(int t = 1; t <= T; t++) {
            int idx = -1;
            while(c <= ' ') c = System.in.read();
            while(c == 'B' || c == 'W') {
                buffer[++idx] = c;
                c = System.in.read();
            }
            
            long w = 0, res = 0;
            for(int i = idx; i >= 0; i--) {
                if(buffer[i] == 'W') w++;
                else res += w;
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