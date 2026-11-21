import java.util.*;
import java.io.*;

class Main {

    static int c;
    static long res;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();

        int n = readInt();
        while(n > 0) {
            ArrayList<int[]>[] list = new ArrayList[n];
            for(int i = 0; i < n; i++) {
                list[i] = new ArrayList<>();
            }

            for(int i = 0; i < n - 1; i++) {
                int u = readInt();
                int v = readInt();
                int w = readInt();

                list[u].add(new int[]{v, w});
                list[v].add(new int[]{u, w});
            }

            long[] dp = new long[n];
            int[] size = new int[n];

            settingTree(list, dp, size, 0, -1);

            res = dp[0];
            solve(list, dp, size, n, 0, -1);

            sb.append(res).append('\n');
            n = readInt();
        }

        System.out.print(sb);
    }

    private static void settingTree(List<int[]>[] list, long[] dp, int[] size, int cur, int p) {
        size[cur] = 1;

        for(int[] temp : list[cur]) {
            if(temp[0] == p) continue;

            int child = temp[0];
            int w = temp[1];

            settingTree(list, dp, size, child, cur);

            size[cur] += size[child];
            dp[cur] += dp[child] + (long) w * size[child];
        }
    }

    private static void solve(List<int[]>[] list, long[] dp, int[] size, int n, int cur, int p) {
        res = Math.min(res, dp[cur]);

        for(int[] temp : list[cur]) {
            if(temp[0] == p) continue;

            int child = temp[0];
            int w = temp[1];

            dp[child] = dp[cur] + (long) (n - 2 * size[child]) * w;

            solve(list, dp, size, n, child, cur);
        }
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