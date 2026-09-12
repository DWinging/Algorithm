import java.io.*;

class Main {
    
    static int c;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();

        int t = readInt();
        while(t-- > 0) {
            int n = readInt();
            int k = readInt();
            int[] arr = new int[n + 2];

            while(c <= ' ') c = System.in.read();
            for(int i = 1; i <= n; i++) {
                int left = Math.max(0, i - k);
                int right = Math.min(n, i + k) + 1;                
                
                if(c == '1') {
                    arr[left]++;
                    arr[right]--;
                }
                c = System.in.read();
            }

            int cnt = 0;
            for(int i = 1; i <= n; i++) {
                arr[i] += arr[i - 1];
                if(arr[i] == 0) {
                    cnt++;
                    if(i + k > n) break;
                    else {
                        arr[i]++;
                        arr[i + k + 1]--;
                    }
                }
            }

            sb.append(cnt).append('\n');
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