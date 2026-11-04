import java.util.*;
import java.io.*;

class Main {

    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();

        int n = readInt();
        long m = readLong();

        long[] prefix = new long[n + 1];
        long[] arr = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + readLong();
        }

        System.out.println(solve(arr, prefix, 0, n, m));
    }

    private static long solve(long[] arr, long[] prefix,
                              int left, int right, long m) {
        if (left >= right) {
            return 0;
        }

        int mid = (left + right) / 2;

        long res = solve(arr, prefix, left, mid, m);
        res += solve(arr, prefix, mid + 1, right, m);

        res += countValue(prefix, left, mid, right, m);

        int l = left;
        int r = mid + 1;
        int idx = left;

        while (l <= mid && r <= right) {
            if (prefix[l] <= prefix[r]) {
                arr[idx++] = prefix[l++];
            } else {
                arr[idx++] = prefix[r++];
            }
        }

        while (l <= mid) {
            arr[idx++] = prefix[l++];
        }

        while (r <= right) {
            arr[idx++] = prefix[r++];
        }

        for (int i = left; i <= right; i++) {
            prefix[i] = arr[i];
        }

        return res;
    }

    private static long countValue(long[] prefix,
                                   int left, int mid, int right, long m) {
        long res = 0;
        int j = mid + 1;

        for (int i = left; i <= mid; i++) {
            while (j <= right && prefix[j] - prefix[i] < m) {
                j++;
            }

            res += j - (mid + 1);
        }

        return res;
    }

    private static int readInt() throws IOException {
        while (c <= ' ') c = System.in.read();
        boolean flag = false;
        if (c == '-') {
            flag = true;
            c = System.in.read();
        }
        int n = 0;
        while (c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return flag ? -n : n;
    }

    private static long readLong() throws IOException {
        while (c <= ' ') c = System.in.read();
        boolean flag = false;
        if (c == '-') {
            flag = true;
            c = System.in.read();
        }
        long n = 0;
        while (c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return flag ? -n : n;
    }
}