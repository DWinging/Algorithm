package BJ_14942;

import java.io.*;
import java.util.*;
/**
 * 2026년 3월 17일 풀이
 * BaekJoon_14942 개미
 * 메모리 35292 KB
 * 시간 288 ms
 */
public class BJ_14942_103968424 {

    static ArrayList<int[]>[] edge;
    static int[] node, dist, arr;
    static boolean[] visited;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        init(n);
        inputArray(n);
        inputEdge(n);
        dfs(1, 0);
        System.out.print(buildString(n));
    }

    private static void init(int n) {
        edge = new ArrayList[n + 1];

        for(int i = 1; i <= n; i++)
            edge[i] = new ArrayList<>();

        node = new int[n + 1];
        dist = new int[n + 1];
        arr = new int[n + 1];
        visited = new boolean[n + 1];
    }

    private static void inputArray(int n) throws IOException {
        for(int i = 1; i <= n; i++)
            arr[i] = readInt();
    }

    private static void inputEdge(int n) throws IOException {
        while(n-- > 1) {
            int a = readInt();
            int b = readInt();
            int c = readInt();

            edge[a].add(new int[]{b, c});
            edge[b].add(new int[]{a, c});
        }
    }

    private static void dfs(int idx, int cnt) {
        node[cnt] = idx;
        arr[idx] = dist[cnt] <= arr[idx] ? 1 : node[binSearch(idx, cnt)];
        visited[idx] = true;
        for(int[] next : edge[idx]) {
            int v = next[0];
            int w = next[1];
            if(!visited[v]) {
                dist[cnt + 1] = dist[cnt] + w;
                dfs(v, cnt + 1);
            }
        }
    }

    private static int binSearch(int idx, int cnt) {
        int left = 0, right = cnt, res = 0;
        while(left <= right) {
            int mid = (left + right) >> 1;

            int target = dist[cnt] - arr[idx];
            if(dist[mid] >= target) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    private static String buildString(int n) {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            sb.append(arr[i]).append('\n');
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
