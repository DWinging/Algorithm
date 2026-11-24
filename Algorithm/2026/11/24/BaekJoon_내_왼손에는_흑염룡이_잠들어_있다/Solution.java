import java.util.*;
import java.io.*;

class Main {

    static ArrayList<int[]>[] list;
    static int[][] len;
    static int[] idx;
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();

        int n = readInt();
        list = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < n - 1; i++) {
            int u = readInt();
            int v = readInt();
            int w = readInt();

            list[u].add(new int[]{v, w});
            list[v].add(new int[]{u, w});
        }
        
        len = new int[n + 1][2];
        idx = new int[n + 1];
        settingTree(1, 0);

        int[] res = new int[n + 1];
        solve(res, 1, 0, 0);

        System.out.println(buildString(res));
    }

    private static void settingTree(int node, int p) {
        for(int[] temp : list[node]) {
            int child = temp[0];
            int w = temp[1];

            if(child == p) continue;

            settingTree(child, node);
            if(len[node][0] <= len[child][0] + w) {
                len[node][1] = len[node][0];
                
                len[node][0] = len[child][0] + w;
                idx[node] = child;
            } else if(len[node][1] <= len[child][0] + w) {
                len[node][1] = len[child][0] + w;
            }
        }
    }

    private static void solve(int[] res, int node, int p, int val) {
        res[node] = Math.max(val, len[node][0]);

        for(int[] temp : list[node]) {
            int child = temp[0];
            int w = temp[1];

            if(child == p) continue;

            if(idx[node] == child) {
                solve(res, child, node, Math.max(val, len[node][1]) + w);
            } else {
                solve(res, child, node, Math.max(val, len[node][0]) + w);
            }
        }
    }

    private static String buildString(int[] res) {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < res.length; i++) {
            sb.append(res[i]).append('\n');
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