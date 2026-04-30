/**
 * [BOJ] 14428 - 수열과 쿼리 16
 * - 제출 날짜: 2026년 3월 1일
 * - 결과: 틀렸습니다
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

    final static int INF = 1_000_000_005;
    static int[] tree, arr;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        inputTree(n);
        int m = readInt();
        System.out.print(solve(n, m));
    }

    private static void inputTree(int n) throws IOException {
        tree = new int[2 * n];
        arr = new int[n + 1];
        for(int i = 0; i < n; i++) {
            arr[i + 1] = readInt();
            tree[i + n] = i + 1;
        }

        for(int i = n - 1; i >= 0; i--) {
            int n1 = tree[i << 1];
            int n2 = tree[(i << 1) | 1];
            tree[i] = arr[n1] <= arr[n2] ? n1 : n2;
        }
    }

    private static String solve(int n, int m) throws IOException {
        StringBuilder sb = new StringBuilder();
        while(m-- > 0) {
            int command = readInt();
            int a = readInt();
            int b = readInt();
            if(command == 1) update(n, a, b);
            else sb.append(searchQuery(n, a, b)).append('\n');
        }
        return sb.toString();
    }

    private static void update(int n, int a, int b) {
        arr[a] = b;
        int idx = a + n - 1;
        while(idx > 1) {
            idx >>= 1;
            int n1 = tree[idx << 1];
            int n2 = tree[(idx << 1) | 1];
            tree[idx] = arr[n1] <= arr[n2] ? n1 : n2;
        }
    }

    private static int searchQuery(int n, int a, int b) {
        int res = INF, idx = 0;
        int left = a + n - 1;
        int right = b + n - 1;

        while(left <= right) {
            if(left % 2 != 0) {
                int temp = tree[left++];
                if(arr[temp] <= res) {
                    res = arr[temp];
                    idx = temp;
                }
            }
            if(right % 2 == 0) {
                int temp = tree[right--];
                if(arr[temp] < res) {
                    res = arr[temp];
                    idx = temp;
                }
            }
            left >>= 1;
            right >>= 1;
        }
        return idx;
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
