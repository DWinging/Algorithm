import java.util.*;
import java.io.*;

class Main {

    static int c;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();

        int t = readInt();
        while (t-- > 0) {
            int n = readInt();
            int m = readInt();

            int[] val = new int[m];
            for (int i = 0; i < m; i++) {
                val[i] = readInt();
            }

            List<Integer>[] list = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < n - 1; i++) {
                int u = readInt();
                int v = readInt();

                list[u].add(v);
                list[v].add(u);
            }

            boolean res = solve(list, val, n, 1);
            sb.append(res ? "YES" : "NO").append('\n');
        }

        System.out.print(sb);
    }

    private static boolean solve(List<Integer>[] list, int[] val, int n, int root) {
        Queue<int[]> que = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];

        for (int i : val) {
            que.add(new int[]{i, -1});
            visited[i] = true;
        }

        que.add(new int[]{root, 1});
        visited[root] = true;

        while (!que.isEmpty()) {
            int[] temp = que.poll();
            int cur = temp[0];
            int flag = temp[1];

            for (int next : list[cur]) {
                if (visited[next]) continue;

                if (flag == 1 && list[next].size() == 1) {
                    return true;
                }

                visited[next] = true;
                que.add(new int[]{next, flag});
            }
        }

        return false;
    }

    private static int readInt() throws IOException {
        while (c <= ' ') c = System.in.read();
        int n = 0;
        while (c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}
