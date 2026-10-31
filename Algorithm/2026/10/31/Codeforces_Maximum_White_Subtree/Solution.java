import java.util.*;
import java.io.*;

class Main {

    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();

        int n = readInt();
        List<Integer>[] list = new ArrayList[n + 1];
        int[] color = new int[n + 1];
        inputColor(list, color, n);
        inputEdge(list, n);

        int[] val = new int[n + 1];
        settingTree(list, color, val, 0, 1);

        solve(list, color, val, 0, 1);

        System.out.println(buildString(val));
    }

    private static void inputColor(List<Integer>[] list, int[] color, int n) throws IOException {
        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
            color[i] = readInt() == 1 ? 1 : -1;
        }
    }

    private static void inputEdge(List<Integer>[] list, int n) throws IOException {
        while(n-- > 1) {
            int u = readInt();
            int v = readInt();

            list[u].add(v);
            list[v].add(u);
        }
    }

    private static int settingTree(List<Integer>[] list, int[] color, int[] val, int p, int node) {
        val[node] += color[node];

        for(int child : list[node]) {
            if(child == p) continue;
            val[node] += settingTree(list, color, val, node, child);
        }

        return val[node] >= 0 ? val[node] : 0;
    }

    private static void solve(List<Integer>[] list, int[] color, int[] val, int p, int node) {
        if(val[node] >= 0) {
            val[node] = Math.max(val[p], val[node]);
        } else {
            val[node] = Math.max(val[p] + color[node], val[node]);
        }

        for(int child : list[node]) {
            if(child == p) continue;
            solve(list, color, val, node, child);
        }
    }

    private static String buildString(int[] val) {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < val.length; i++) {
            sb.append(val[i]).append(' ');
        }
        return sb.toString();
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