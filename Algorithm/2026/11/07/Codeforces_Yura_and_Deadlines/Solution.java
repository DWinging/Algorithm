import java.io.*;
import java.util.*;

class Main {
    static int c;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();

        int t = readInt();

        while (t-- > 0) {
            int n = readInt();
            int[] arr = new int[n + 1];
            long[] dp = new long[n + 1];
            long[] tree = new long[n + 1];

            int[] head = new int[n + 2];
            int[] next = new int[n + 1];
            Arrays.fill(head, -1);

            for (int i = 1; i <= n; i++) {
                arr[i] = readInt();
            }

            long answer = 0;

            for (int i = 1; i <= n; i++) {
                for (int j = head[i]; j != -1; j = next[j]) {
                    update(tree, n, j, dp[j]);
                }

                int limit = i - arr[i] - 1;
                dp[i] = arr[i] + query(tree, limit);
                answer = Math.max(answer, dp[i]);

                long time = (long) i + arr[i] + 1;

                if (time <= n) {
                    int pos = (int) time;
                    next[i] = head[pos];
                    head[pos] = i;
                }
            }

            sb.append(answer).append('\n');
        }

        System.out.print(sb);
    }

    static void update(long[] tree, int n, int idx, long val) {
        while (idx <= n) {
            tree[idx] = Math.max(tree[idx], val);
            idx += idx & -idx;
        }
    }

    static long query(long[] tree, int idx) {
        long result = 0;

        while (idx > 0) {
            result = Math.max(result, tree[idx]);
            idx -= idx & -idx;
        }

        return result;
    }

    static int readInt() throws IOException {
        while (c <= ' ') c = System.in.read();
        int n = 0;

        while (c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }

        return n;
    }
}