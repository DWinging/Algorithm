package BaekJoon;

import java.io.*;

public class BaekJoon_2104 {

    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        System.out.println(solve(n));
    }

    private static long solve(int n) throws IOException {
        long[] sum = new long[n + 1];
        int[] arr = new int[n + 1];
        int[] stack = new int[n + 1];
        long value = 0;
        int top = 0;
        stack[0] = 0;
        for(int i = 1; i <= n; i++) {
            arr[i] = readInt();
            sum[i] = sum[i-1] + arr[i];
            while (top > 0 && arr[stack[top]] > arr[i]) {
                int mid = stack[top--];
                int left= stack[top];
                int right = i - 1;
                value = Math.max(value, (sum[right] - sum[left]) * arr[mid]);
            }
            stack[++top] = i;
        }

        while(top > 0) {
            int mid = stack[top--];
            int left= stack[top];
            value = Math.max(value, (sum[n] - sum[left]) * arr[mid]);
        }
        return value;
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
