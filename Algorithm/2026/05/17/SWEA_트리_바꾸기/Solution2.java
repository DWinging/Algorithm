import java.io.*;

class Solution {
    
    static final int MAX_SIZE = 300_000;
    
    static int[] edges = new int[MAX_SIZE + 1];
    static int c;
    
	public static void main(String args[]) throws IOException	{
        StringBuilder sb = new StringBuilder();
		c = System.in.read();
        
        int t = readInt();
        
		while(t-- > 0) {
			int n = readInt();
            init(n);
            inputEdges(n);
         	sb.append(solve(n)).append('\n');   
		}
        System.out.print(sb);
	}
    
    private static void init(int n) {
        for(int i = 1; i <= n; i++) edges[i] = -2;
    }
    
    private static void inputEdges(int n) throws IOException {
        while(n-- > 1) {
            int a = readInt();
            int b = readInt();
            edges[a]++;
            edges[b]++;
        }
    }
    
    private static int solve(int n) {
        int cnt = 0;
        for(int i = 1;  i <= n; i++) {
            // if 조건문을 제거하여 연산 과정을 최적화함
            cnt += edges[i] & ~(edges[i] >> 31);
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