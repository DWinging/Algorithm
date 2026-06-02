import java.io.*;

public class Solution {

    static final int MAX_SIZE = 85;
    static final int MAX_E = 165;
    
    static int[] e = new int[MAX_SIZE];
    static int[][][] dp = new int[MAX_SIZE][MAX_E][MAX_E];
    static int[][][] visited = new int[MAX_SIZE][MAX_E][MAX_E];
    static int mark = 0, n, c;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int T = readInt();
        int res = 0;
        
        for(int t = 1; t <= T; t++) {
            n = readInt();
            mark++;
            
            for (int i = 0; i < n; i++) e[i] = readInt();

            if(n == 1 || n == 2) res = e[n - 1];
            else res = solve(0, e[0], e[1]);
            sb.append('#').append(t).append(' ').append(res).append('\n');
        }
        System.out.print(sb);
    }
    
    private static int solve(int idx, int cur, int next) {
        if (idx == n - 2) {
            return next;
        }
        
        if (visited[idx][cur][next] == mark) {
            return dp[idx][cur][next];
        }
        
        int ans = 0;        
        ans = solve(idx + 1, next, e[idx + 2]);
        
        if (next >= 1) {
            ans = Math.max(ans, solve(idx + 1, next - 1, e[idx + 2] + cur));
        }
        
        visited[idx][cur][next] = mark;
        return dp[idx][cur][next] = ans;
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int num = 0;
        while(c >= '0' && c <= '9') {
            num = (num << 3) + (num << 1) + (c & 15);
            c = System.in.read();
        }
        return num;
    }
}