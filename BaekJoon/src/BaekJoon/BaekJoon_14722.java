package BaekJoon;

import java.io.IOException;

public class BaekJoon_14722 {
    static int c;
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt() + 1;
        int[] arr = inputArray(n);
        System.out.println(solve(arr, n));
    }

    private static int[] inputArray(int n) throws IOException {
        int[] arr = new int[n * n];
        for(int i = 1; i < n; i++) {
            for(int j = 1; j < n; j++) {
                arr[i * n + j] = readInt();
            }
        }
        return arr;
    }

    private static int solve(int[] arr, int n) {
        int kind = 3;
        for(int i = 0; i < n; i++) {
            arr[i * n] = -1;
            arr[i] = -1;
        }

        for(int y = 1; y < n; y++) {
            for(int x = 1; x < n; x++) {
                int w1 = arr[(y-1) * n + x] + ((arr[(y-1) * n + x] + 1) % kind == arr[y * n + x] ? 1 : 0);
                int w2 = arr[y * n + (x-1)] + ((arr[y * n + (x-1)] + 1) % kind == arr[y * n + x] ? 1 : 0);
                arr[y * n + x] = Math.max(w1, w2);
            }
        }
        return arr[(n - 1) * n + (n - 1)] + 1;
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c - '0');
            c = System.in.read();
        }
        return n;
    }
}
