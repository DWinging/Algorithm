import java.io.*;

class Main {

    static final int INF = 100_000;
    
    static int[] arr = new int[INF];
    
    static int[] parents = new int[26];
    static int[] check = new int[26];
    
    static int c, cnt;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();

        int T = readInt();
        for(int t = 1; t <= T; t++) {
            for(int i = 0; i < 26; i++) {
                parents[i] = i;
            }

            int n = readInt();
            while(c <=' ') c = System.in.read();
            for(int i = 0; i < n; i++) {
                arr[i] = c - 'a';
                c = System.in.read();
            }        

            cnt = 0;
            for(int i = 0; i < n; i++) {
                int val = arr[i];
                if(parents[val] == val) {
                    val = solve(val, t);
                    cnt++;
                } 
                sb.append((char)(parents[val] + 'a'));
            }

            sb.append('\n');
        }
        System.out.println(sb);
    }

    private static int solve(int x, int mark) {
        for(int i = 0; i < 26; i++) {
            if(i != x && check[i] < mark) {
                if(dfs(i, x)) {
                    parents[x] = i;
                    check[i] = mark;
                    break;
                }
            }
        }
        return x;
    }

    private static boolean dfs(int a, int b) {
        if(cnt == 25) return true;
        while(parents[a] != a) {
            if(parents[a] == b) return false;
            a = parents[a];
        }
        return a != b;
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