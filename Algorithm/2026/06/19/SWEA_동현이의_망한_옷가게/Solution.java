import java.io.*;

class Solution {

    static int c;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();    
        int T = readInt();

        for(int t = 1; t <= T; t++) {
            int n = readInt();
    
            long res = readInt() - 1;
            int cost = 2;
            
            for(int i = 1; i < n; i++) {
                int val = readInt();
                
                res += (long) (val - 1) / cost;
                
                if (val == cost) {
                    cost++;
                }
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