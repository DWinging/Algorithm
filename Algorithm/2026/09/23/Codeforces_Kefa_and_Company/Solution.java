import java.util.*;
import java.io.*;

class Main {

    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();

        int n = readInt();
        long d = readInt();

        int[][] arr = new int[n][2];
        for(int i = 0; i < n; i++) {
            arr[i][0] = readInt();
            arr[i][1] = readInt();
        }

        Arrays.sort(arr, (l1, l2) -> { return l1[0] - l2[0]; });

        int right = 1;
        long val = arr[0][1], res = arr[0][1];
        for(int left = 0; left < n; left++) {
            while(right < n && arr[right][0] - arr[left][0] < d) {
                val += arr[right++][1];
            }

            if(val > res) {
                res = val;
            }

            val -= arr[left][1];
        }

        System.out.println(res);
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