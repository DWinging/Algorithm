/**
 * [BOJ] 1068 - 트리
 * - 제출 날짜: 2026년 3월 2일
 * - 결과: 맞았습니다!!
 * - 메모리: 11496 KB
 * - 시간: 68 ms
 */

import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Integer>[] leaf;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int root = inputTree(n);
        int rm = readInt();
        System.out.println(dfs(n, root, rm));
    }

    private static int inputTree(int n) throws IOException {
        leaf = new ArrayList[n + 1];
        for(int i = 0; i < n; i++) leaf[i] = new ArrayList<>();

        int root = 0;
        for(int i = 0; i < n; i++) {
            int node = readInt();
            if(node == -1) root = i;
            else leaf[node].add(i);
        }
        return root;
    }

    private static int dfs(int n, int root, int rm) {
        int[] stack = new int[n];
        int top = -1, cnt = 0;
        if(rm != root) stack[++top] = root;

        while(top > -1) {
            int node = stack[top--];
            int child = 0;

            for(int i : leaf[node]) {
                if(i == rm) continue;
                stack[++top] = i;
                child++;
            }
            if(child == 0) cnt++;
        }
        return cnt;
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        boolean flag = false;
        if(c == '-') {flag = true; c = System.in.read(); }
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return flag ? -n : n;
    }
}
