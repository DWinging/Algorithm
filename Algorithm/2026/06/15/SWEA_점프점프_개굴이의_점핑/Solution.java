import java.util.*;
import java.io.*;

class Solution {

    private static class Node implements Comparable<Node> {
        int idx, y, x, f;

        public int compareTo(Node n) {
            if(this.y != n.y) return this.y - n.y;
            else return this.x - n.x;
        }
    }
    
    static final int MAX_SIZE = 300_000;
    static final int MAX_COORD = 100_000;
    
    static Node[] nodes = new Node[MAX_SIZE];
    static {
        for(int i = 0; i < MAX_SIZE; i++) nodes[i] = new Node(); 
    }
    
    static int[][] dp = new int[MAX_SIZE][2];
    static int[] maxRow = new int[MAX_COORD + 1];
    static int[] maxCol = new int[MAX_COORD + 1];
    static int c;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int T = readInt();

        for(int t = 1; t <= T; t++) {
            int n = readInt();
            int k = readInt();

            for(int i = 0; i < n; i++) {
                nodes[i].idx = i;
                nodes[i].y = readInt();
                nodes[i].x = readInt();
                nodes[i].f = readInt();

                dp[i][0] = -1;
                dp[i][1] = -1;
            }

            Arrays.sort(nodes, 0, n);

            sb.append('#').append(t).append(' ').append(solve(n, k, 0)).append('\n');
        }
        System.out.print(sb);
    }

    private static int solve(int n, int k, int s) {
        int cur = 0, end = 0;
        for(int i = 0; i < n; i++) {
            if(nodes[i].idx == s) {
                cur = i;
            } else if(nodes[i].idx == n - 1) {
                end = i;
                break;
            }
        }

        Arrays.fill(maxRow, -1);
        Arrays.fill(maxCol, -1);

        dp[cur][0] = nodes[cur].f;
        dp[cur][1] = nodes[cur].f;
        
        maxRow[nodes[cur].y] = nodes[cur].f;
        maxCol[nodes[cur].x] = nodes[cur].f;

        for(int i = cur + 1; i <= end; i++) {
            Node node = nodes[i];

            int fromRow = maxRow[node.y];
            int fromCol = maxCol[node.x];

            if(fromRow >= k) {
                dp[i][0] = fromRow - k + node.f;
            }
            
            if(fromCol >= k) {
                dp[i][1] = fromCol - k + node.f;
            }

            int curMax = Math.max(dp[i][0], dp[i][1]);
            
            if(curMax != -1) {
                if(curMax > maxRow[node.y]) maxRow[node.y] = curMax;
                if(curMax > maxCol[node.x]) maxCol[node.x] = curMax;
            }
        }

        return Math.max(dp[end][0], dp[end][1]);
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