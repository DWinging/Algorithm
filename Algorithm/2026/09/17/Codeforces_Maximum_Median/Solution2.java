import java.util.*;
import java.io.*;

class Main {

    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();

        int n = readInt();
        int k = readInt();

        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
        }

        Arrays.sort(arr);
        long[] prefix = new long[n + 1];
        for(int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + arr[i];
        }

        int half = n >> 1;
        long left = 0, right = arr[half] + k;

        while(left < right) {
            long mid = (left + right + 1) >> 1;

            int temp = lowerBound(arr, half, n, mid);

            long count = temp - half;
            long sum = prefix[temp] - prefix[half];
            long cost = mid * count - sum;

            if(k >= cost) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(left);
    }

    private static int lowerBound(int[] arr, int left, int right, long target) {
        while(left < right) {
            int mid = (left + right) >> 1;

            if(arr[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
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