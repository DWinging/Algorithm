import java.io.*;

class Solution {

    static final int MAX_SIZE = 1000;

    static int[] lis = new int[MAX_SIZE + 1];
    static int[] houses = new int[MAX_SIZE + 1];
    static int c;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int T = readInt();

        for(int t = 1; t <= T; t++) {
            int n = readInt();
            for(int i = 0; i < n; i++) houses[i] = readInt();

            sb.append('#').append(t).append(' ').append(solve(n)).append('\n');
        }
        System.out.print(sb);
    }

    private static int solve(int n) {
        int len = 0;
        
        for(int i = 0; i < n; i++) {
            int h = houses[i];

            if(lis[len] < h) {
                lis[++len] = h;
            } else {
                int idx = binarySearch(h, len);
                lis[idx] = h;
            }
        }

        return n - len;
    }

    private static int binarySearch(int target, int idx) {
        int left = 0, right = idx, mid;

        while(left <= right) {
            mid = (left + right) / 2;

            if(lis[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return left;
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