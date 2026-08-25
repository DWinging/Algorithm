import java.util.*;
import java.io.*;

class Main {

    static int[] arr = new int[100];
    static int c;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();

        int t = readInt();
        while(t-- > 0) {
            int n = readInt();
            for(int i = 0; i < n; i++) arr[i] = readInt();
            Arrays.sort(arr, 0, n);

            sb.append(solve(n)).append('\n');
        }
        System.out.println(sb);
    }

    private static int solve(int n) {
        int left = 0, right = n;
        int res = 0;
        while(left <= right) {
            int mid = (left + right) >> 1;

            if(game(mid, n)) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return res;
    }

    private static boolean game(int k, int n) {
        int cnt = 0, t = k, right = n - 1;
        for(int left = 0; left <= right; left++) {
            while(right-- >= left) {
                if(arr[right + 1] <= t) {
                    cnt++;
                    break;
                }
            }
            t--;
        }

        return cnt == k;
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