import java.io.*;

class Solution {

    static int MAX_SIZE = 2_000_005;
    
    static long[] arr = new long[MAX_SIZE];
    static long[] deque = new long[MAX_SIZE];
    static int[] idx = new int[MAX_SIZE];
    static int c;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int T = readInt();

        for(int t = 1; t <= T; t++) {
            int n = readInt();
            long p = readLong();
            int d = readInt();

            for(int i = 0; i < n; i++) arr[i] = readLong();
            sb.append('#').append(t).append(' ').append(solve(n, d, p)).append('\n');
        }
        System.out.print(sb);
    }

    private static int solve(int n, int d, long p) {
        int head = 0, tail = 0, left = 0, cnt = d;

        long sum = 0;
        for(int i = 0; i < d; i++) sum += arr[i];
        deque[tail] = sum;
        idx[tail] = 0;
        tail++;

        long coupon = sum;
        for(int right = d; right < n; right++) {
            sum += arr[right];
            coupon += arr[right];
            coupon -= arr[right - d];

            while(head < tail && deque[tail-1] <= coupon) {
                tail--;
            }
            deque[tail] = coupon;
            idx[tail] = right - d + 1;
            tail++;

            long value = sum - deque[head];
            while(value > p) {
                sum -= arr[left++];
                if(idx[head] < left) head++;                
                value = sum - deque[head];
            }

            if(cnt < right - left + 1) {
                cnt = right - left + 1;
            }
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

    private static long readLong() throws IOException {
        while(c <= ' ') c = System.in.read();
        long n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}