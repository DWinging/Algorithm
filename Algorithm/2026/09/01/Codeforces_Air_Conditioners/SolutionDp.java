import java.io.*;

class Main {

    static final int INF = 1_000_300_005;
    
    static int[] arr = new int[300_001];
    static int[] stack = new int[300_001];
    static int c;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int T = readInt();

        while(T-- > 0) {
            int n = readInt();
            int k = readInt();

            for(int i = 0; i <= n; i++) { arr[i] = INF; }

            int top = -1;
            for(int i = 0; i < k; i++) {
                stack[++top] = readInt();
            }

            for(int i = 0; i < k; i++) {
                arr[stack[i]] = readInt();
            }

            for(int i = n - 1; i >= 1; i--) {
                if(arr[i] > arr[i + 1] + 1) {
                    arr[i] = arr[i + 1] + 1;
                }
            }
            
            for(int i = 1; i <= n; i++) {
                if(arr[i] > arr[i - 1] + 1) arr[i] = arr[i - 1] + 1;
                sb.append(arr[i]).append(' ');
            }
            sb.append('\n');
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