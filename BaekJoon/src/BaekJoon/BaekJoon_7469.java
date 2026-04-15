package BaekJoon;

import java.io.*;

public class BaekJoon_7469 {

    final static int MAX_VALUE = 1_000_000_000;
    static int[][] tree;
    static int[] arr;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();

        init(n);
        System.out.print(solve(n, m));
    }

    private static void init(int n) throws IOException {
        arr = new int[n + 1];
        for(int i = 1; i <= n; i++) arr[i] = readInt();

        tree = new int[n * 4][];
        mergeSortTree(1, 1, n);
    }

    private static void mergeSortTree(int node, int s, int e) {
        tree[node] = new int[e - s + 1];
        if(s == e) {
            tree[node][0] = arr[s];
            return;
        }

        int mid = (s + e) / 2;

        mergeSortTree(node << 1, s, mid);
        mergeSortTree(node << 1 | 1, mid + 1, e);
        tree[node] = merge(tree[node << 1], tree[node << 1 | 1]);
    }

    private static int[] merge(int[] left, int[] right) {
        int[] res = new int[left.length + right.length];
        int l = 0, r = 0, idx = 0;
        while(l < left.length && r < right.length) {
            res[idx++] = left[l] < right[r] ? left[l++] : right[r++];
        }

        while(l < left.length) res[idx++] = left[l++];
        while(r < right.length) res[idx++] = right[r++];
        return res;
    }

    private static String solve(int n, int m) throws IOException {
        StringBuilder sb = new StringBuilder();
        while(m-- > 0) {
            int s = readInt();
            int e = readInt();
            int k = readInt();
            sb.append(searchTree(n, s, e, k)).append('\n');
        }
        return sb.toString();
    }

    private static int searchTree(int n, int s, int e, int k) {
        int res = 0, left = -MAX_VALUE, right = MAX_VALUE;
        while(left <= right) {
            int mid = (right + left) / 2;
            if(countLessOrEqual(1, 1, n, s, e, mid) >= k) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    private static int countLessOrEqual(int node, int s, int e, int i, int j, int k) {
        if(j < s || e < i) return 0;
        if(i <= s && e <= j) {
            return upperBound(tree[node], k);
        }

        int mid = (s + e) / 2, res = 0;
        res += countLessOrEqual(node << 1, s, mid, i, j, k);
        res += countLessOrEqual(node << 1 | 1, mid + 1, e, i, j, k);
        return res;
    }

    private static int upperBound(int[] arr, int target) {
        int left = 0, right = arr.length;
        while(left < right) {
            int mid = (left + right) / 2;
            if(arr[mid] <= target) left = mid + 1;
            else right = mid;
        }
        return right;
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        boolean flag = false;
        if(c == '-') {flag = true; c = System.in.read();}
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return flag ? -n : n;
    }
}
