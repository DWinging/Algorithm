import java.io.*;

class Main {

    final static int MAX_TURN = 1_000_000_000;
    final static int INF = 100_000;
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        StringBuilder sb = new StringBuilder();

        int t = readInt();
    
        int[] power = new int[INF + 1];    
        int[] sTurn = new int[INF + 1];
        int[] eTurn = new int[INF + 1];
        
        while(t-- > 0) {
            int n = readInt();
            int q = readInt();

            for(int i = 1; i <= n; i++) {
                power[i] = readInt();
            }

            int idx = 1;
            sTurn[idx] = 1;
            for(int i = 2; i <= n; i++) {
                sTurn[i] = i - 1;
                if(power[idx] > power[i]) {
                    eTurn[i] = i - 1;
                } else {
                    eTurn[idx] = i - 1;
                    idx = i;
                }
            }

            eTurn[idx] = MAX_TURN + 1;

            while(q-- > 0) {
                int cur = readInt();
                int k = readInt();

                int s = sTurn[cur];
                int e = eTurn[cur];

                int val = 0;
                if(s > k) {
                    val = 0;
                } else {
                    val = Math.min(e, k + 1) - s;
                }

                sb.append(val).append('\n');
            }
        }
        System.out.println(sb);
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