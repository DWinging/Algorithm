import java.io.*;

class Solution {

    static final int SIZE = 100_000;
    static final String W_ANSWER = "UNKNOWN";
    
    static int[] diff = new int[SIZE + 1];
    static int[] parents = new int[SIZE + 1];
    static int[] ranks = new int[SIZE + 1];
    static int[] stack = new int[SIZE + 1];
    static int c;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int T = readInt();

        for(int t = 1; t <= T; t++) {
            int n = readInt();
            int m = readInt();

            init(n);
            sb.append('#').append(t);
            solve(sb, n, m);
        } 
        System.out.print(sb);
    }

    private static void init(int n) {
        for(int i = 1; i <= n; i++) {
            diff[i] = 0;
            parents[i] = i;
        }
    }

    private static void solve(StringBuilder sb, int n, int m) throws IOException {
        while(m-- > 0) {
            while(c <= ' ') c = System.in.read();
            int comm = c;
            c = System.in.read();

            if(comm == '!') {
                int a = readInt();
                int b = readInt();
                int w = readInt();

                union(a, b, w);
            } else {
                int a = readInt();
                int b = readInt();

                if(find(a) == find(b)) sb.append(' ').append(diff[b] - diff[a]);
                else sb.append(' ').append(W_ANSWER);
            }
        }
        sb.append('\n');
    }

    private static void union(int a, int b, int w) {
        int pA = find(a);
        int pB = find(b);

        if(pA == pB) return;

        if(ranks[pA] >= ranks[pB]) {
            parents[pB] = pA;
            diff[pB] = diff[a] - diff[b] + w;
            ranks[pA] += ranks[pB];
        } else {
            parents[pA] = pB;
            diff[pA] = diff[b] - diff[a] + w;
            ranks[pB] += ranks[pA];
        }
    }

    private static int find(int p) {
        int top = -1;

        while(parents[p] != p) {
            stack[++top] = p;
            p = parents[p];
        }

        while(top > -1) {
            int cur = stack[top--];
            int pCur = parents[cur];
            diff[cur] += diff[pCur];
            parents[cur] = p;
        }

        return p;
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