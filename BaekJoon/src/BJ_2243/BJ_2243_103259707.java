package BJ_2243;

import java.io.*;
/**
 * 2026년 2월 24일 풀이
 * BaekJoon_2243 사탕상자
 * 메모리 34368 KB
 * 시간 216 ms
 */
public class BJ_2243_103259707 {

    final static int KIND = 1_000_000;
    static int[] sumTree = new int[KIND * 4];
    static int c;

    public static void main(String[] args) throws IOException {
        int n = init();
        System.out.print(solve(n));
    }

    private static int init() throws IOException {
        c = System.in.read();
        return readInt();
    }

    private static String solve(int n) throws IOException {
        StringBuilder sb = new StringBuilder();
        while(n-- > 0) {
            int command = readInt();
            if(command == 1) {
                int idx = readInt();
                sb.append(searchTree(1, 1, KIND, idx)).append('\n');
            } else {
                int num = readInt();
                int cnt = readInt();
                inputCandy(1, 1, KIND, num, cnt);
            }
        }
        return sb.toString();
    }

    private static void inputCandy(int node, int start, int end, int idx, int cnt) {
        sumTree[node] += cnt;
        if(start == end) return;
        int mid = (start + end) / 2;
        if(idx <= mid) inputCandy(node * 2, start, mid, idx, cnt);
        else inputCandy(node * 2 + 1, mid + 1, end, idx, cnt);
    }

    private static int searchTree(int node, int start, int end, int idx) {
        sumTree[node]--;
        if(start == end) return start;

        int mid = (start + end) / 2;
        int value;
        if(idx <= sumTree[node * 2])
            value = searchTree(node * 2, start, mid, idx);
        else
            value = searchTree(node * 2 + 1, mid + 1, end, idx - sumTree[node * 2]);
        return value;
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int n = 0; boolean flag = false;
        if(c == '-') { flag = true; c = System.in.read();}
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c - '0');
            c = System.in.read();
        }
        return flag ? -n : n;
    }
}

